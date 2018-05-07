package com.example.m3.mapapp;

import android.content.Context;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Bulletin {

    public String ID;
    public String username;
    public String title;
    public String location;
    public String content;
    public String time;
    public int type;


    public static ArrayList<Bulletin> getBulletinsFromDatabase(String location, DatabaseHelper db, Context context){
        ArrayList<Bulletin> bulletinList = new ArrayList<>();
        Cursor res = db.getAllData();
        // if there is nothing in res
        // no data in your table
        if (res.getCount() == 0){
            return bulletinList;
        }
        // build a String to display
        while (res.moveToNext()){
            Bulletin b = new Bulletin();
            // get the column name + data of that row
            // add it to the string buffer
            b.ID = res.getString(0);
            b.username = res.getString(1);
            b.title = res.getString(2);
            b.location = res.getString(3);
            b.content = res.getString(4);
            b.time = res.getString(5);
            b.type = res.getInt(6);
            if(b.location.equals(location)) {
                System.out.println("Adding at " + location);
                bulletinList.add(b);
            }
        }

        return bulletinList;
    }



    public static ArrayList<Bulletin> getBulletinsFromFile(String filename, Context context){
        ArrayList<Bulletin> bulletinList = new ArrayList<>();

        //try to read from json, get info by using tags
        //construct a bulletin object for each bulletin in json
        //add the object to arrayList, return ArrayList
        try{
            String jsonString = loadJsonFromAsset(filename, context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray bulletins = json.getJSONArray("bulletins");

            //for loop to go through each bulletin in your bulletins array

            for (int i = 0; i < bulletins.length(); i++){
                Bulletin bulletin = new Bulletin();

                bulletin.title = bulletins.getJSONObject(i).getString("title");
                bulletin.content = bulletins.getJSONObject(i).getString("content");
                bulletin.username = bulletins.getJSONObject(i).getString("username");
                bulletin.time = bulletins.getJSONObject(i).getString("time");
                bulletin.type = bulletins.getJSONObject(i).getInt("type");
                bulletin.location = bulletins.getJSONObject(i).getString("location");

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
