package com.epicodus.ypapp.models;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.epicodus.ypapp.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHoP on 11/6/15.
 */

@Table(name = "Users", id = "_id")
public class User extends Model{
    ParseUser mParseUser;

    @Column(name = "userName")
    private String mUserName;

    @Column(name = "password")
    private String mPassword;

    @Column(name = "location")
    private String mLocation;

    @Column(name = "imageId")
    private int mImageId;



    public User(){
        super();
    }

    public User(String username, String password) {
        super();
        mUserName = username;
        mPassword = password;

        mParseUser = new ParseUser();
        mParseUser.setUsername(username);
        mParseUser.setPassword(password);
    }

    public void signUp() {
        mParseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(String.valueOf(R.string.sign_up), String.valueOf(R.string.succeed));
                } else {
                    Log.i(String.valueOf(R.string.sign_up), String.valueOf(R.string.fail));
                    e.printStackTrace();
                }
            }
        });
    }

    public static void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i(String.valueOf(R.string.login), String.valueOf(R.string.succeed));

                } else {
                    Log.i(String.valueOf(R.string.login), String.valueOf(R.string.fail));
                }
            }
        });
    }

//    public String getParseUserName() {
//        return mParseUser.getString("username");
//    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String username) {
        mUserName = username;
        mParseUser.setUsername(username);
    }

    public void setPassword(String password) {
        mParseUser.setPassword(password);
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

    public static List<User> all(){
        return new Select()
                .from(User.class)
                .execute();
    }

    public static User find(String username) {
        return new Select()
                .from(User.class)
                .where("Name = ?", username)
                .executeSingle();
    }

    public List<Route> getRoutes(){
        List<UserRoute> joins = new Select()
                .from(UserRoute.class)
                .where("User = ?", this.getId())
                .execute();

        List<Route> routes = new ArrayList<>();

        for(UserRoute join : joins){
            routes.add(join.mRoute);
        }

        return routes;
    }
}

