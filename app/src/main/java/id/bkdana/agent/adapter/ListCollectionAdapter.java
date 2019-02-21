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
import id.bkdana.agent.model.response.listMyCollectionResponse.ListMycollection;
import id.bkdana.agent.view.activity.DetailMyCollectionActivity;

public class ListCollectionAdapter extends RecyclerView.Adapter<ListCollectionAdapter.CollectionViewHolder> {

    private Context context;
    private List<ListMycollection> data;

    public ListCollectionAdapter(Context context, List<ListMycollection> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ListCollectionAdapter.CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_collection, parent, false);

        return new CollectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCollectionAdapter.CollectionViewHolder collectionViewHolder, int position) {
        final ListMycollection datum = data.get(position);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        collectionViewHolder.tv_id_collection.setText(datum.getMasterLoanId());
        collectionViewHolder.tv_nama_collection_tv_tenor_collection.setText(datum.getNamaPengguna());
        collectionViewHolder.tv_tenor_mycollection.setText(datum.getLtpProductTitle());
        collectionViewHolder.tv_sisa_hutang_collection.setText(formatRupiah.format((double)Integer.parseInt(datum.getSisaTagihan())));
        collectionViewHolder.tv_total_peminjam_collection.setText(formatRupiah.format((double)Integer.parseInt(datum.getJmlTagihan())));
        collectionViewHolder.btn_detail_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuDetailMyCollection = new Intent(context,DetailMyCollectionActivity.class);
                menuDetailMyCollection.putExtra("masted_id",datum.getMasterLoanId());
                context.startActivity(menuDetailMyCollection);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_id_collection, tv_nama_collection_tv_tenor_collection, tv_tenor_mycollection,tv_total_peminjam_collection,tv_sisa_hutang_collection;
        private Button btn_detail_collection;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id_collection = itemView.findViewById(R.id.tv_id_mycollection);
            tv_nama_collection_tv_tenor_collection =  itemView.findViewById(R.id.tv_nama_mycollection);
            tv_tenor_mycollection = itemView.findViewById(R.id.tv_tenor_collection);
            tv_sisa_hutang_collection = itemView.findViewById(R.id.tv_sisa_hutang_mycollection);
            tv_total_peminjam_collection = itemView.findViewById(R.id.tv_total_pinjam_mycollection);
            btn_detail_collection = itemView.findViewById(R.id.item_btn_detail_collection);
        }
    }
}
