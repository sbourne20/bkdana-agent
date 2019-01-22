package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import id.bkdana.agent.R;
import id.bkdana.agent.model.DataListSurvey;

public class ListSurveyActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv_survey;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListSurveyAdapter mAdapter;
    private ImageView iv_back_list_survey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_survey);

        ArrayList<DataListSurvey> data = new ArrayList<>();

        iv_back_list_survey = findViewById(R.id.iv_back_list_survey);
        rv_survey = findViewById(R.id.rv_survey);
        rv_survey.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        rv_survey.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        data.add(new DataListSurvey("Dedi Kusniadi Saputra","No. PM-FB7FFCOA1234"," 3 Bulan","Rp 10 JT"));
        data.add(new DataListSurvey("Nurhadia","No. PM-FB7FFCOA1234"," 12 Bulan","Rp 20 JT"));
        data.add(new DataListSurvey("Aldo","No. PM-FB7FFCOA1234"," 6 Bulan","Rp 15 JT"));
        data.add(new DataListSurvey("Asep","No. PM-FB7FFCOA1234"," 10 Bulan","Rp 25 JT"));
        data.add(new DataListSurvey("Nanag","No. PM-FB7FFCOA1234"," 3 Bulan","Rp 5 JT"));

        mAdapter = new ListSurveyAdapter(this,data);
        rv_survey.setAdapter(mAdapter);

        iv_back_list_survey.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back_list_survey:
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

