package id.bkdana.agent.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.FormSurvey2Contract;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.presenter.FormSurvey2Presenter;
import id.bkdana.agent.view.bridge.FormSurvey2Bridge;

public class MenuDataUsahaActivity extends AppCompatActivity implements FormSurvey2Bridge<FormSurveyResponse>, View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private Button btn_selanjutnya_data_peminjam;
    ImageButton btn_upload_foto_udaha_peminjam;
    private ImageView iv_back_survey,iv_upload_foto_usaha_peminjam;
    private TextView tv_dataUsaha_masterLoadId, tv_dataUsaha_produktitle;
    private EditText et_alamat_usaha_peminjam, et_jenis_usaha_peminjam;
    private String id_agent,id_peminjam,master_loan_id,product_title,alamatUsaha,jenisUsaha;
    private Long last_id;
    private BKDanaAgentSession agentSession;
    private FormSurvey2Contract formSurvey2Contract;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    private  Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_usaha);

        agentSession = new BKDanaAgentSession(this);
        formSurvey2Contract = new FormSurvey2Presenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        btn_selanjutnya_data_peminjam = findViewById(R.id.btn_selanjutnya_peminjam);
        iv_back_survey = findViewById(R.id.iv_back_survey);
        tv_dataUsaha_masterLoadId = findViewById(R.id.tv_dataUsaha_masterLoadId);
        tv_dataUsaha_produktitle = findViewById(R.id.tv_dataUsaha_produktitle);
        et_alamat_usaha_peminjam = findViewById(R.id.et_alamat_usaha_peminjam);
        et_jenis_usaha_peminjam = findViewById(R.id.et_jenis_usaha_peminjam);
        btn_upload_foto_udaha_peminjam = findViewById(R.id.btn_upload_foto_udaha_peminjam);
        iv_upload_foto_usaha_peminjam = findViewById(R.id.iv_upload_foto_usaha_peminjam);

        btn_upload_foto_udaha_peminjam.setOnClickListener(this);
        btn_selanjutnya_data_peminjam.setOnClickListener(this);
        iv_back_survey.setOnClickListener(this);

        onSetData();

    }

    void onSetData(){

        id_peminjam = getIntent().getStringExtra("intent_idPeminjam");
        master_loan_id = getIntent().getStringExtra("intent_masterLoadId");
        product_title = getIntent().getStringExtra("intent_productTitle");
        last_id = getIntent().getExtras().getLong("last_id");
        Log.i("koc", "onSetData: " + last_id);

        tv_dataUsaha_produktitle.setText(product_title);
        tv_dataUsaha_masterLoadId.setText(master_loan_id);
    }

    void onCamera(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            return;
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    void onSendData(){

        alamatUsaha = et_alamat_usaha_peminjam.getText().toString();
        jenisUsaha = et_alamat_usaha_peminjam.getText().toString();

        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            formSurvey2Contract.postFormSurvey2(String.valueOf(last_id),alamatUsaha,jenisUsaha,imageBitmap);
        }  else if (isInternetPresent.equals(false)) {
            Toast.makeText(this, "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case  R.id.btn_upload_foto_udaha_peminjam :
                onCamera();
                break;
            case R.id.btn_selanjutnya_peminjam :
                onSendData();
                break;
            case  R.id.iv_back_survey :
                finish();
                break;
        }
    }


    @Override
    public void onSuccessFromSuervey2(FormSurveyResponse response) {
        Toast.makeText(this, response.getResponse(), Toast.LENGTH_SHORT).show();
        Intent data_usaha = new Intent(this,MenuDataKeuanganUsahaActivity.class);
        data_usaha.putExtra("intent_idPeminjam", id_peminjam);
        data_usaha.putExtra("intent_masterLoadId", master_loan_id);
        data_usaha.putExtra("intent_productTitle", product_title);
        data_usaha.putExtra("last_id",last_id);
        startActivity(data_usaha);
        finish();
    }

    @Override
    public void onFailureFromSurvey2(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            if(imageBitmap!=null){
                iv_upload_foto_usaha_peminjam.setVisibility(View.VISIBLE);
                btn_upload_foto_udaha_peminjam.setVisibility(View.GONE);
                iv_upload_foto_usaha_peminjam.setImageBitmap(imageBitmap);
            }

            Log.i("photo", "onActivityResult: "  + extras);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


}
