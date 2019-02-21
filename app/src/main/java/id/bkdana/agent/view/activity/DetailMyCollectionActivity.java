package id.bkdana.agent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.adapter.ListDetailMyCollectioAdapter;
import id.bkdana.agent.adapter.ListMySurveyAdapter;
import id.bkdana.agent.contarct.DetailMyCollectionContract;
import id.bkdana.agent.model.response.detailMyCollectionResponse.CatatanPenagihan;
import id.bkdana.agent.model.response.detailMyCollectionResponse.DetailMyCollectionResponse;
import id.bkdana.agent.presenter.DetailMyCollectionPresenter;
import id.bkdana.agent.view.bridge.DetailMyCollectionBridge;

public class DetailMyCollectionActivity extends AppCompatActivity implements DetailMyCollectionBridge<DetailMyCollectionResponse>, View.OnClickListener {

    private TextView tv_id_detailmycollection, tv_nama_detailmycollection, tv_tenor_detailmycollection,
            tv_total_pinjam_detailmycollection, tv_sisa_hutang_detailmycollection;
    private RecyclerView rv_tagihan_detailmycollection;
    private ImageView iv_back_detailmycollection;
    private ListDetailMyCollectioAdapter mAdapter;
    private String idModAgent;
    private ConnectionDetector cd;
    private DetailMyCollectionContract detailMyCollectionContract;
    private BKDanaAgentSession agentSession;
    private Boolean isInternetPresent = false;
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
        iv_back_detailmycollection = findViewById(R.id.iv_back_detailmycollection);
        rv_tagihan_detailmycollection.setHasFixedSize(true);

        idModAgent = getIntent().getExtras().getString("masted_id");

        agentSession = new BKDanaAgentSession(this);
        detailMyCollectionContract = new DetailMyCollectionPresenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            detailMyCollectionContract.postDetailMycontract(idModAgent);
        }  else if (isInternetPresent.equals(false)) {
            Toast.makeText(this, "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
        }


        iv_back_detailmycollection.setOnClickListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_tagihan_detailmycollection.setLayoutManager(mLayoutManager);
        mAdapter = new ListDetailMyCollectioAdapter(this,datumList);
        rv_tagihan_detailmycollection.setAdapter(mAdapter);

//        List<CatatanPenagihan> data  = new ArrayList<>();
//        data.add(new CatatanPenagihan("1000000","pM","2019-02-16 07:10:41"));






    }

    @Override
    public void onSuccessDetailMyCollection(DetailMyCollectionResponse response) {


        for (int i = 0; i < response.getContent().getCatatanPenagihan().size() ; i++) {
            datumList.add(response.getContent().getCatatanPenagihan().get(i));
            mAdapter.notifyDataSetChanged();
        }

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        tv_nama_detailmycollection.setText(response.getContent().getSummaryPenagihan().getmNamaPengguna());
        tv_tenor_detailmycollection.setText(response.getContent().getSummaryPenagihan().getmLtpProductTitle());
        tv_id_detailmycollection.setText(response.getContent().getSummaryPenagihan().getMasterLoanId());
        tv_total_pinjam_detailmycollection.setText(formatRupiah.format((double)Integer.parseInt(response.getContent().getSummaryPenagihan().getJmlTagihan())));
        tv_sisa_hutang_detailmycollection.setText(formatRupiah.format((double)Integer.parseInt(response.getContent().getSummaryPenagihan().getSisaTagihan())));



    }

    @Override
    public void onFailureDetailMyCollection(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back_detailmycollection :
                finish();
                break;
        }
    }
}
