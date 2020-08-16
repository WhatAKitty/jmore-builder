package com.whatakitty.jmore.dfs.client.webdav;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/09
 * @since 1.0.0
 **/
@RunWith(PowerMockRunner.class)
@PrepareForTest({SardineFactory.class, FileUtils.class})
public class WebDavDfsClientTest {

    private String endpoint;
    private String accessKey;
    private String accessSecret;
    private String testFilePath;

    @Before
    public void init() throws IOException {
//        this.endpoint = System.getenv("DFS_ENDPOINT");
//        this.accessKey = System.getenv("DFS_ACCESS_KEY");
//        this.accessSecret = System.getenv("DFS_ACCESS_SECRET");
//        this.testFilePath = System.getenv("DFS_TEST_FILE_PATH");
        this.endpoint = "http://xx.com";
        this.accessKey = "";
        this.accessSecret = "";
        this.testFilePath = "";

        // init web dfs client
        final Sardine sardine = PowerMockito.mock(Sardine.class);
        PowerMockito.when(sardine.exists(Mockito.anyString())).thenReturn(true);

        PowerMockito.mockStatic(SardineFactory.class);
        PowerMockito.when(SardineFactory.begin(this.accessKey, this.accessSecret)).thenReturn(sardine);

        PowerMockito.mockStatic(FileUtils.class);
        PowerMockito.when(FileUtils.readFileToByteArray(Mockito.any())).thenReturn(new byte[0]);
    }


    @Test
    public void test_put() throws IOException, ExecutionException, InterruptedException {
        WebDavProperties webDavProperties = new WebDavProperties();
        webDavProperties.setType("webdav");
        webDavProperties.setEndpoint(this.endpoint);
        webDavProperties.setAccessKey(this.accessKey);
        webDavProperties.setAccessSecret(this.accessSecret);
        WebDavDfsClient webDavDfsClient = new WebDavDfsClient(webDavProperties);
        webDavDfsClient.init();

        final byte[] bytes = FileUtils.readFileToByteArray(new File(this.testFilePath));
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        WebDavObjectKey objectKey = new WebDavObjectKey("aa", new String[] {"test"});
        WebDavObject webDavObject = new WebDavObject(objectKey);
        webDavObject.setInputStream(byteArrayInputStream);

        final Boolean result = webDavDfsClient.putObject(webDavObject, false).get();
        Assert.assertTrue(result);

        webDavDfsClient.destroy();
    }

    @Test
    public void test_put_bytes() throws IOException, ExecutionException, InterruptedException {
        WebDavProperties webDavProperties = new WebDavProperties();
        webDavProperties.setType("webdav");
        webDavProperties.setEndpoint(this.endpoint);
        webDavProperties.setAccessKey(this.accessKey);
        webDavProperties.setAccessSecret(this.accessSecret);
        WebDavDfsClient webDavDfsClient = new WebDavDfsClient(webDavProperties);
        webDavDfsClient.init();

        final byte[] bytes = FileUtils.readFileToByteArray(new File(this.testFilePath));
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        WebDavObjectKey objectKey = new WebDavObjectKey("bb", new String[] {"test"});
        WebDavObject webDavObject = new WebDavObject(objectKey);
        webDavObject.setInputStream(byteArrayInputStream);

        final Boolean result = webDavDfsClient.putObject(webDavObject, false).get();
        Assert.assertTrue(result);

        webDavDfsClient.destroy();
    }

    @Test
    public void test_put_no_parents() throws IOException, ExecutionException, InterruptedException {
        WebDavProperties webDavProperties = new WebDavProperties();
        webDavProperties.setType("webdav");
        webDavProperties.setEndpoint(this.endpoint);
        webDavProperties.setAccessKey(this.accessKey);
        webDavProperties.setAccessSecret(this.accessSecret);
        WebDavDfsClient webDavDfsClient = new WebDavDfsClient(webDavProperties);
        webDavDfsClient.init();

        final byte[] bytes = FileUtils.readFileToByteArray(new File(this.testFilePath));
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        WebDavObjectKey objectKey = new WebDavObjectKey("bb", new String[] {"test2", "test_inner2"});
        WebDavObject webDavObject = new WebDavObject(objectKey);
        webDavObject.setInputStream(byteArrayInputStream);

        final Boolean result = webDavDfsClient.putObject(webDavObject, true).get();
        Assert.assertTrue(result);

        webDavDfsClient.destroy();
    }

}