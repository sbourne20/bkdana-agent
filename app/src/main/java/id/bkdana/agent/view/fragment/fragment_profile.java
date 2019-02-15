package id.bkdana.agent.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.view.activity.EditProfileActivity;
import id.bkdana.agent.view.activity.MainActivity;

public class fragment_profile extends Fragment implements View.OnClickListener {

    private BKDanaAgentSession agentSession;
    private TextView tv_nama_profile,tv_email_profile,tv_phone_profile;
    private Button btn_edit_profile;
    private ImageView iv_back_profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        agentSession = new BKDanaAgentSession(getActivity());

        tv_nama_profile = view.findViewById(R.id.tv_nama_profile);
        tv_email_profile = view.findViewById(R.id.tv_email_profile);
        tv_phone_profile = view.findViewById(R.id.tv_phone_profile);
        btn_edit_profile = view.findViewById(R.id.btn_edit_profile);
        iv_back_profile = view.findViewById(R.id.iv_back_profile);

        tv_nama_profile.setText(agentSession.getFullname());
        tv_email_profile.setText(agentSession.getEmail());
        tv_phone_profile.setText(agentSession.getPhone());
        iv_back_profile.setOnClickListener(this);
        btn_edit_profile.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_edit_profile:
                Intent menuEditProfile = new Intent(getActivity(),EditProfileActivity.class);
                getActivity().startActivity(menuEditProfile);
                break;

            case R.id.iv_back_profile :
                Intent menuMain = new Intent(getActivity(),MainActivity.class);
                getActivity().startActivity(menuMain);
                getActivity().finish();
        }

    }
}
