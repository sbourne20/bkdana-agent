package id.bkdana.agent.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import id.bkdana.agent.R;
import id.bkdana.agent.view.activity.MenuDataPeminjamActivity;

public class fragment_dashboard extends Fragment implements View.OnClickListener {

    private LinearLayout btn_pengajuan, btn_peminjam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        btn_pengajuan = view.findViewById(R.id.btn_pengajuan);
        btn_peminjam = view.findViewById(R.id.btn_penagihan);

        btn_pengajuan.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_pengajuan :
                Intent menu_pengajuan = new Intent(getActivity(), MenuDataPeminjamActivity.class);
                getActivity().startActivity(menu_pengajuan);
                break;
        }
    }
}
