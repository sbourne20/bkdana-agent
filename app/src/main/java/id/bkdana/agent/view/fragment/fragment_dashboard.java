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
import id.bkdana.agent.view.activity.ListSurveyActivity;

public class fragment_dashboard extends Fragment implements View.OnClickListener {

    private LinearLayout llListSurvey, llListCollection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);


        llListSurvey = view.findViewById(R.id.llListSurvey);
        llListCollection = view.findViewById(R.id.llListCollection);

        llListSurvey.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.llListSurvey :
                Intent menuListSurvey = new Intent(getActivity(),ListSurveyActivity.class);
                getActivity().startActivity(menuListSurvey);
                break;
        }
    }
}
