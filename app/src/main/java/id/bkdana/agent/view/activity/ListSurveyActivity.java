package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import id.bkdana.agent.R;

public class ListSurveyActivity extends AppCompatActivity {

    private RecyclerView rv_survey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_survey);


        rv_survey = findViewById(R.id.rv_survey);




    }
}
