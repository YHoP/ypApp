package com.epicodus.ypapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.epicodus.ypapp.R;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {

            goToLoginActivity();
            finish();
        } else {
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser == null) {
                goToLoginActivity();
                finish();
            } else {
                Toast.makeText(this, currentUser.getUsername(), Toast.LENGTH_LONG).show();
//                ParseObject testObject = new ParseObject("Routes");
//                testObject.put("name", "Eagle Creek run");
//                testObject.saveInBackground();
            }
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
            case  R.id.action_maps:
                Intent mapIntent = new Intent(this, MapsActivity.class);
                startActivity(mapIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
