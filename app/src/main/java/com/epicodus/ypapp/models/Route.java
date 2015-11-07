package com.epicodus.ypapp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by YHoP on 10/27/15.
 */
@Table(name = "Routes", id = "_id")
public class Route extends Model {
    ParseObject mRouteObject;

    @Column(name = "name")
    private String mName;

    @Column(name = "date")
    private String mDate;

    @Column(name = "location")
    private String mLocation;

    @Column(name = "distance")
    private double mDistance;

    @Column(name = "totalTime")
    private double mTotalTime;

    @Column(name = "pace")
    private double mPace;

    @Column(name = "mapId")
    private int mMapId;

    @Column(name = "userName")
    private String mUserName;

    public Route(){
        super();
    }

    public Route(String name, String date, String location, double distance, double totalTime){
        mRouteObject = new ParseObject("Routes");
        mRouteObject.put("name", name);
        mName = name;
        mRouteObject.put("date", date);
        mDate = date;
        mRouteObject.put("location", location);
        mLocation = location;
        mRouteObject.put("distance", String.valueOf(distance));
        mDistance = distance;
        mRouteObject.put("totalTime", String.valueOf(totalTime));
        mTotalTime = totalTime;
        mRouteObject.saveInBackground();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDate() { return mDate;}

    public void setDate(String date) { mDate = date; }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(long distance) {
        mDistance = distance;
    }

    public double getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(long totalTime) {
        mTotalTime = totalTime;
    }

    public int getMapId() { return mMapId; }

    public void setMapId(int mapId) { mMapId = mapId; }

    public String getUser() {
        return mUserName;
    }

    public void setUser(String userName) {
        mUserName = userName;
    }


    public static List<Route> all(){
        return new Select()
                .from(Route.class)
                .execute();
    }

    public static Route find(String location) {
        return new Select()
                .from(Route.class)
                .where("Location = ?", location)
                .executeSingle();
    }
}
