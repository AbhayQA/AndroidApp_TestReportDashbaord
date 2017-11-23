/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klm.mobile.android.reports;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Details
 */
public class AndroidUte extends Activity {
    String guestStatus = "fail";
    String response = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_ute);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.myjson.com/bins/577fo", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    response = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ArrayList<String> status = getStatus(response, "time", "finalStatus");
                TextView r = (TextView)findViewById(R.id.text_id);
                TextView r1 = (TextView)findViewById(R.id.text_id1);
                TextView r2 = (TextView)findViewById(R.id.text_id2);
                TextView r3 = (TextView)findViewById(R.id.text_id3);
                TextView r4 = (TextView)findViewById(R.id.text_id4);

                if(status.get(0)!=null) {
                    r.setText("Status @"+status.get(0));
                }
                if(status.get(1)!=null) {
                    String[] details = status.get(1).split(",");
                    r1.setText("Pass Percentage: "+details[0]);
                    r2.setText("Total Passed: "+details[1]);
                    r3.setText("Total Failed: "+details[2]);
                    r4.setText("Not Executed: "+details[3]);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public ArrayList getStatus(String response, String... APIName) {
        JSONObject jObj = null;
        String json = "";
        int apiLen = APIName.length;
        ArrayList<String> statusList = new ArrayList<String>();

        try{
            BufferedReader br = new BufferedReader(new StringReader(response));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            json = sb.toString();
            // try parse the string to a JSON object
            try {
                jObj = new JSONObject(json);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                for(int i=0; i<apiLen; i++) {
                    statusList.add(jObj.getString(APIName[i]));
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch(Exception e) {

        }

        return statusList;
    }


}