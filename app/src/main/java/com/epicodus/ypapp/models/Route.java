package com.epicodus.ypapp.models;

import com.epicodus.ypapp.ui.MainActivity;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by YHoP on 10/27/15.
 */

public class Route {
    ParseObject mRouteObject;

    public Route(String name, String location, Number distance, Date startTime, Date finishTime){

        mRouteObject = new ParseObject("Routes");
        mRouteObject.put("name", name);
        mRouteObject.put("location", location);
        mRouteObject.put("distance", distance);
        mRouteObject.put("startTime", startTime);
        mRouteObject.put("finishTime", finishTime);
        mRouteObject.put("user", MainActivity.mUserName);
        mRouteObject.saveInBackground();

        // Route route_sql = new Route(name, location, distance, startTime, finishTime);
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

    public void setDistance(long distance) {
        mRouteObject.put("distance", distance);
    }

    public Date getStartTime() {
        return mRouteObject.getDate("startTime");
    }

    public void setStartTime(Date startTime) {
        mRouteObject.put("startTime", startTime);
    }

    public Date getFinishTime() {
        return mRouteObject.getDate("finishTime");
    }

}
