package com.whatakitty.jmore.dfs.client.webdav;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.whatakitty.jmore.dfs.client.api.DfsClient;
import com.whatakitty.jmore.dfs.client.api.DfsListener;
import com.whatakitty.jmore.dfs.client.api.domain.Object;
import com.whatakitty.jmore.dfs.client.api.visitor.Visitor;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;

/**
 * web dav client
 *
 * @author WhatAKitty
 * @date 2020/07/05
 * @since 1.0.0
 **/
@Slf4j
public class WebDavDfsClient implements DfsClient<WebDavObjectKey> {

    private final WebDavProperties configuration;

    private AtomicReference<Sardine> sardine = new AtomicReference<>();

    public WebDavDfsClient(WebDavProperties configuration) {
        this.configuration = configuration;
    }

    @Override
    public void registerListeners(List<DfsListener> listeners) {
        // TODO
        throw new UnsupportedOperationException("not supported register listeners yet");
    }

    @Override
    public void init() {
        final URL url;
        try {
            url = new URL(configuration.getEndpoint());
        } catch (MalformedURLException e) {
            throw new Error("illegal endpoint: " + configuration.getEndpoint());
        }
        final Sardine newSardine = SardineFactory.begin(configuration.getAccessKey(), configuration.getAccessSecret());
        newSardine.enablePreemptiveAuthentication(url.getHost(), 80, 443);
        newSardine.enableCompression();
        sardine.set(newSardine);
    }

    @Override
    public Future<Boolean> putObject(Object<WebDavObjectKey> object) {
        assert object instanceof WebDavObject;
        return CompletableFuture.supplyAsync(() -> {
            try (
                final InputStream inputStream = ((WebDavObject) object).getInputStream();
            ) {
                sardine.get().put(getUrl((String) object.getKey()), inputStream);
                return Boolean.TRUE;
            } catch (IOException e) {
                log.error("failed to put object into platform", e);
            }
            return Boolean.FALSE;
        });
    }

    @Override
    public Future<Object<WebDavObjectKey>> getObject(WebDavObjectKey objectKey) {
        return CompletableFuture.supplyAsync(() -> {
            final WebDavObject webDavObject = new WebDavObject(objectKey);
            try {
                final InputStream inputStream = sardine.get().get(getUrl(objectKey.getKey()));
                webDavObject.setInputStream(inputStream);
                return webDavObject;
            } catch (IOException e) {
                log.error("failed to get data from webdav", e);
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void visit(Visitor visitor) {
        final Sardine sardine = this.sardine.get();
        final WebDavObjectKey objectKey = new WebDavObjectKey("/", new String[0]);
        final WebDavDirectory webDavDirectory = new WebDavDirectory(sardine, objectKey);

        // visit
        webDavDirectory.visit(visitor);
    }

    @Override
    public void destroy() {
        try {
            sardine.get().shutdown();
        } catch (IOException e) {
            log.error("failed to shutdown sardine");
        }
    }

    private String getUrl(String resourceKey) {
        return configuration.getEndpoint() + resourceKey;
    }

}
