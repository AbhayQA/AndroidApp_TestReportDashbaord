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
public class AndroidLive extends Activity {
    String guestStatus = "fail";
    String response = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_live);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.myjson.com/bins/4ry1w", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    response = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ArrayList<String> status = getStatus(response, "countryList", "login", "fbcard", "checkin", "baggage", "seats"
                                                                            ,"booking", "addBooking", "weather", "time", "finalStatus");
                TextView r = (TextView)findViewById(R.id.text_id);
                TextView r1 = (TextView)findViewById(R.id.text_id1);
                TextView r2 = (TextView)findViewById(R.id.text_id2);
                TextView r3 = (TextView)findViewById(R.id.text_id3);
                TextView r4 = (TextView)findViewById(R.id.text_id4);
                TextView r5 = (TextView)findViewById(R.id.text_id5);
                TextView r6 = (TextView)findViewById(R.id.text_id6);
                TextView r7 = (TextView)findViewById(R.id.text_id7);
                TextView r8 = (TextView)findViewById(R.id.text_id8);
                TextView r9 = (TextView)findViewById(R.id.text_id9);
                TextView r10 = (TextView)findViewById(R.id.text_id10);

                if(status.get(9)!=null) {
                    r.setText("Status @"+status.get(9));
                }
                if(status.get(10)!=null) {
                    String[] details = status.get(10).split(",");
                    r1.setText("Pass Percentage: "+details[0]);
                }
                if(status.get(0).equalsIgnoreCase("pass")) {
                    r2.setText("Country List");
                    r2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r2.setText("Country List");
                    r2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(1).equalsIgnoreCase("pass")) {
                    r3.setText("FB Login");
                    r3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r3.setText("FB Login");
                    r3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(2).equalsIgnoreCase("pass")) {
                    r4.setText("FB Card");
                    r4.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r4.setText("FB Card");
                    r4.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(3).equalsIgnoreCase("pass")) {
                    r5.setText("Check-in");
                    r5.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r5.setText("Check-in");
                    r5.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(4).equalsIgnoreCase("pass")) {
                    r6.setText("Baggage Offer");
                    r6.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r6.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r6.setText("Baggage Offer");
                    r6.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r6.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(5).equalsIgnoreCase("pass")) {
                    r7.setText("Seats Offer");
                    r7.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r7.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r7.setText("Seats Offer");
                    r7.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r7.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(6).equalsIgnoreCase("pass")) {
                    r8.setText("Booking Flow");
                    r8.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r8.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r8.setText("Booking Flow");
                    r8.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r8.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(7).equalsIgnoreCase("pass")) {
                    r9.setText("Add Booking");
                    r9.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r9.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r9.setText("Add Booking");
                    r9.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r9.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                if(status.get(8).equalsIgnoreCase("pass")) {
                    r10.setText("Weather");
                    r10.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.success));
                    r10.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                }
                else {
                    r10.setText("Weather");
                    r10.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.failure));
                    r10.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
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
