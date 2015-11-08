package com.epicodus.ypapp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

/**
 * Created by YHoP on 10/27/15.
 */
@Table(name = "Routes_sql", id = "_id")
public class Route_sql extends Model {
    ParseObject mRouteObject;

    @Column(name = "name")
    private String mName;

    @Column(name = "date")
    private String mDate;

    @Column(name = "location")
    private String mLocation;

    @Column(name = "distance")
    private Number mDistance;

    @Column(name = "startTime")
    private Date mStartTime;

    @Column(name = "finishTime")
    private Date mFinishTime;


    public Route_sql(){
        super();
    }

    public Route_sql(String name, String date, String location, Number distance, Date startTime, Date finishTime){
        mName = name;
        mDate = date;
        mLocation = location;
        mDistance = distance;
        mStartTime = startTime;
        mFinishTime = finishTime;
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

    public Number getDistance() {
        return mDistance;
    }

    public void setDistance(Number distance) {
        mDistance = distance;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Date startTime) {
        mStartTime = startTime;
    }

    public Date getFinishTime() {
        return mFinishTime;
    }

    public void setFinishTime(Date finishTime) {
        mFinishTime = finishTime;
    }

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
