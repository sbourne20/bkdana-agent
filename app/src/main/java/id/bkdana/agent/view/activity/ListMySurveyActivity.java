package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.EndlessOnScrollListener;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.adapter.ListMySurveyAdapter;
import id.bkdana.agent.contarct.ListMySurveyContract;
import id.bkdana.agent.model.response.listMySurveyResponse.ListMySurveyResponse;
import id.bkdana.agent.model.response.listMySurveyResponse.ListMysurvey;
import id.bkdana.agent.presenter.ListMySurveyPresenter;
import id.bkdana.agent.view.bridge.ListMySurveyBridge;

public class ListMySurveyActivity extends AppCompatActivity implements ListMySurveyBridge<ListMySurveyResponse>, View.OnClickListener {

    private RecyclerView rv_mysurvey;
    private ListMySurveyAdapter mAdapter;
    private ImageView iv_back_list_mysurvey;
    private BKDanaAgentSession agentSession;
    private ListMySurveyContract listMySurveyContract;
    private EndlessOnScrollListener scrollListener;
    private int offset = 1;
    private List<ListMysurvey> datumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_survey);

        agentSession = new BKDanaAgentSession(this);
        listMySurveyContract = new ListMySurveyPresenter(agentSession,this,this);

//        ArrayList<DataListSurvey> data = new ArrayList<>();

        iv_back_list_mysurvey = findViewById(R.id.iv_back_list_mysurvey);
        rv_mysurvey = findViewById(R.id.rv_mysurvey);
        rv_mysurvey.setHasFixedSize(true);


        listMySurveyContract.getListMySurvey(agentSession.getidMod(),"0","10");
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_mysurvey.setLayoutManager(mLayoutManager);

        mAdapter = new ListMySurveyAdapter(this,datumList);
        rv_mysurvey.setAdapter(mAdapter);

        scrollListener = new EndlessOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                offset = offset + 1;
                listMySurveyContract.getListMySurvey(agentSession.getidMod(),String.valueOf(offset),"10");
                final int curSize = mAdapter.getItemCount();


                view.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyItemRangeInserted(curSize, datumList.size() - 1);
                    }
                });
            }
        };
        rv_mysurvey.addOnScrollListener(scrollListener);


        iv_back_list_mysurvey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back_list_mysurvey:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    @Override
    public void onSuccessListMySurvey(ListMySurveyResponse response) {
        for (int i = 0; i < response.getContent().getListMysurvey().size() ; i++) {
            datumList.add(response.getContent().getListMysurvey().get(i));
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailureListMySurvey(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
