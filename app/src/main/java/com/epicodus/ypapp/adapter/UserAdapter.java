package com.epicodus.ypapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.ypapp.R;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by YHoP on 11/3/15.
 */
public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private List<ParseUser> mUsers;

    public UserAdapter(Context context, List<ParseUser> users) {
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

        ParseUser user = mUsers.get(position);

//        holder.userImage.setImageResource(user.getImageId());
        holder.userNameText.setText(user.getString("username"));
        holder.locationText.setText(user.getString("location"));
//        holder.routeCountText.setText(String.valueOf(routes.size()));

        return convertView;
    }

    private static class ViewHolder {
        ImageView userImage;
        TextView userNameText;
        TextView locationText;
        TextView routeCountText;
    }
}