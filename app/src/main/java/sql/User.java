package sql;

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

@Table(name = "Users", id = "_id")
public class User extends Model{
    ParseUser mParseUser;

    @Column(name = "name")
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

        List<Route> routes = new ArrayList<Route>();

        for(UserRoute join : joins){
            Route route = join.getRoute();
            routes.add(route);
        }
        return routes;
    }
}

