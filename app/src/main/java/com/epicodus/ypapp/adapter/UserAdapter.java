package com.epicodus.ypapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.ypapp.R;

import java.util.ArrayList;
import java.util.List;

import sql.Route;
import sql.User;

/**
 * Created by YHoP on 11/3/15.
 */
public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<User> mUsers;

    public UserAdapter(Context context, ArrayList<User> users) {
        mContext = context;
        mUsers = users;
    }

    public Context getContext() {
        return mContext;
    }
    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.user_list_item, null);
            holder = new ViewHolder();
            holder.userImage = (ImageView) convertView.findViewById(R.id.userImage);
            holder.userNameText = (TextView) convertView.findViewById(R.id.txtUserName);
            holder.locationText = (TextView) convertView.findViewById(R.id.locationText);
            holder.routeCountText = (TextView) convertView.findViewById(R.id.routeCountText);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = mUsers.get(position);
        List<Route> routes = user.getRoutes();

//        holder.userImage.setImageResource(user.getImageId());
        holder.userNameText.setText(user.getUserName());
        holder.locationText.setText(user.getLocation());
        holder.routeCountText.setText(String.valueOf(routes.size()));

        return convertView;
    }

    private static class ViewHolder {
        ImageView userImage;
        TextView userNameText;
        TextView locationText;
        TextView routeCountText;
    }
}