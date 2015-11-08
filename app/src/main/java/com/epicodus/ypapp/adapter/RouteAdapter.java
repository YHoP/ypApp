package com.epicodus.ypapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.ypapp.R;
import com.epicodus.ypapp.models.Route_sql;

import java.util.ArrayList;

/**
 * Created by YHoP on 11/3/15.
 */
public class RouteAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Route_sql> mRoutes;

    public RouteAdapter(Context context, ArrayList<Route_sql> routes) {
        mContext = context;
        mRoutes = routes;
    }

    public Context getContext() {
        return mContext;
    }
    @Override
    public int getCount() {
        return mRoutes.size();
    }

    @Override
    public Object getItem(int position) {
        return mRoutes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.route_list_item, null);
            holder = new ViewHolder();
            holder.routeMapImage = (ImageView) convertView.findViewById(R.id.routeMapImage);
            holder.routeText = (TextView) convertView.findViewById(R.id.routeText);
            holder.dateText = (TextView) convertView.findViewById(R.id.dateText);
            holder.distanceText = (TextView) convertView.findViewById(R.id.distanceText);
            holder.totalTimeText = (TextView) convertView.findViewById(R.id.totalTimeText);
            holder.paceText = (TextView) convertView.findViewById(R.id.paceText);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Route_sql route = mRoutes.get(position);

//        holder.routeMapImage.setImageResource(route.getMapId());
        holder.routeText.setText(route.getName());
        holder.dateText.setText(route.getLocation());
        holder.distanceText.setText(String.valueOf(route.getDistance()));
//        holder.totalTimeText.setText(String.valueOf(route.getTotalTime()));
//        holder.paceText.setText(String.valueOf(route.getTotalTime()/route.getDistance()));

        return convertView;
    }

    private static class ViewHolder {
        ImageView routeMapImage;
        TextView routeText;
        TextView dateText;
        TextView distanceText;
        TextView totalTimeText;
        TextView paceText;
    }
}