package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import id.bkdana.agent.R;

public class MenuDataPeminjamActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_selanjutnya_data_peminjam;
    private ImageView iv_back_survey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_peminjam);

        btn_selanjutnya_data_peminjam = findViewById(R.id.btn_selanjutnya_peminjam);
        iv_back_survey = findViewById(R.id.iv_back_survey);

        btn_selanjutnya_data_peminjam.setOnClickListener(this);
        iv_back_survey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_selanjutnya_peminjam :
                Intent data_usaha = new Intent(this,MenuDataUsahaActivity.class);
                startActivity(data_usaha);
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
