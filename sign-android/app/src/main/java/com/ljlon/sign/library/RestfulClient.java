package com.ljlon.sign.library;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by liujinlong on 15/8/24.
 */
public class RestfulClient {

    public static String Post(String strUrl, JSONObject obj) {
        String responseBody = "";

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(strUrl);
        httppost.setHeader("Content-Type", "application/json");

        try
        {
            StringEntity s = new StringEntity(obj.toString());

            httppost.setEntity(s);
            HttpResponse response = httpclient.execute(httppost);

            /* Checking response */
            if(response != null)
            {
                responseBody = EntityUtils.toString(response.getEntity());
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }

        return responseBody;
    }

}
