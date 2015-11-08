package com.epicodus.ypapp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


/**
 * Created by YHoP on 11/3/15.
 */

@Table(name = "User_Route", id = "_id")
public class UserRoute extends Model{
    @Column(name = "Runner")
    public User_sql mUser;

    @Column(name = "Route")
    public Route_sql mRoute;

    public UserRoute() {super();}

    public UserRoute(User_sql user, Route_sql route){
        super();
        mUser = user;
        mRoute = route;
    }

    public User_sql getUser() {
        return mUser;
    }

    public Route_sql getRoute() {
        return mRoute;
    }
}
