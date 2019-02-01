package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

import id.bkdana.agent.R;
//import id.bkdana.agent.view.activity.;
import id.bkdana.agent.model.DataListSurvey;

public class ListMyCollection extends AppCompatActivity {

    private RecyclerView rv_collection;
    private RecyclerView.LayoutManager mLayoutManager;
//    private ListSurveyAdapter mAdapter;
    private ImageView iv_back_list_collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_collection);

        ArrayList<DataListSurvey> data = new ArrayList<>();

        iv_back_list_collection = findViewById(R.id.iv_back_list_collection);
        rv_collection = findViewById(R.id.rv_collection);
        rv_collection.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        rv_collection.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        data.add(new DataListSurvey("Dedi Kusniadi Saputra","No. PM-FB7FFCOA1234"," 3 Bulan","Rp 10 JT"));
        data.add(new DataListSurvey("Nurhadia","No. PM-FB7FFCOA1234"," 12 Bulan","Rp 20 JT"));
        data.add(new DataListSurvey("Aldo","No. PM-FB7FFCOA1234"," 6 Bulan","Rp 15 JT"));
        data.add(new DataListSurvey("Asep","No. PM-FB7FFCOA1234"," 10 Bulan","Rp 25 JT"));
        data.add(new DataListSurvey("Nanag","No. PM-FB7FFCOA1234"," 3 Bulan","Rp 5 JT"));

//        mAdapter = new ListSurveyAdapter(this,data,"0");
//        rv_collection.setAdapter(mAdapter);
    }
}
