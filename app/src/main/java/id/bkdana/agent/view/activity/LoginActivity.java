package id.bkdana.agent.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.LoginContarct;
import id.bkdana.agent.model.response.loginResponse;
import id.bkdana.agent.model.response.profileResponse.ProfileResponse;
import id.bkdana.agent.presenter.LoginPresenter;
import id.bkdana.agent.view.bridge.LoginBridge;

public class LoginActivity extends AppCompatActivity implements LoginBridge<loginResponse,ProfileResponse>, View.OnClickListener {

    private EditText et_email_login, et_password_login;
    private ImageView eye_open, eye_close;
    private Button btn_masuk_login;
    private LoginContarct loginContarct;
    private BKDanaAgentSession agentSession;
    private   String Username,Password;
    private static final int REQUEST_CAMERA_PERMISSION = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        agentSession = new BKDanaAgentSession(this);
        loginContarct = new LoginPresenter(agentSession,this,this);

        et_email_login = findViewById(R.id.et_email_login);
        et_password_login = findViewById(R.id.et_password_login);
        btn_masuk_login = findViewById(R.id.btn_masuk_login);
        btn_masuk_login.setOnClickListener(this);


        et_email_login.setText("iriawan.maarif@gmail.com");
        et_password_login.setText("MD123123aabb");



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,},
                        REQUEST_CAMERA_PERMISSION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

        if (!agentSession.getAutorization().equals("")){
            Intent i = new Intent(this,MainActivity.class);
            finish();
            startActivity(i);
        }
    }

    void onLogin(){
        et_email_login.setError(null);
        et_password_login.setError(null);

        Username = et_email_login.getText().toString();
        Password = et_password_login.getText().toString();

        if(et_email_login.equals("") || et_password_login.equals("")){
            Toast.makeText(this, "Username dan Password Harap di isi", Toast.LENGTH_SHORT).show();
        } else if (et_email_login.equals("")) {
            et_email_login.setError("Harap isi Username");
        } else if(et_password_login.equals("")){
            et_password_login.setError("Harap isi Password");
        } else {
            loginContarct.postlogin(Username,Password);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_masuk_login :
                onLogin();
        }
    }



    @Override
    public void onSuccessLogin(loginResponse response) {
        loginContarct.postprofile(response.getToken());

    }

    @Override
    public void onSuccessProfile(ProfileResponse response) {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        Intent masuk_login = new Intent(this,MainActivity.class);
        startActivity(masuk_login);
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
