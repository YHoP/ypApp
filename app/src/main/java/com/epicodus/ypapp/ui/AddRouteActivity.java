package com.epicodus.ypapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.ypapp.R;
import com.epicodus.ypapp.models.Route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddRouteActivity extends AppCompatActivity {

    @Bind(R.id.editName) EditText mEditName;
    @Bind(R.id.editLocation) EditText mEditLocation;
    @Bind(R.id.editDistance) EditText mEditDistance;
    @Bind(R.id.editDate) EditText mEditDate;
    @Bind(R.id.editStartTime) EditText mEditStartTime;
    @Bind(R.id.editFinishTime) EditText mEditFinishTime;
    @Bind(R.id.btnSubmit) Button mBtnSubmit;

    Route mRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEditName.getText().toString();
                String location = mEditLocation.getText().toString();
                Double distance_double = Double.parseDouble(mEditDistance.getText().toString());
                Number distance = (Number)distance_double;
                Date inputDate = dateFormatter(mEditDate.getText().toString());
                Date startTime = timeFormatter(mEditStartTime.getText().toString());
                Date finishTime = timeFormatter(mEditFinishTime.getText().toString());

                Route thisRoute = new Route(name, location, distance, startTime, finishTime);

                // mEditStartTime.setIs24HourView(true);
                // mEditFinishTime.setIs24HourView(true);

                Intent intent = new Intent(AddRouteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private Date dateFormatter(String txtDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(txtDate);
        } catch (ParseException e) {
            Log.i("DateFormatter Error: ", e.toString());
            e.printStackTrace();
        }
        return date;
    }

    private Date timeFormatter(String txtTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = formatter.parse(txtTime);
        } catch (ParseException e) {
            Log.i("TimeFormatter Error: ", e.toString());
            e.printStackTrace();
        }
        return date;
    }



}
