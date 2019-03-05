package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.EndlessOnScrollListener;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.adapter.ListCollectionAdapter;
import id.bkdana.agent.contarct.ListMyCollectionContract;
import id.bkdana.agent.model.response.listMyCollectionResponse.ListMyCollectionResponse;
import id.bkdana.agent.model.response.listMyCollectionResponse.ListMycollection;
import id.bkdana.agent.presenter.ListMyCollectionPresenter;
import id.bkdana.agent.view.bridge.ListMyCollectionBridge;

public class ListMyCollectionActivity extends AppCompatActivity implements ListMyCollectionBridge<ListMyCollectionResponse>,View.OnClickListener {

    private RecyclerView rv_collection;
    private ListCollectionAdapter mAdapter;
    private ImageView iv_back_list_collection;
    private ListMyCollectionContract listMyCollectionContract;
    private EndlessOnScrollListener scrollListener;
    private int offset = 1;
    private BKDanaAgentSession agentSession;
    private List<ListMycollection> datumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_collection);

        agentSession = new BKDanaAgentSession(this);
        listMyCollectionContract = new ListMyCollectionPresenter(agentSession,this,this);

        iv_back_list_collection = findViewById(R.id.iv_back_list_collection);
        rv_collection = findViewById(R.id.rv_collection);
        rv_collection.setHasFixedSize(true);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_collection.setLayoutManager(mLayoutManager);

        listMyCollectionContract.getListMyCollection(agentSession.getidMod(),"0","10");

        mAdapter = new ListCollectionAdapter(this,datumList);
        rv_collection.setAdapter(mAdapter);

        scrollListener = new EndlessOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                offset = offset + 1;
                listMyCollectionContract.getListMyCollection(agentSession.getidMod(), String.valueOf(offset),"10");
                final int curSize = mAdapter.getItemCount();


                view.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyItemRangeInserted(curSize, datumList.size() - 1);
                    }
                });
            }
        };
        rv_collection.addOnScrollListener(scrollListener);

        iv_back_list_collection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back_list_collection:
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
    public void onSuccessListMyCollection(ListMyCollectionResponse response) {
        for (int i = 0; i < response.getContent().getListMycollection().size() ; i++) {
            datumList.add(response.getContent().getListMycollection().get(i));
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailureListMyCollection(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
