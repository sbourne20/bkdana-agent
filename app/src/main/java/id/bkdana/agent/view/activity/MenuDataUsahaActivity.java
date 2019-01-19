package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.bkdana.agent.R;

public class MenuDataUsahaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_selanjutnya_usaha, btn_sebelumnya_usaha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_usaha);

        btn_selanjutnya_usaha = findViewById(R.id.btn_selanjutnya_peminjam_usaha);
        btn_sebelumnya_usaha = findViewById(R.id.btn_sebelumnya_peminjam_usaha);

        btn_selanjutnya_usaha.setOnClickListener(this);
        btn_sebelumnya_usaha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_selanjutnya_peminjam_usaha :
                Intent data_upload = new Intent(this,MenuDataUploadActivity.class);
                startActivity(data_upload);
                break;
            case  R.id.btn_sebelumnya_peminjam_usaha :
                Intent data_usaha = new Intent(this,MenuDataUploadActivity.class);
                startActivity(data_usaha);
                break;
        }
    }
}
