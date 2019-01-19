package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import id.bkdana.agent.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_email_login, et_password_login;
    private ImageView eye_open, eye_close;
    private Button btn_masuk_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email_login = findViewById(R.id.et_email_login);
        et_password_login = findViewById(R.id.et_password_login);
        btn_masuk_login = findViewById(R.id.btn_masuk_login);


        btn_masuk_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_masuk_login :
                Intent masuk_login = new Intent(this,MainActivity.class);
                startActivity(masuk_login);
        }
    }
}
