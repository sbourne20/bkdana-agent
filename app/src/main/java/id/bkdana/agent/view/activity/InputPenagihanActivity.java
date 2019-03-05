package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.SubmitCollectionContract;
import id.bkdana.agent.model.response.submitCollectionResponse.SubmitCollectionReponse;
import id.bkdana.agent.presenter.SubmitCollectionPresenter;
import id.bkdana.agent.view.bridge.SubmitCollectionBridge;

public class InputPenagihanActivity extends AppCompatActivity implements SubmitCollectionBridge<SubmitCollectionReponse>, View.OnClickListener {

    private EditText et_borrowercode_inputpenagihan, et_jmltagihan_inputpenagihan, et_sisatagihan_inputpenagihan;
    private ImageView iv_back_inoutpenagihan;
    private Button btn_sebelumnya_inputpenagihan, btn_proses_penagihan;
    private BKDanaAgentSession agentSession;
    private SubmitCollectionContract submitCollectionContract;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    private String idUser,idModAgent,masterLoanId,jmlTgihan,sisaTghan,borrowCode,idtrk,total_pinjam,sisa_tagihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penagihan);


        idtrk = getIntent().getExtras().getString("idtrk");
        idUser = getIntent().getExtras().getString("jancuk");
        total_pinjam = getIntent().getExtras().getString("disetujui");
        sisa_tagihan = getIntent().getExtras().getString("sisaTagihan");

        Log.i("in", "ii: " + idUser + idtrk);

        agentSession = new BKDanaAgentSession(this);
        submitCollectionContract = new SubmitCollectionPresenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        et_borrowercode_inputpenagihan = findViewById(R.id.et_borrowercode_inputpenagihan);
        et_jmltagihan_inputpenagihan = findViewById(R.id.et_jmltagihan_inputpenagihan);
        et_sisatagihan_inputpenagihan = findViewById(R.id.et_sisatagihan_inputpenagihan);

        iv_back_inoutpenagihan = findViewById(R.id.iv_back_inoutpenagihan);
        btn_proses_penagihan = findViewById(R.id.btn_proses_penagihan);
        btn_sebelumnya_inputpenagihan = findViewById(R.id.btn_sebelumnya_inputpenagihan);

        iv_back_inoutpenagihan.setOnClickListener(this);
        btn_sebelumnya_inputpenagihan.setOnClickListener(this);
        btn_proses_penagihan.setOnClickListener(this);

        et_borrowercode_inputpenagihan.setText(idUser);
        et_sisatagihan_inputpenagihan.setText(sisa_tagihan);

        et_jmltagihan_inputpenagihan.setText(total_pinjam);

    }


    void sendData(){
        idModAgent = agentSession.getidMod();
        jmlTgihan = et_jmltagihan_inputpenagihan.getText().toString();
        sisaTghan = et_sisatagihan_inputpenagihan.getText().toString();
        borrowCode = et_borrowercode_inputpenagihan.getText().toString();

        if(et_jmltagihan_inputpenagihan.equals("")){
            et_jmltagihan_inputpenagihan.setError("Isi Terlebih Dahulu!");
        } else if(et_sisatagihan_inputpenagihan.equals("")){
            et_sisatagihan_inputpenagihan.setError("Isi Terlebih Dahulu!");
        } else if(et_borrowercode_inputpenagihan.equals("")){
            et_borrowercode_inputpenagihan.setError("Isi Terlebih Dahulu!");
        } else if(!borrowCode.equals(idUser)){
            et_borrowercode_inputpenagihan.setError("Borrower Code Salah");
        } else {
            customDialog();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_back_inoutpenagihan:
                finish();
                break;
            case R.id.btn_sebelumnya_inputpenagihan:
                finish();
                break;
            case R.id.btn_proses_penagihan:
                sendData();
                break;

        }
    }


    private void customDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);
        TextView tv_yes_dialog = dialogView.findViewById(R.id.tv_yes_dialog);
        TextView tv_no_dialog = dialogView.findViewById(R.id.tv_no_dialog);


        final AlertDialog alertDialog = builder.create();

        tv_yes_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    submitCollectionContract.postSubmitCollection(idUser,idModAgent,idtrk,jmlTgihan,sisaTghan,borrowCode);
                }  else if (isInternetPresent.equals(false)) {
                    Toast.makeText(InputPenagihanActivity.this, "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
                }

            }
        });

        tv_no_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();




    }

    @Override
    public void onSuccessSubmitCollection(SubmitCollectionReponse response) {
        Toast.makeText(this, response.getContent(), Toast.LENGTH_SHORT).show();
        Intent menumain = new Intent(this,MainActivity.class);
        startActivity(menumain);
        finish();

    }

    @Override
    public void onFailureSubmitCollection(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
