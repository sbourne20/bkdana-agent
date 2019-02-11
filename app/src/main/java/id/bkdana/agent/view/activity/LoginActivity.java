package id.bkdana.agent.view.activity;

import android.content.Intent;
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
