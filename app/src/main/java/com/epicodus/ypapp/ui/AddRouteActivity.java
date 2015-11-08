package com.epicodus.ypapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.epicodus.ypapp.R;
import com.epicodus.ypapp.models.Route;
import com.parse.ParseACL;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddRouteActivity extends AppCompatActivity {

    @Bind(R.id.editName) EditText mEditName;
    @Bind(R.id.editLocation) EditText mEditLocation;
    @Bind(R.id.editDistance) EditText mEditDistance;
    @Bind(R.id.editDate) DatePicker mEditDate;
    @Bind(R.id.editStartTime) TimePicker mEditStartTime;
    @Bind(R.id.editFinishTime) TimePicker mEditFinishTime;
    @Bind(R.id.btnSubmit) Button mBtnSubmit;
    @Bind(R.id.imgRoute) ImageView mImgRoute;

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
                Number distance = (Number) distance_double;
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

        mImgRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent, 1);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data !=null) {
            Uri selectedImage = data.getData();

            try {
                Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                mImgRoute.setImageBitmap(bitmapImage);
                Log.i("Select image: ", String.valueOf(R.string.succeed));

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 20, stream);

                byte[] byteArray = stream.toByteArray();

                String imgName =  mRoute.getId() + ".png";
                ParseFile file = new ParseFile(imgName, byteArray);

                ParseObject object = new ParseObject("RouteImage");
                object.put("image", file);
                ParseACL parseACL = new ParseACL();
                parseACL.setPublicReadAccess(true);
                object.setACL(parseACL);

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if( e == null) {
                            Log.i("Save image", String.valueOf(R.string.succeed));
                        } else {
                            Toast.makeText(getApplication().getBaseContext(), "Error! Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            } catch (IOException e) {
                Toast.makeText(getApplication().getBaseContext(), "Error! Please try again", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

}
