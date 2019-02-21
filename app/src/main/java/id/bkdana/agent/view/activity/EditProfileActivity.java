package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.adapter.ExpandableEditProfileAdapter;
import id.bkdana.agent.contarct.UpdateProfileContract;
import id.bkdana.agent.model.response.UpdateProfileResponse;
import id.bkdana.agent.model.response.profileResponse.ProfileResponse;
import id.bkdana.agent.presenter.UpdateProfilePresenter;
import id.bkdana.agent.view.bridge.UpdateProfileBridge;
import id.bkdana.agent.view.fragment.fragment_profile;
import id.bkdana.agent.view.fragment.fragment_scanbarcode;

public class EditProfileActivity extends AppCompatActivity implements UpdateProfileBridge<UpdateProfileResponse,UpdateProfileResponse,ProfileResponse>, View.OnClickListener {


    private LinearLayout ll_edit_informasi,ll_edit_password;
    private RelativeLayout rl_title_informasi,rl_title_password;
    private TextView et_nama_editprofile,et_email_editprofile,et_phone_editprofile,et_old_password_editprofile,
            et_new_password_editprofile,et_confrim_password_editprofile;
    private ImageView iv_back_editprofile, btn_eye_visible_old, btn_eye_invisible_old, btn_eye_visible_new, btn_eye_invisible_new,
            btn_eye_visible_conf, btn_eye_invisible_conf;
    private Button btn_update_informasi_editprofile, btn_update_password_editprofile;
    private boolean open = false;
    private BKDanaAgentSession agentSession;
    private UpdateProfileContract updateProfileContract;
    private ConnectionDetector cd;
    private String  fullNameIn,emailIn,phoneIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        agentSession = new BKDanaAgentSession(this);
        updateProfileContract = new UpdateProfilePresenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        fullNameIn = agentSession.getFullname();
        emailIn = agentSession.getEmail();
        phoneIn = agentSession.getPhone();

        ll_edit_informasi = findViewById(R.id.ll_editprofile_informasi);
        ll_edit_password = findViewById(R.id.ll_editprofile_password);
        rl_title_informasi = findViewById(R.id.rl_title_infromasi);
        rl_title_password = findViewById(R.id.rl_title_password);
        iv_back_editprofile = findViewById(R.id.iv_back_editprofile);
        et_nama_editprofile = findViewById(R.id.et_nama_editprofile);
        et_email_editprofile = findViewById(R.id.et_email_editprofile);
        et_phone_editprofile = findViewById(R.id.et_phone_editprofile);
        et_old_password_editprofile = findViewById(R.id.et_old_password_editprofile);
        et_new_password_editprofile = findViewById(R.id.et_new_password_editprofile);
        et_confrim_password_editprofile = findViewById(R.id.et_confrim_password_editprofile);
        btn_eye_visible_old = findViewById(R.id.btn_eye_visible_old);
        btn_eye_invisible_old = findViewById(R.id.btn_eye_invisible_old);
        btn_eye_visible_new = findViewById(R.id.btn_eye_visible_new);
        btn_eye_invisible_new = findViewById(R.id.btn_eye_invisible_new);
        btn_eye_visible_conf = findViewById(R.id.btn_eye_visible_conf);
        btn_eye_invisible_conf = findViewById(R.id.btn_eye_invisible_conf);
        btn_update_informasi_editprofile = findViewById(R.id.btn_update_informasi_editprofile);
        btn_update_password_editprofile = findViewById(R.id.btn_update_password_editprofile);

        rl_title_informasi.setOnClickListener(this);
        rl_title_password.setOnClickListener(this);
        iv_back_editprofile.setOnClickListener(this);
        btn_eye_visible_old.setOnClickListener(this);
        btn_eye_invisible_old.setOnClickListener(this);
        btn_eye_visible_new.setOnClickListener(this);
        btn_eye_invisible_new.setOnClickListener(this);
        btn_eye_visible_conf.setOnClickListener(this);
        btn_eye_invisible_conf.setOnClickListener(this);
        btn_update_informasi_editprofile.setOnClickListener(this);
        btn_update_password_editprofile.setOnClickListener(this);

