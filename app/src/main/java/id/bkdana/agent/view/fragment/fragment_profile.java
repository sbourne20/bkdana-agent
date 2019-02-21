package id.bkdana.agent.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.view.activity.EditProfileActivity;
import id.bkdana.agent.view.activity.MainActivity;

public class fragment_profile extends AppCompatActivity implements View.OnClickListener {

    private BKDanaAgentSession agentSession;
    private TextView tv_nama_profile,tv_email_profile,tv_phone_profile;
    private Button btn_edit_profile;
    private ImageView iv_back_profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        agentSession = new BKDanaAgentSession(this);

        tv_nama_profile = findViewById(R.id.tv_nama_profile);
        tv_email_profile = findViewById(R.id.tv_email_profile);
        tv_phone_profile = findViewById(R.id.tv_phone_profile);
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        iv_back_profile = findViewById(R.id.iv_back_profile);

        tv_nama_profile.setText(agentSession.getFullname());
        tv_email_profile.setText(agentSession.getEmail());
        tv_phone_profile.setText(agentSession.getPhone());
        iv_back_profile.setOnClickListener(this);
        btn_edit_profile.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_edit_profile:
                Intent menuEditProfile = new Intent(this,EditProfileActivity.class);
                this.startActivity(menuEditProfile);
                break;

            case R.id.iv_back_profile :
                Intent menuMain = new Intent(this,MainActivity.class);
                this.startActivity(menuMain);
                this.finish();
        }

    }
}
