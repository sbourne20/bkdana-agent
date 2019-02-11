package id.bkdana.agent.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.FormSurvey2Contract;
import id.bkdana.agent.contarct.FormSurvey3Contract;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.FormSurvey3Bridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSurvey3Presenter implements FormSurvey3Contract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private FormSurvey3Bridge formSurvey3Bridge;
    private String TAG = FormSurvey3Presenter.class.getSimpleName();

    public FormSurvey3Presenter(BKDanaAgentSession agentSession, Context context, FormSurvey3Bridge formSurvey3Bridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.formSurvey3Bridge = formSurvey3Bridge;
    }


    @Override
    public void postFormSurvey3(String idAgentFormSurvey3, String omset, String biaya, String laba) {
        Call<FormSurveyResponse> call =ServiceClient.getClient().create(BKDapi.class).postFormSurvey3(agentSession.getAutorization(),idAgentFormSurvey3,omset,biaya,laba);
        call.enqueue(new Callback<FormSurveyResponse>() {
            @Override
            public void onResponse(Call<FormSurveyResponse> call, Response<FormSurveyResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        formSurvey3Bridge.onSuccessFromSuervey3(response.body());
                    } else {
                        formSurvey3Bridge.onFailureFromSurvey3(response.body().getResponse());
                        Log.d(TAG, "onFailureFromSurvey: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<FormSurveyResponse> call, Throwable t) {
                formSurvey3Bridge.onFailureFromSurvey3(t.getMessage());
                Log.d(TAG, "onFailureFromSurvey: " + t.getMessage());
            }
        });
    }
}
