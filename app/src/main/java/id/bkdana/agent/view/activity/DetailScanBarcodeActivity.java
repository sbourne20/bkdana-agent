package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.model.response.scanBarcodeResponse.DataBorrower;
import id.bkdana.agent.view.fragment.fragment_scanbarcode;

public class DetailScanBarcodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_selanjutnya_scanbarcode;
    private TextView tv_id_transaksi_scanbarcode, tv_product_scanbarcode, tv_nama_scanbarcode, tv_tenor_scanbarcode, tv_jmlpembayaran_scanbarcode;
    private ImageView iv_back_scanbarcodedetail;
    private ArrayList<DataBorrower> dataBorrower;
    private BKDanaAgentSession agentSession;
    private String idpan,idtrk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scan_barcode);

        dataBorrower = getIntent().getParcelableArrayListExtra("dataBorrower");

        tv_id_transaksi_scanbarcode = findViewById(R.id.tv_id_transaksi_scanbarcode);
        tv_product_scanbarcode = findViewById(R.id.tv_product_scanbarcode);
        tv_nama_scanbarcode = findViewById(R.id.tv_nama_scanbarcode);
        tv_tenor_scanbarcode = findViewById(R.id.tv_tenor_scanbarcode);
        tv_jmlpembayaran_scanbarcode = findViewById(R.id.tv_jmlpembayaran_scanbarcode);
        btn_selanjutnya_scanbarcode = findViewById(R.id.btn_selanjutnya_scanbarcode);
        iv_back_scanbarcodedetail = findViewById(R.id.iv_back_scanbarcodedetail);

        btn_selanjutnya_scanbarcode.setOnClickListener(this);
        iv_back_scanbarcodedetail.setOnClickListener(this);

//        Log.i("dd", "hasil: " + dataBorrower.getTotalPinjamanDiset);


        onSetData();

    }

    void onSetData(){

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


            for (int i = 0; i < dataBorrower.size(); i++) {
                idpan = dataBorrower.get(i).getIdPeminjam();
                idtrk = dataBorrower.get(i).getTransaksiId();
                tv_id_transaksi_scanbarcode.setText(dataBorrower.get(i).getTransaksiId());
                tv_product_scanbarcode.setText(dataBorrower.get(i).getProductTitle());
                tv_nama_scanbarcode.setText(dataBorrower.get(i).getNamaPeminjam());
                tv_tenor_scanbarcode.setText(dataBorrower.get(i).getLoanTerm() + " Bulan");
                if(dataBorrower.get(i).getTotalPinjamanDisetujui() != null) {
                    tv_jmlpembayaran_scanbarcode.setText(formatRupiah.format((double) Integer.parseInt(dataBorrower.get(i).getTotalPinjamanDisetujui())));
                }
        }



    }

    void onSendData(){
        Intent menuInput = new Intent(this,InputPenagihanActivity.class);
        Log.i("jancuk", "onSendData: " + idpan);
        menuInput.putExtra("idtrk",idtrk);
        menuInput.putExtra("jancuk", idpan);
        startActivity(menuInput);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_selanjutnya_scanbarcode :
                onSendData();
                break;
            case R.id.iv_back_scanbarcodedetail:
                Intent menuScan = new Intent(this,fragment_scanbarcode.class);
                startActivity(menuScan);
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent menuScan = new Intent(this,fragment_scanbarcode.class);
        startActivity(menuScan);
        finish();
    }
}
