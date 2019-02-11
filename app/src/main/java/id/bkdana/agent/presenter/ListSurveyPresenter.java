package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.ListSurveyContract;
import id.bkdana.agent.model.response.listSurveyResponse.ListSurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.ListSurveyBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSurveyPresenter implements ListSurveyContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private ListSurveyBridge surveyBridge;
    private String TAG = ListSurveyPresenter.class.getSimpleName();

    public ListSurveyPresenter(BKDanaAgentSession agentSession, Context context, ListSurveyBridge surveyBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.surveyBridge = surveyBridge;
    }

    @Override
    public void getListSurvey(String limit, String page) {
        BKDapi api = ServiceClient.getClient().create(BKDapi.class);
        Call<ListSurveyResponse> call = api.getListSurvey(agentSession.getAutorization(),limit,page);
        call.enqueue(new Callback<ListSurveyResponse>() {
            @Override
            public void onResponse(Call<ListSurveyResponse> call, Response<ListSurveyResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        surveyBridge.onSuccessListSurvey(response.body());
                    } else {
                        surveyBridge.onFailureListSurvey(response.body().getResponse());
                        Log.d(TAG, "onFailureListSurvey: " + response.body().getResponse());
                    }
                } else if(response.code() == 401){
//                    Toast.makeText(context, "Username and Password wajib di isi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListSurveyResponse> call, Throwable t) {
                surveyBridge.onFailureListSurvey(t.getMessage());
                Log.d(TAG, "onFailureListSurvey: " + t.getMessage());
            }
        });

    }
}
