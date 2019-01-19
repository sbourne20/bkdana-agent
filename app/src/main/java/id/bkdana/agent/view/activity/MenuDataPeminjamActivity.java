package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.bkdana.agent.R;

public class MenuDataPeminjamActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_selanjutnya_data_peminjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_peminjam);

        btn_selanjutnya_data_peminjam = findViewById(R.id.btn_selanjutnya_peminjam);

        btn_selanjutnya_data_peminjam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_selanjutnya_peminjam :
                Intent data_usaha = new Intent(this,MenuDataUsahaActivity.class);
                startActivity(data_usaha);
                break;

        }
    }
}
