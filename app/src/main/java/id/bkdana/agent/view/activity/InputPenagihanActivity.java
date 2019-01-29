package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import id.bkdana.agent.R;

public class InputPenagihanActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back_inoutpenagihan;
    private Button btn_sebelumnya_inputpenagihan, btn_proses_penagihan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penagihan);

        iv_back_inoutpenagihan = findViewById(R.id.iv_back_inoutpenagihan);
        btn_proses_penagihan = findViewById(R.id.btn_proses_penagihan);
        btn_sebelumnya_inputpenagihan = findViewById(R.id.btn_sebelumnya_inputpenagihan);

        iv_back_inoutpenagihan.setOnClickListener(this);
        btn_sebelumnya_inputpenagihan.setOnClickListener(this);
        btn_proses_penagihan.setOnClickListener(this);
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
                Intent menumain = new Intent(this,MainActivity.class);
                startActivity(menumain);
                finish();
        }
    }
}
