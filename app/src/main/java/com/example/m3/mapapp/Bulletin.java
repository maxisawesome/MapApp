package com.example.m3.mapapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Bulletin {
    public String title;
    public String description;
    public String author;
    public String time;
    public int type;

    public static ArrayList<Bulletin> getbulletinsFromFile(String filename, Context context){
        ArrayList<Bulletin> bulletinList = new ArrayList<>();

        //try to read from json, get info by using tags
        //construct a bulletin object for each bulletin in json
        //add the object to arrayList, return ArrayList
        try{
            String jsonString = loadJsonFromAsset("posts.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray bulletins = json.getJSONArray("bulletins");

            //for loop to go through each bulletin in your bulletins array

            for (int i = 0; i < bulletins.length(); i++){
                Bulletin bulletin = new Bulletin();

                bulletin.title = bulletins.getJSONObject(i).getString("title");
                bulletin.description = bulletins.getJSONObject(i).getString("description");
                bulletin.author = bulletins.getJSONObject(i).getString("author");
                bulletin.time = bulletins.getJSONObject(i).getString("time");
                bulletin.type = bulletins.getJSONObject(i).getInt("type");

                bulletinList.add(bulletin);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bulletinList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
