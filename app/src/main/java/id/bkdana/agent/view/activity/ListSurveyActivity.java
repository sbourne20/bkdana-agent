package id.bkdana.agent.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.EndlessOnScrollListener;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.adapter.ListSurveyAdapter;
import id.bkdana.agent.contarct.ListSurveyContract;
import id.bkdana.agent.model.DataListSurvey;
import id.bkdana.agent.model.response.listSurveyResponse.ListPeminjam;
import id.bkdana.agent.model.response.listSurveyResponse.ListSurveyResponse;
import id.bkdana.agent.presenter.ListSurveyPresenter;
import id.bkdana.agent.view.bridge.ListSurveyBridge;

public class ListSurveyActivity extends AppCompatActivity implements ListSurveyBridge<ListSurveyResponse>, View.OnClickListener {

    private RecyclerView rv_survey;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListSurveyAdapter mAdapter;
    private ImageView iv_back_list_survey;
    private BKDanaAgentSession agentSession;
    private ListSurveyContract listSurveyContract;
    private EndlessOnScrollListener scrollListener;
    private int offset = 1;
    private List<ListPeminjam> datumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_survey);

        ArrayList<DataListSurvey> data = new ArrayList<>();

        agentSession = new BKDanaAgentSession(this);
        listSurveyContract = new ListSurveyPresenter(agentSession,this,this);
        iv_back_list_survey = findViewById(R.id.iv_back_list_survey);
        rv_survey = findViewById(R.id.rv_listsurvey);
        rv_survey.setHasFixedSize(true);

        listSurveyContract.getListSurvey("0","10");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_survey.setLayoutManager(mLayoutManager);
        mAdapter = new ListSurveyAdapter(this,datumList);
        rv_survey.setAdapter(mAdapter);

        scrollListener = new EndlessOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                offset = offset + 1;
                listSurveyContract.getListSurvey(String.valueOf(offset),"10");
                final int curSize = mAdapter.getItemCount();


                view.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyItemRangeInserted(curSize, datumList.size() - 1);
                    }
                });
            }
        };
        rv_survey.addOnScrollListener(scrollListener);

        iv_back_list_survey.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back_list_survey:
                Intent mainMenu = new Intent(this,MainActivity.class);
                startActivity(mainMenu);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainMenu = new Intent(this,MainActivity.class);
        startActivity(mainMenu);
        finish();
    }

    @Override
    public void onSuccessListSurvey(ListSurveyResponse response) {
        for (int i = 0; i < response.getContent().getListPeminjam().size() ; i++) {
            datumList.add(response.getContent().getListPeminjam().get(i));
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFailureListSurvey(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