        onSetData();

    }

    private void onSendDataPassword() {
        et_old_password_editprofile.setError(null);
        et_new_password_editprofile.setError(null);
        et_confrim_password_editprofile.setError(null);

        String oldPass = et_old_password_editprofile.getText().toString();
        String newPass = et_new_password_editprofile.getText().toString();
        String confPass = et_confrim_password_editprofile.getText().toString();

        if(oldPass.isEmpty() && newPass.isEmpty() && confPass.isEmpty()){
            et_old_password_editprofile.setError("Harap isi Terlebih dahulu!");
            et_new_password_editprofile.setError("Harap isi Terlebih dahulu!");
            et_confrim_password_editprofile.setError("Harap isi Terlebih dahulu!");
        } else if(oldPass.isEmpty()){
            et_old_password_editprofile.setError("Harap isi Terlebih dahulu!");
        } else if (newPass.isEmpty()) {
            et_new_password_editprofile.setError("Harap isi Terlebih dahulu!");
        } else if(confPass.isEmpty()){
            et_confrim_password_editprofile.setError("Harap isi Terlebih dahulu!");
        } else if(newPass == oldPass){
            Toast.makeText(this, "Password Tidak Boleh Sama dengan yang lama", Toast.LENGTH_SHORT).show();
        } else {
            updateProfileContract.postUpdatePasswordAkun(oldPass,newPass,confPass);
        }

    }

    private void onSendDataInformasi() {
        et_nama_editprofile.setError(null);
        et_phone_editprofile.setError(null);

        String nameEdit = et_nama_editprofile.getText().toString();
        String phoneEdit = et_phone_editprofile.getText().toString();

        if(nameEdit.isEmpty() && phoneEdit.isEmpty()){
            et_nama_editprofile.setError("Harap isi Nama dan No Telfon dahulu!");
            et_phone_editprofile.setError("Harap isi Nama dan No Telfon dahulu!");
        } else if(nameEdit.isEmpty()) {
            et_nama_editprofile.setError("Harap isi Nama dahulu!");
        } else if (phoneEdit.isEmpty()) {
            et_phone_editprofile.setError("Harap isi No Telfon dahulu!");
        } else {
            updateProfileContract.postUpdateInformasiAkun(nameEdit,phoneEdit);
        }
    }

    private void onSetData() {

        et_nama_editprofile.setText(fullNameIn);
        et_email_editprofile.setText(emailIn);
        et_phone_editprofile.setText(phoneIn);

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

                updateProfileContract.postUpdateprofile(agentSession.getAutorization());

                break;

            case R.id.btn_eye_visible_old :
                et_old_password_editprofile.setInputType(InputType.TYPE_CLASS_TEXT);
                btn_eye_invisible_old.setVisibility(View.VISIBLE);
                btn_eye_visible_old.setVisibility(View.GONE);
                break;

            case  R.id.btn_eye_invisible_old :
                et_old_password_editprofile.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btn_eye_invisible_old.setVisibility(View.GONE);;
                btn_eye_visible_old.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_eye_visible_new :
                et_new_password_editprofile.setInputType(InputType.TYPE_CLASS_TEXT);
                btn_eye_invisible_new.setVisibility(View.VISIBLE);
                btn_eye_visible_new.setVisibility(View.GONE);
                break;
            case R.id.btn_eye_invisible_new :
                et_new_password_editprofile.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btn_eye_invisible_new.setVisibility(View.GONE);;
                btn_eye_visible_new.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_eye_visible_conf :
                et_confrim_password_editprofile.setInputType(InputType.TYPE_CLASS_TEXT);
                btn_eye_invisible_conf.setVisibility(View.VISIBLE);
                btn_eye_visible_conf.setVisibility(View.GONE);
                break;
            case R.id.btn_eye_invisible_conf :
                et_confrim_password_editprofile.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btn_eye_invisible_conf.setVisibility(View.GONE);;
                btn_eye_visible_conf.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_update_informasi_editprofile :
                onSendDataInformasi();
                break;

            case R.id.btn_update_password_editprofile :
                onSendDataPassword();
                break;
        }
    }

    @Override
    public void onSuccessUpdateInformasiAkun(UpdateProfileResponse response) {
        customDialogInformasi(response.getMessage());
    }

    @Override
    public void onSuccessUpdatePasswordAkun(UpdateProfileResponse response) {
        customDialog(response.getMessage());
    }

    @Override
    public void onSuccesUpdateSessionProfile(ProfileResponse response) {
        Intent menuScan = new Intent(this,fragment_profile.class);
        startActivity(menuScan);
        finish();
    }

    @Override
    public void onFailureUpdateProfile(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        updateProfileContract.postUpdateprofile(agentSession.getAutorization());
    }

    private void customDialog(String isi){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);
        TextView title_dialog = dialogView.findViewById(R.id.title_dialog);
        TextView tv_isi_dialog = dialogView.findViewById(R.id.txt_isi_dialog);
        TextView tv_yes_dialog = dialogView.findViewById(R.id.tv_yes_dialog);
        TextView tv_no_dialog = dialogView.findViewById(R.id.tv_no_dialog);


        final AlertDialog alertDialog = builder.create();
        title_dialog.setText("Pemberitahuan");
        tv_isi_dialog.setText(isi);
        tv_yes_dialog.setVisibility(View.GONE);
        tv_no_dialog.setText("OK");
        tv_no_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void customDialogInformasi(String isi){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);
        TextView title_dialog = dialogView.findViewById(R.id.title_dialog);
        TextView tv_isi_dialog = dialogView.findViewById(R.id.txt_isi_dialog);
        TextView tv_yes_dialog = dialogView.findViewById(R.id.tv_yes_dialog);
        TextView tv_no_dialog = dialogView.findViewById(R.id.tv_no_dialog);


        final AlertDialog alertDialog = builder.create();
        title_dialog.setText("Pemberitahuan");
        tv_isi_dialog.setText(isi);
        tv_yes_dialog.setVisibility(View.GONE);
        tv_no_dialog.setText("OK");
        tv_no_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfileContract.postUpdateprofile(agentSession.getAutorization());
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();


    }
}
