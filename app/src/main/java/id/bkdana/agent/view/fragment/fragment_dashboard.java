package id.bkdana.agent.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.DashboardContract;
import id.bkdana.agent.model.response.dashboardResponse.DashboardResponse;
import id.bkdana.agent.presenter.DasboardPresenter;
import id.bkdana.agent.view.activity.ListMyCollectionActivity;
import id.bkdana.agent.view.activity.ListMySurveyActivity;
import id.bkdana.agent.view.activity.LoginActivity;
import id.bkdana.agent.view.bridge.DashboardBridge;

public class fragment_dashboard extends Fragment implements DashboardBridge<DashboardResponse>, View.OnClickListener {

    private LinearLayout llListSurvey, llListCollection;
    private ImageView iv_signtout;
    private BKDanaAgentSession agentSession;
    private TextView tv_idAgent,tv_namaAgent,tv_total_mySurvey,tv_total_myCollection;
    private DashboardContract dashboardContract;
    private String TAG = fragment_dashboard.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);


        llListSurvey = view.findViewById(R.id.llListSurvey);
        llListCollection = view.findViewById(R.id.llListCollection);
        iv_signtout = view.findViewById(R.id.iv_signout);
        tv_idAgent  = view.findViewById(R.id.tv_id_agent);
        tv_namaAgent = view.findViewById(R.id.tv_name_agent);
        tv_total_mySurvey = view.findViewById(R.id.tv_total_mysurvey);
        tv_total_myCollection = view.findViewById(R.id.tv_total_mycollection);

        agentSession = new BKDanaAgentSession(getActivity());
        dashboardContract = new DasboardPresenter(agentSession,getActivity(),this);
        dashboardContract.getDashboard();

        tv_namaAgent.setText(agentSession.getFullname());
        tv_idAgent.setText("Agent #"+agentSession.getidMod());
        llListCollection.setOnClickListener(this);
        llListSurvey.setOnClickListener(this);
        iv_signtout.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.llListSurvey :
                Intent menuListSurvey = new Intent(getActivity(),ListMySurveyActivity.class);
                getActivity().startActivity(menuListSurvey);
                break;

            case R.id.llListCollection :
                Intent menuListMyCollection= new Intent(getActivity(),ListMyCollectionActivity.class);
                getActivity().startActivity(menuListMyCollection);
                break;

            case R.id.iv_signout :
                Intent menuSinout = new Intent(getActivity(),LoginActivity.class);
                menuSinout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().startActivity(menuSinout);
                agentSession.clear();
                getActivity().finish();
        }
    }

    @Override
    public void onSuccessDashboard(DashboardResponse reponse) {
        tv_total_mySurvey.setText(reponse.getContent().getTotalMysurvey().getItotal());
        tv_total_myCollection.setText(reponse.getContent().getTotalMycollection().getItotal());
    }

    @Override
    public void onFailureDashboard(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
