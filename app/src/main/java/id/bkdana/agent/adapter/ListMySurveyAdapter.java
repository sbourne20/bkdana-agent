package id.bkdana.agent.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.model.response.listMySurveyResponse.ListMysurvey;
import id.bkdana.agent.view.activity.DetailScanBarcodeActivity;
import id.bkdana.agent.view.activity.DetailSurveyActivity;
import id.bkdana.agent.view.activity.MenuDataPersonalActivity;

public class ListMySurveyAdapter extends RecyclerView.Adapter<ListMySurveyAdapter.MySurveyViewHolder> {


    private Context context;
    private List<ListMysurvey> data;
    private ListMysurvey datum;


    public ListMySurveyAdapter(Context context, List<ListMysurvey> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ListMySurveyAdapter.MySurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_survey, parent, false);

        return new MySurveyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMySurveyAdapter.MySurveyViewHolder mySurveyViewHolder, int position) {
        datum = data.get(position);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        mySurveyViewHolder.item_add_survey.setText("detail survey");
        mySurveyViewHolder.item_nama_peminjam.setText(datum.getNama());
        mySurveyViewHolder.item_no_reg_peminjam.setText(datum.getMasterLoanId());


        mySurveyViewHolder.item_add_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuAddSurvey = new Intent(context, DetailSurveyActivity.class);
                menuAddSurvey.putExtra("detail_id", datum.getIdModAgentSurvey());
                context.startActivity(menuAddSurvey);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MySurveyViewHolder extends RecyclerView.ViewHolder {

        private TextView item_nama_peminjam,item_no_reg_peminjam,item_bulan_peminjam,item_total_peminjaman;
        private Button item_add_survey;

        public MySurveyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_nama_peminjam = itemView.findViewById(R.id.item_nama_peminjam);
            item_no_reg_peminjam = itemView.findViewById(R.id.item_no_reg_peminjam);
            item_bulan_peminjam = itemView.findViewById(R.id.item_bulan_peminjam);
            item_total_peminjaman = itemView.findViewById(R.id.item_total_peminjaman);
            item_add_survey = itemView.findViewById(R.id.item_add_survey);
        }
    }
}
