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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.model.DataListSurvey;
import id.bkdana.agent.model.response.listSurveyResponse.ListPeminjam;
import id.bkdana.agent.view.activity.DetailScanBarcodeActivity;
import id.bkdana.agent.view.activity.MenuDataPersonalActivity;

public class ListSurveyAdapter extends RecyclerView.Adapter<ListSurveyAdapter.SurveyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ListPeminjam> data;




    public ListSurveyAdapter(Context context, List<ListPeminjam> data) {
        this.context = context ;
        this.data = data;
    }

    @NonNull
    @Override
    public SurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_survey, parent, false);

        return new SurveyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SurveyViewHolder surveyViewHolder, int position) {
        final ListPeminjam datum = data.get(position);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        surveyViewHolder.item_add_survey.setText("add survey");
        surveyViewHolder.item_nama_peminjam.setText(datum.getNamaPeminjam());
        surveyViewHolder.item_no_reg_peminjam.setText(datum.getTransaksiId());
        surveyViewHolder.item_bulan_peminjam.setText(datum.getLoanTerm() + " Bulan");
        surveyViewHolder.item_total_peminjaman.setText(formatRupiah.format((double)Integer.parseInt(datum.getTotalPinjam())));

        surveyViewHolder.item_add_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuAddSurvey = new Intent(v.getContext(), MenuDataPersonalActivity.class);
                menuAddSurvey.putExtra("intent_idPeminjam", datum.getIdPeminjam());
                menuAddSurvey.putExtra("intent_masterLoadId", datum.getTransaksiId());
                menuAddSurvey.putExtra("intent_productTitle", datum.getProductTitle());

                context.startActivity(menuAddSurvey);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.item_add_survey :

                    break;

        }
    }

    public class SurveyViewHolder extends RecyclerView.ViewHolder {

        private TextView item_nama_peminjam,item_no_reg_peminjam,item_bulan_peminjam,item_total_peminjaman;
        private Button item_add_survey;


        public SurveyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_nama_peminjam = itemView.findViewById(R.id.item_nama_peminjam);
            item_no_reg_peminjam = itemView.findViewById(R.id.item_no_reg_peminjam);
            item_bulan_peminjam = itemView.findViewById(R.id.item_bulan_peminjam);
            item_total_peminjaman = itemView.findViewById(R.id.item_total_peminjaman);
            item_add_survey = itemView.findViewById(R.id.item_add_survey);
        }
    }
}
