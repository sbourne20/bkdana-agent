package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.DetailSurveyContract;
import id.bkdana.agent.model.response.detailSurveyResponse.DataSurvey;
import id.bkdana.agent.model.response.detailSurveyResponse.DetailSurveyResponse;
import id.bkdana.agent.presenter.DetailMySurveyPresenter;
import id.bkdana.agent.view.bridge.DetailMySurveyBridge;

public class DetailSurveyActivity extends AppCompatActivity implements DetailMySurveyBridge<DetailSurveyResponse>, View.OnClickListener {

    private TextView tv_id_transaksi_detailmysurvey, tv_id_produk_detailmysurvey, tv_nama_deatilmysurvey,
            tv_alamat_detailmysurvey, tv_ktp_detailmysurvey, tv_jenis_detailsurvey, tv_alamat_usaha_detailsurvey,
            tv_omset_detailsurvey, tv_biaya_detailsurvey, tv_laba_detailsurvey;
    private ImageView iv_img_detailMysurvey, iv_back_detailmysurvey;
    private String id_transaksi_detailmysurvey, produk_detailmysurvey, nama_detailmysurvey, alamat_detailmysurvey,
            ktp_detailmysurvey, jenis_detailmysurvey, alamat_usaha_detailmysurvey, omset_detailmysurvey,biaya_detailmysurvey,
            laba_detailmysurvey, id_mod_agent_detail;

    private BKDanaAgentSession agentSession;
    private DetailSurveyContract detailSurveyContract;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_survey);

        id_mod_agent_detail = getIntent().getStringExtra("detail_id");
        agentSession = new BKDanaAgentSession(this);
        detailSurveyContract = new DetailMySurveyPresenter(agentSession,this,this);

        iv_back_detailmysurvey = findViewById(R.id.iv_back_detailmysurvey);
       tv_id_transaksi_detailmysurvey = findViewById(R.id.tv_id_transaksi_detailmysurvey);
        tv_id_produk_detailmysurvey = findViewById(R.id.tv_id_produk_detailmysurvey);
        tv_nama_deatilmysurvey = findViewById(R.id.tv_nama_deatilmysurvey);
        tv_ktp_detailmysurvey = findViewById(R.id.tv_ktp_detailmysurvey);
        tv_alamat_detailmysurvey = findViewById(R.id.tv_alamat_detailmysurvey);
        tv_jenis_detailsurvey = findViewById(R.id.tv_jenis_detailsurvey);
        tv_alamat_usaha_detailsurvey = findViewById(R.id.tv_alamat_usaha_detailsurvey);
        tv_omset_detailsurvey = findViewById(R.id.tv_omset_detailsurvey);
        tv_biaya_detailsurvey = findViewById(R.id.tv_biaya_detailsurvey);
        tv_laba_detailsurvey = findViewById(R.id.tv_laba_detailsurvey);


        detailSurveyContract.postDetailMySurvey(id_mod_agent_detail);

        iv_back_detailmysurvey.setOnClickListener(this);
    }

    @Override
    public void onSuccessDetailMySurvey(DetailSurveyResponse response) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        List<DataSurvey> dataSurveys = new ArrayList<>();
        for (int i = 0; i < response.getContent().getDataSurvey().size(); i++) {
            dataSurveys.add(response.getContent().getDataSurvey().get(i));

            tv_id_transaksi_detailmysurvey.setText(dataSurveys.get(i).getMasterLoanId());
            tv_id_produk_detailmysurvey.setText(dataSurveys.get(i).getProductTitle());
            tv_nama_deatilmysurvey.setText(dataSurveys.get(i).getNama());
            tv_ktp_detailmysurvey.setText(dataSurveys.get(i).getNoKtp());
            tv_alamat_detailmysurvey.setText(dataSurveys.get(i).getAlamat());
            tv_jenis_detailsurvey.setText(dataSurveys.get(i).getJenisUsaha());
            tv_alamat_usaha_detailsurvey.setText(dataSurveys.get(i).getAlamatUsaha());
            tv_omset_detailsurvey.setText(formatRupiah.format((double)Integer.parseInt(dataSurveys.get(i).getOmset())));
            tv_biaya_detailsurvey.setText(formatRupiah.format((double)Integer.parseInt(dataSurveys.get(i).getBiaya())));
            tv_laba_detailsurvey.setText(formatRupiah.format((double)Integer.parseInt(dataSurveys.get(i).getLaba())));
        }



        Toast.makeText(this, response.getResponse(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailureDetailMySurvey(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back_detailmysurvey :
                finish();
                break;
        }
    }
}
