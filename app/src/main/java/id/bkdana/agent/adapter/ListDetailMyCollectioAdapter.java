package id.bkdana.agent.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.model.response.detailMyCollectionResponse.CatatanPenagihan;
import id.bkdana.agent.view.activity.DetailMyCollectionActivity;

public class ListDetailMyCollectioAdapter extends RecyclerView.Adapter<ListDetailMyCollectioAdapter.DetailMyCollectionHolder> {

    private Context context;
    private List<CatatanPenagihan> data;
    private CatatanPenagihan datum;

    public ListDetailMyCollectioAdapter(Context context, List<CatatanPenagihan> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ListDetailMyCollectioAdapter.DetailMyCollectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_detail_mycollection, parent, false);

        return new DetailMyCollectionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDetailMyCollectioAdapter.DetailMyCollectionHolder detailMyCollectionHolder, int position) {
        datum = data.get(position);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat outputDay = new SimpleDateFormat("EEEE",localeID);
        DateFormat outputDate = new SimpleDateFormat("dd");
        DateFormat outputMonth = new SimpleDateFormat("MMMM",localeID);
        DateFormat outputYear = new SimpleDateFormat("yyyy");
        DateFormat outputJam = new SimpleDateFormat("HH");
        DateFormat outputMenit = new SimpleDateFormat("mm");
        Date date1 = null;

        try {
            date1 = inputFormat.parse(datum.getTglCollection());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String hari = outputDay.format(date1);
        String tanggal = outputDate.format(date1);
        String bulan = outputMonth.format(date1);
        String tahun = outputYear.format(date1);
        String jam = outputJam.format(date1);
        String menit = outputMenit.format(date1);

        detailMyCollectionHolder.tv_id_item_listdetailmycollection.setText(datum.getMasterLoanId());
        detailMyCollectionHolder.tv_tgl_item_listdetailmycollection.setText(hari + " " + tanggal + " " + bulan + " " + tahun + " " + jam + ":" + menit);
        detailMyCollectionHolder.tv_tgh_item_listdetailmycollection.setText(formatRupiah.format((double) Integer.parseInt(datum.getJmlTagihan())));






    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailMyCollectionHolder extends RecyclerView.ViewHolder {

        private TextView tv_id_item_listdetailmycollection, tv_tgl_item_listdetailmycollection, tv_tgh_item_listdetailmycollection;

        public DetailMyCollectionHolder(@NonNull View itemView) {
            super(itemView);
            tv_id_item_listdetailmycollection = itemView.findViewById(R.id.tv_id_item_listdetailmycollection);
            tv_tgl_item_listdetailmycollection = itemView.findViewById(R.id.tv_tgl_item_listdetailmycollection);
            tv_tgh_item_listdetailmycollection = itemView.findViewById(R.id.tv_tgh_item_listdetailmycollection);
        }
    }
}
