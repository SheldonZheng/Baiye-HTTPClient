package com.baiye.HTTPClientTest;

import com.baiye.HTTPClient.HTTPClient;
import com.baiye.HTTPClient.HTTPGetClient;
import com.baiye.Model.HTTPResData;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Baiye on 8/21/16.
 */
public class HTTPGetClientTest {

    @Test
    public void sendRequestTest()
    {
        HTTPClient client = new HTTPGetClient();
        HTTPResData resData = null;
        try {
            resData = client.sendRequest("http://127.0.0.1:8080/test?a=1&b=白叶","");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(resData.getHead());
        Assert.assertNotNull(resData.getContent());
        Assert.assertNotNull(resData.getContentLength());
    }
}
