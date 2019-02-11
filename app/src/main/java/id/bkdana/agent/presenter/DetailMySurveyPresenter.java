package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.DetailSurveyContract;
import id.bkdana.agent.model.response.detailSurveyResponse.DetailSurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.DetailMySurveyBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMySurveyPresenter implements DetailSurveyContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private DetailMySurveyBridge detailMySurveyBridge;
    private String TAG = DetailMySurveyPresenter.class.getSimpleName();

    public DetailMySurveyPresenter(BKDanaAgentSession agentSession, Context context, DetailMySurveyBridge detailMySurveyBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.detailMySurveyBridge = detailMySurveyBridge;
    }

    @Override
    public void postDetailMySurvey(String idModAgent) {
        Call<DetailSurveyResponse> call = ServiceClient.getClient().create(BKDapi.class).postDetailMySurvey(agentSession.getAutorization(),idModAgent);
        call.enqueue(new Callback<DetailSurveyResponse>() {
            @Override
            public void onResponse(Call<DetailSurveyResponse> call, Response<DetailSurveyResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        detailMySurveyBridge.onSuccessDetailMySurvey(response.body());
                    } else {
                        detailMySurveyBridge.onFailureDetailMySurvey(response.body().getResponse());
                        Log.d(TAG, "onFailureDetailMySurvey: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailSurveyResponse> call, Throwable t) {
                detailMySurveyBridge.onFailureDetailMySurvey(t.getMessage());
                Log.d(TAG, "onFailureDetailMySurvey: " + t.getMessage());
            }
        });

    }
}
