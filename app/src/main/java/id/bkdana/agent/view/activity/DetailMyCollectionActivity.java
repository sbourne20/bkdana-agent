package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.bkdana.agent.R;
import id.bkdana.agent.adapter.ListDetailMyCollectioAdapter;
import id.bkdana.agent.adapter.ListMySurveyAdapter;
import id.bkdana.agent.model.response.detailMyCollectionResponse.CatatanPenagihan;
import id.bkdana.agent.model.response.detailMyCollectionResponse.DetailMyCollectionResponse;
import id.bkdana.agent.view.bridge.DetailMyCollectionBridge;

public class DetailMyCollectionActivity extends AppCompatActivity implements DetailMyCollectionBridge<DetailMyCollectionResponse> {

    private TextView tv_id_detailmycollection, tv_nama_detailmycollection, tv_tenor_detailmycollection,
            tv_total_pinjam_detailmycollection, tv_sisa_hutang_detailmycollection;
    private RecyclerView rv_tagihan_detailmycollection;
    private ListDetailMyCollectioAdapter mAdapter;
    private List<CatatanPenagihan> datumList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_my_collection);

        tv_id_detailmycollection = findViewById(R.id.tv_id_detailmycollection);
        tv_nama_detailmycollection = findViewById(R.id.tv_nama_detailmycollection);
        tv_tenor_detailmycollection = findViewById(R.id.tv_tenor_detailmycollection);
        tv_total_pinjam_detailmycollection = findViewById(R.id.tv_total_pinjam_detailmycollection);
        tv_sisa_hutang_detailmycollection = findViewById(R.id.tv_sisa_hutang_detailmycollection);
        rv_tagihan_detailmycollection = findViewById(R.id.rv_tagihan_detailmycollection);
        rv_tagihan_detailmycollection.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_tagihan_detailmycollection.setLayoutManager(mLayoutManager);

        mAdapter = new ListDetailMyCollectioAdapter(this,datumList);
        rv_tagihan_detailmycollection.setAdapter(mAdapter);


    }

    @Override
    public void onSuccessDetailMyCollection(DetailMyCollectionResponse response) {
        tv_id_detailmycollection.setText(response.getContent().getSummaryPenagihan().getMasterLoanId());
        tv_total_pinjam_detailmycollection.setText(response.getContent().getSummaryPenagihan().getJmlTagihan());
        tv_sisa_hutang_detailmycollection.setText(response.getContent().getSummaryPenagihan().getSisaTagihan());

        for (int i = 0; i < response.getContent().getCatatanPenagihan().size() ; i++) {
            datumList.add(response.getContent().getCatatanPenagihan().get(i));
        }

    }

    @Override
    public void onFailureDetailMyCollection(String message) {

    }
}
