package com.epicodus.ypapp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHoP on 11/6/15.
 */

@Table(name = "Users_sql", id = "_id")
public class User_sql extends Model{
    ParseUser mParseUser;

    @Column(name = "userName")
    private String mUserName;

    @Column(name = "password")
    private String mPassword;

    @Column(name = "location")
    private String mLocation;

    @Column(name = "imageId")
    private int mImageId;

    public User_sql(){
        super();
    }

    public User_sql(String username, String password) {
        super();
        mUserName = username;
        mPassword = password;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String username) {
        mUserName = username;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public static List<User_sql> all(){
        return new Select()
                .from(User_sql.class)
                .execute();
    }

    public static User_sql find(String username) {
        return new Select()
                .from(User_sql.class)
                .where("Name = ?", username)
                .executeSingle();
    }

    public List<Route_sql> getRoutes(){
        List<UserRoute> joins = new Select()
                .from(UserRoute.class)
                .where("User = ?", this.getId())
                .execute();

        List<Route_sql> routes = new ArrayList<Route_sql>();

        for(UserRoute join : joins){
            Route_sql route = join.getRoute();
            routes.add(route);
        }
        return routes;
    }
}

