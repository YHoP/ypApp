package com.epicodus.ypapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.ypapp.R;
import com.parse.FindCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.txtName) TextView mTxtUserName;
    @Bind(R.id.txtLocation) TextView mTxtLocation;
    @Bind(R.id.txtLastRun) TextView mTxtLastRun;
    @Bind(R.id.listRoute) ListView mListRoute;

    ArrayList<String> mRouteName;
    ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListRoute = (ListView) findViewById(R.id.listRoute);
        mRouteName = new ArrayList<String>();
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mRouteName);

        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {

            goToLoginActivity();
            finish();
        } else {
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser == null) {
                goToLoginActivity();
                finish();
            } else {
                String userName = currentUser.getString("username");
                Toast.makeText(this, "Hello " + userName, Toast.LENGTH_LONG).show();

                mTxtUserName.setText(userName);

            }
        }


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Routes");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> allRoutes, ParseException e) {
                if (e == null) {

                    for(ParseObject route : allRoutes){
                        mRouteName.add(route.getString("name"));
                    }
                    mListRoute.setAdapter(mArrayAdapter);

                } else {
                    Toast.makeText(MainActivity.this, "Error - please try again", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRouteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
            case  R.id.action_web:
                Uri heatMap = Uri.parse("http://labs.strava.com/heatmap/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, heatMap);
                startActivity(webIntent);
                break;
            case  R.id.action_maps:
                Intent mapIntent = new Intent(this, MapsActivity.class);
                startActivity(mapIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
