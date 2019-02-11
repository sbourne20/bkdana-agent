package id.bkdana.agent.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;

public class fragment_profile extends Fragment {

    private BKDanaAgentSession agentSession;
    private TextView tv_nama_profile,tv_email_profile,tv_phone_profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        agentSession = new BKDanaAgentSession(getActivity());

        tv_nama_profile = view.findViewById(R.id.tv_nama_profile);
        tv_email_profile = view.findViewById(R.id.tv_email_profile);
        tv_phone_profile = view.findViewById(R.id.tv_phone_profile);

        tv_nama_profile.setText(agentSession.getFullname());
        tv_email_profile.setText(agentSession.getEmail());
        tv_phone_profile.setText(agentSession.getPhone());

        return view;
    }
}
