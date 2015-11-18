package sql;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by YHoP on 10/27/15.
 */
@Table(name = "Routes", id = "_id")
public class Route extends Model {
    ParseObject mRouteObject;

    @Column(name = "name")
    private String mName;


    @Column(name = "location")
    private String mLocation;

    @Column(name = "distance")
    private Number mDistance;

    @Column(name = "startTime")
    private Date mStartTime;

    @Column(name = "finishTime")
    private Date mFinishTime;


    public Route(){
        super();
    }

    public Route(String name, String location, Number distance, Date startTime, Date finishTime){
        mName = name;
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

    public static List<Route> all(){
        return new Select()
                .from(Route.class)
                .execute();
    }

    public static Route find(String name) {
        return new Select()
                .from(Route.class)
                .where("name = ?", name)
                .executeSingle();
    }

    public static long getTimeDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
