package com.epicodus.ypapp.models;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;

/**
 * Created by YHoP on 10/27/15.
 */

public class Route {
    ParseObject mRouteObject;

    public Route(){
        mRouteObject = new ParseObject("Routes");
    }

    public Route(String name, String location, Number distance, Date startTime, Date finishTime, ParseFile file){

        mRouteObject = new ParseObject("Routes");
        mRouteObject.put("name", name);
        mRouteObject.put("location", location);
        mRouteObject.put("distance", distance);
        mRouteObject.put("startTime", startTime);
        mRouteObject.put("finishTime", finishTime);
        mRouteObject.put("image", file);
        mRouteObject.put("user", ParseUser.getCurrentUser().get("username"));
        save(mRouteObject);

        // Route route_sql = new Route(name, location, distance, startTime, finishTime);
    }

    public ParseObject getRouteObject(){
        return mRouteObject;
    }

    public void setImgId(String imgId){
        mRouteObject.put("imgId", imgId);
    }

    public String getName() {
        return mRouteObject.getString("name");
    }

    public void setName(String name) {
        mRouteObject.put("name", name);
    }

    public Date getDate() {
        return mRouteObject.getDate("startTime");
    }

    public void setDate(Date date) {
        mRouteObject.put("date", date);
    }

    public String getLocation() {
        return mRouteObject.getString("location");
    }

    public void setLocation(String location) {
        mRouteObject.put("location", location);
    }

    public Number getDistance() {
        return mRouteObject.getNumber("distance");
    }

    public void setDistance(Number distance) {
        mRouteObject.put("distance", distance);
    }

    public Date getStartTime() {
        return mRouteObject.getDate("startTime");
    }

    public void setStartTime(Date startTime) {
        mRouteObject.put("startTime", startTime);
    }

    public void setFinishTime(Date finishTime) {
        mRouteObject.put("finishTime", finishTime);
    }

    public Date getFinishTime() {
        return mRouteObject.getDate("finishTime");
    }

    public ParseFile getImage() {
        return mRouteObject.getParseFile("image");
    }

    public void save(ParseObject parseObject){
        parseObject.saveInBackground(new SaveCallback(){
            @Override
            public void done(ParseException e) {
                if( e == null) {
                    Log.i("Save RouteObject", "Succeed");

                } else {
                    Log.i("Save RouteObject", "Failed");
                }
            }
        });
    }

}
