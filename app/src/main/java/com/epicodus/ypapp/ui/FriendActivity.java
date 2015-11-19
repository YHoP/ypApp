package com.epicodus.ypapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.ypapp.R;
import com.epicodus.ypapp.adapter.UserAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendActivity extends AppCompatActivity {

    @Bind(R.id.listFriend) ListView mListFriend;
    ArrayList<String> mFriendName;
    ArrayAdapter<String> mArrayAdapter;
    private UserAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        mFriendName = new ArrayList<String>();
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFriendName);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", MainActivity.mUserName);
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> userFriends, ParseException e) {
                if (e == null) {
                    if (userFriends.size() > 0) {
                        for (ParseUser user : userFriends) {
                            mFriendName.add(user.getUsername());
                        }
                        mUserAdapter = new UserAdapter(FriendActivity.this, userFriends);
                        mListFriend.setAdapter(mArrayAdapter);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.action_friend:
                Intent friendIntent = new Intent(this, FriendActivity.class);
                startActivity(friendIntent);
                break;
            case  R.id.action_web:
                Uri heatMap = Uri.parse(getString(R.string.StravaHeatMapLink));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, heatMap);
                startActivity(webIntent);
                break;
            case  R.id.action_maps:
                Intent mapIntent = new Intent(this, MapsActivity.class);
                startActivity(mapIntent);
                break;
            case  R.id.action_logout:
                ParseUser.logOut();
                Intent logoutIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logoutIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
