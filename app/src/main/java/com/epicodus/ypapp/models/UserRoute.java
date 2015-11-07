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
    public User mUser;

    @Column(name = "Route")
    public Route mRoute;

    public UserRoute() {super();}

    public UserRoute(User user, Route route){
        super();
        mUser = user;
        mRoute = route;
    }

}
