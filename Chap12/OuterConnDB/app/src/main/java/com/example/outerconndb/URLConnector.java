package com.example.outerconndb;

import android.util.Log;

public class URLConnector extends Thread{
    private String result;
    private String URL;

    public URLConnector(String URL) {
        this.URL = URL;
    }


    @Override
    public void run() {
        final String output = request(URL);
        result = output;
    }

    public String request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try{


        } catch (Exception e) {
            Log.e("MyApp", "Exception in processing response",e);
            e.printStackTrace();
        }
        return output.toString();

    }

    public String getResult() {
        return result;
    }
}
