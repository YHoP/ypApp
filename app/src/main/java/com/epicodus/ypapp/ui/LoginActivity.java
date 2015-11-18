package com.epicodus.ypapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epicodus.ypapp.R;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;
import sql.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private int stravaClientID = 8394;
    private String stravaClientSecret = "331b1f0d78a9d41311e865baa9e1fdfd2656a795";
    private String stravaCallBackDomain = "http://localhost:4200";
    private String URL = "https://www.strava.com/oauth/authorize?client_id=" + stravaClientID +
          "&response_type=code&redirect_uri=" + stravaCallBackDomain + "&approval_prompt=force";
    private String CODE = "234b31220041e56c4ee53a65be11e0a03266b20e";


    @Bind(R.id.txtUsername) EditText mTxtUsername;
    @Bind(R.id.txtPassword) EditText mTxtPassword;
    @Bind(R.id.btnSubmit) Button mBtnSubmit;
    @Bind(R.id.txtSubmit) TextView mTxtSubmit;
    @Bind(R.id.imgLogo) ImageView mImgLogo;
    @Bind(R.id.relativeLayout) RelativeLayout mRelativeLayout;
    @Bind(R.id.btnStravaLogIn) ImageButton mBtnStravaLogin;

    Boolean signUpModeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        signUpModeActive = true;
        mTxtSubmit.setOnClickListener(this);
        mTxtSubmit.setOnClickListener(this);
        mImgLogo.setOnClickListener(this);
        mRelativeLayout.setOnClickListener(this);

        mBtnStravaLogin.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.txtSubmit){

            Log.i("Log In Activity", "Change Sign Up Mode");
            if(signUpModeActive){
                signUpModeActive = false;
                mTxtSubmit.setText(R.string.sign_up);
                mBtnSubmit.setText(R.string.login);
            } else {
                signUpModeActive = true;
                mBtnSubmit.setText(R.string.login);
                mTxtSubmit.setText(R.string.sign_up);
            }
        } else if (v.getId() == R.id.imgLogo || v.getId() == R.id.relativeLayout){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);

        }

    }





    public class StravaAPI {

        AuthorisationService service = new AuthorisationServiceImpl();
        Token token = service.tokenExchange(stravaClientID, stravaClientSecret, CODE);
        Strava strava = new Strava(token);
        StravaAthlete athlete = strava.getAthlete(0);


       OkHttpClient client = new OkHttpClient();

        String doGetRequest(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
                Log.i("Get response: ", "Successful");
            } catch (IOException e) {
                Log.i("Get resposne: ", "Failed");
                e.printStackTrace();
            }


            String urlResponse = responses.body().toString();
            JSONObject jsonObject = null;
            String jsonName = null;

            try {
                jsonObject = new JSONObject(urlResponse);
                jsonName = jsonObject.getString("athlete");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("Response: ", jsonName);
            return urlResponse;
        }

        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String doPostRequest(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
            Response response = client.newCall(request).execute();
            return response.body().toString();
        }
    }


//    public void loginWithStrava (View view) throws IOException {
//        StravaAPI stravaAPI = new StravaAPI();
//        String getResponse = stravaAPI.doGetRequest(URL);
//        Log.i("Response: ", getResponse);
//
//        String postResponse = stravaAPI.doPostRequest("", getResponse);
//        Log.i("Response 2 : ", postResponse);
//
//    }




//    public void loginWithStrava (View view) throws IOException {
//        try {
//            StravaAPI(URL);
//            Log.e("Login", "Successful");
//        } catch (Exception e) {
//            Log.e("Login", "Failed");
//            e.printStackTrace();
//        }
//    }




//    public void run() throws Exception {
//
//        Request request = new Request.Builder()
//                .url("https://www.strava.com/oauth/authorize?client_id=" + stravaClientID +
//           "&response_type=code&redirect_uri=" + stravaCallBackDomain + "&approval_prompt=force")
//                .build();
//        Response response = client.newCall(request).execute();
//        if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//        Log.i("URL: ", response.body().toString());
//    }


    public void loginWithStrava (View view){
//        Uri uriUrl = Uri.parse(URL);
//        Intent stravaIntent = new Intent(Intent.ACTION_VIEW, uriUrl);
//        view.getContext().startActivity(stravaIntent);
//        Log.i("view contaxt", view.getContext().toString());

//        Intent stravaIntent = new Intent(this, WebViewActivity.class);
//        startActivity(stravaIntent);

    }

    public void signUpOrLogin(View view) {
        String username = String.valueOf(mTxtUsername.getText());
        String password = String.valueOf(mTxtPassword.getText());
        if(signUpModeActive) {
            com.epicodus.ypapp.models.User user = new com.epicodus.ypapp.models.User(username, password);
            User user_ = new User(username, password);
            user.signUp();
            goToMainActivity();
        } else {
            com.epicodus.ypapp.models.User.login(username, password);
            goToMainActivity();
        }

    }

    public void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
