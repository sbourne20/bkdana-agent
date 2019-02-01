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

import java.util.ArrayList;
import java.util.List;

import id.bkdana.agent.R;
import id.bkdana.agent.model.DataListSurvey;
import id.bkdana.agent.view.activity.DetailScanBarcodeActivity;
import id.bkdana.agent.view.activity.MenuDataPersonalActivity;

public class ListSurveyAdapter extends RecyclerView.Adapter<ListSurveyAdapter.SurveyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<DataListSurvey> data;
    private String is_data;

    public ListSurveyAdapter(Context context, List<DataListSurvey> data) {

    }

    public ListSurveyAdapter(Context context, ArrayList<DataListSurvey> data, String is_data) {
        this.context = context ;
        this.data = data;
        this.is_data = is_data;
    }

    @NonNull
    @Override
    public SurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_survey, parent, false);

        return new SurveyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyViewHolder surveyViewHolder, int position) {
        final DataListSurvey datum = data.get(position);

        if(is_data.equals("0")){
            surveyViewHolder.item_add_survey.setText("detail survey");
        } else {
            surveyViewHolder.item_add_survey.setText("add survey");
        }
        surveyViewHolder.item_nama_peminjam.setText(datum.getNama());
        surveyViewHolder.item_no_reg_peminjam.setText(datum.getNo_reg());
        surveyViewHolder.item_bulan_peminjam.setText(datum.getTenor());
        surveyViewHolder.item_total_peminjaman.setText(datum.getTotal_pinjaman());

        surveyViewHolder.item_add_survey.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.item_add_survey :
                if (!is_data.equals("0")) {
                    Intent menuAddSurvey = new Intent(context, MenuDataPersonalActivity.class);
                    context.startActivity(menuAddSurvey);
                } else {
                    Intent menuDetailSurvey = new Intent(context, DetailScanBarcodeActivity.class);
                    context.startActivity(menuDetailSurvey);
                }
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
