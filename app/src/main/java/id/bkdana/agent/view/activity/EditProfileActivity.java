package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import id.bkdana.agent.R;
import id.bkdana.agent.adapter.ExpandableEditProfileAdapter;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout ll_edit_informasi,ll_edit_password;
    private RelativeLayout rl_title_informasi,rl_title_password;
    private ImageView iv_back_editprofile;
    private boolean open = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ll_edit_informasi = findViewById(R.id.ll_editprofile_informasi);
        ll_edit_password = findViewById(R.id.ll_editprofile_password);
        rl_title_informasi = findViewById(R.id.rl_title_infromasi);
        rl_title_password = findViewById(R.id.rl_title_password);
        iv_back_editprofile = findViewById(R.id.iv_back_editprofile);

        rl_title_informasi.setOnClickListener(this);
        rl_title_password.setOnClickListener(this);
        iv_back_editprofile.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rl_title_infromasi :

                if(open){
                    ll_edit_informasi.setVisibility(View.VISIBLE);
                    open =false;
                } else {
                    ll_edit_informasi.setVisibility(View.GONE);
                    open = true;
                }

                break;

            case R.id.rl_title_password :
                if(open){
                    ll_edit_password.setVisibility(View.VISIBLE);
                    open =false;
                } else {
                    ll_edit_password.setVisibility(View.GONE);
                    open = true;
                }

                break;

            case  R.id.iv_back_editprofile :

                finish();

                break;
        }
    }
}
