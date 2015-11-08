package com.epicodus.ypapp.models;

import android.util.Log;

import com.epicodus.ypapp.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by YHoP on 11/6/15.
 */

public class User{
    ParseUser mParseUser;

    public User(String username, String password) {
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

    public String getUserName() {
        return mParseUser.getString("username");
    }

    public void setUserName(String username) {
        mParseUser.setUsername(username);
    }

    public void setPassword(String password) {
        mParseUser.setPassword(password);
    }

    public String getLocation() {
        return mParseUser.getString("location");
    }

    public void setLocation(String location) {
        mParseUser.put("location", location);
    }

    /*
    public String getImageId() {
        return mParseUser.getString("imageId");
    }

    public void setImageId(String imageId) {
        mParseUser.put("imageId", imageId);
    }
    */


}

