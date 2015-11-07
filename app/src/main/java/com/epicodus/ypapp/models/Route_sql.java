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
public class Route_sql extends Model {
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

    @Column(name = "imageId")
    private int mMapId;

    public Route_sql(){
        super();
    }

    public Route_sql(String name, String date, String location, double distance, double totalTime){
        mName = name;
        mDate = date;
        mLocation = location;
        mDistance = distance;
        mTotalTime = totalTime;
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


    public static List<Route_sql> all(){
        return new Select()
                .from(Route_sql.class)
                .execute();
    }

    public static Route_sql find(String name) {
        return new Select()
                .from(Route_sql.class)
                .where("name = ?", name)
                .executeSingle();
    }
}
