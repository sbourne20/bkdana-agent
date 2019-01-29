package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import id.bkdana.agent.R;
import id.bkdana.agent.view.fragment.fragment_scanbarcode;

public class DetailScanBarcodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_selanjutnya_scanbarcode;
    private ImageView iv_back_scanbarcodedetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scan_barcode);

        btn_selanjutnya_scanbarcode = findViewById(R.id.btn_selanjutnya_scanbarcode);
        iv_back_scanbarcodedetail = findViewById(R.id.iv_back_scanbarcodedetail);

        btn_selanjutnya_scanbarcode.setOnClickListener(this);
        iv_back_scanbarcodedetail.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_selanjutnya_scanbarcode :

                Intent menuInput = new Intent(this,InputPenagihanActivity.class);
                startActivity(menuInput);
                break;
            case R.id.iv_back_scanbarcodedetail:
                Intent menuScan = new Intent(this,fragment_scanbarcode.class);
                startActivity(menuScan);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent menuScan = new Intent(this,fragment_scanbarcode.class);
        startActivity(menuScan);
    }
}
