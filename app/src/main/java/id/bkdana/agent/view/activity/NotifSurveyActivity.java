package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.bkdana.agent.R;

public class NotifSurveyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_selesai_survey_peminjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_survey);

        btn_selesai_survey_peminjam = findViewById(R.id.btn_selesai_survey_peminjam);

        btn_selesai_survey_peminjam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_selesai_survey_peminjam:
                Intent menuSelesai = new Intent(this, MainActivity.class);
                startActivity(menuSelesai);
                finish();
        }
    }
}
