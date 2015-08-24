package com.ljlon.sign.library.serverApi;

import com.ljlon.sign.config.Common;
import com.ljlon.sign.library.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by liujinlong on 15/8/24.
 */
public class UserApi {

    public static boolean Login(String strUserName, String strPassword, User user){
        JSONObject obj = new JSONObject();
        try {
            obj.put("user_name", strUserName);
            obj.put("password", strPassword);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        String res = RestfulClient.Post(Common.SERVER_HOST + "/user/login", obj);
        try {
            JSONObject resObj = new JSONObject(res);
            if (resObj.getLong("error") != 0) {
                return false;
            }

            if (user != null) {
                JSONObject result = resObj.getJSONObject("result");
                if (user != null) {
                    user.strUserName = result.getString("user_name");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
