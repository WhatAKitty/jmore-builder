package com.whatakitty.jmore.dfs.client.webdav;

import java.io.*;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2020/07/09
 * @since 1.0.0
 **/
public class WebDavDfsClientTest {

    private String endpoint;
    private String accessKey;
    private String accessSecret;
    private String testFilePath;

    @Before
    public void init() {
        this.endpoint = System.getenv("DFS_ENDPOINT");
        this.accessKey = System.getenv("DFS_ACCESS_KEY");
        this.accessSecret = System.getenv("DFS_ACCESS_SECRET");
        this.testFilePath = System.getenv("DFS_TEST_FILE_PATH");
    }


    @Test
    public void test_put() throws FileNotFoundException, ExecutionException, InterruptedException {
        WebDavProperties webDavProperties = new WebDavProperties();
        webDavProperties.setType("webdav");
        webDavProperties.setEndpoint(this.endpoint);
        webDavProperties.setAccessKey(this.accessKey);
        webDavProperties.setAccessSecret(this.accessSecret);
        WebDavDfsClient webDavDfsClient = new WebDavDfsClient(webDavProperties);
        webDavDfsClient.init();

        WebDavObjectKey objectKey = new WebDavObjectKey("aa", new String[] {"test"});
        WebDavObject webDavObject = new WebDavObject(objectKey);
        webDavObject.setInputStream(new FileInputStream(new File(this.testFilePath)));

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