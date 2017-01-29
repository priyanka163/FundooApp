package com.fundoo.bridgelabz.attendence_proj.view;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 5/1/17.
 */

public class Restapi {

    private static final String BASE_URL = "http://192.168.0.171:3000/readMonthlyAttendanceSummary";

    // private static final String BASE_URL = "http://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


/*

    RequestParams rp = new RequestParams();
    rp.add("username","aaa");rp.add("password","aaa@123");

    HttpUtils.post(AppConstant.URL_FEED,rp,new

    JsonHttpResponseHandler() {
        @Override
        public void onSuccess ( int statusCode, Header[] headers, JSONObject response){
            // If the response is JSONObject instead of expected JSONArray
            Log.d("asd", "---------------- this is response : " + response);
            try {
                JSONObject serverResp = new JSONObject(response.toString());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void onSuccess ( int statusCode, Header[] headers, JSONArray timeline){
            // Pull out the first event on the public timeline

        }
    }

    );
}    }*/
}