package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.FormSurvey1Contract;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.model.response.listSurveyResponse.ListPeminjam;
import id.bkdana.agent.presenter.FormSurvey1Presenter;
import id.bkdana.agent.view.bridge.FormSurvey1Bridge;

public class MenuDataPersonalActivity extends AppCompatActivity implements FormSurvey1Bridge<FormSurveyResponse>, View.OnClickListener {

    private Button btn_selanjutnya_data_peminjam;
    private TextView tv_dataPersonal_masterLoadId, tv_dataPersonal_produktitle;
    private ImageView iv_back_survey;
    private EditText et_nama_peminjam, et_alamat_peminjam, et_noktp_peminjam;
    private String nama_peminjam,alamat_peminjam,noktp_peminjam,id_agent,id_peminjam,master_loan_id,product_title;
    private FormSurvey1Contract formSurvey1Contract;
    private BKDanaAgentSession agentSession;
    private ListPeminjam listPeminjam;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_personal);

        agentSession = new BKDanaAgentSession(this);
        formSurvey1Contract = new FormSurvey1Presenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        btn_selanjutnya_data_peminjam = findViewById(R.id.btn_selanjutnya_peminjam);
        et_nama_peminjam = findViewById(R.id.et_nama_peminjam);
        et_alamat_peminjam = findViewById(R.id.et_alamat_peminjam);
        et_noktp_peminjam = findViewById(R.id.et_noktp_peminjam);
        iv_back_survey = findViewById(R.id.iv_back_survey);
        tv_dataPersonal_masterLoadId = findViewById(R.id.tv_dataPersonal_masterLoadId);
        tv_dataPersonal_produktitle = findViewById(R.id.tv_dataPersonal_produktitle);

        onSetData();

        btn_selanjutnya_data_peminjam.setOnClickListener(this);
        iv_back_survey.setOnClickListener(this);


    }

    void onSetData(){

        id_agent = agentSession.getidMod();
        id_peminjam = getIntent().getStringExtra("intent_idPeminjam");
        master_loan_id = getIntent().getStringExtra("intent_masterLoadId");
        product_title = getIntent().getStringExtra("intent_productTitle");


        tv_dataPersonal_masterLoadId.setText(master_loan_id);
        tv_dataPersonal_produktitle.setText(product_title);
    }

    void onSendData(){
        nama_peminjam = et_nama_peminjam.getText().toString();
        alamat_peminjam = et_alamat_peminjam.getText().toString();
        noktp_peminjam = et_noktp_peminjam.getText().toString();

        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
           formSurvey1Contract.postFormSurvey1(id_agent,id_peminjam,master_loan_id,product_title,nama_peminjam,alamat_peminjam,noktp_peminjam);
        }  else if (isInternetPresent.equals(false)) {
            Toast.makeText(this, "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onSuccessFromSuervey1(FormSurveyResponse response) {
        Toast.makeText(this, response.getContent().getMessage(), Toast.LENGTH_SHORT).show();
        Intent data_usaha = new Intent(this,MenuDataUsahaActivity.class);
        data_usaha.putExtra("intent_idPeminjam", id_peminjam);
        data_usaha.putExtra("intent_masterLoadId", master_loan_id);
        data_usaha.putExtra("intent_productTitle", product_title);
        startActivity(data_usaha);
        finish();
    }

    @Override
    public void onFailureFromSurvey1(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_selanjutnya_peminjam :
                onSendData();
                break;
            case  R.id.iv_back_survey :
                    finish();
                    break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


}
