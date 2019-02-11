package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.FormSurvey1Contract;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.FormSurvey1Bridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSurvey1Presenter implements FormSurvey1Contract {


    private BKDanaAgentSession agentSession;
    private Context context;
    private FormSurvey1Bridge formSurvey1Bridge;
    private String TAG = FormSurvey1Presenter.class.getSimpleName();

    public FormSurvey1Presenter(BKDanaAgentSession agentSession, Context context, FormSurvey1Bridge formSurvey1Bridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.formSurvey1Bridge = formSurvey1Bridge;
    }

    @Override
    public void postFormSurvey1(String id_agent, String id_peminjam, String master_loan_id, String product_title, String nama, String alamat, String no_ktp) {
        BKDapi api  = ServiceClient.getClient().create(BKDapi.class);
        Call<FormSurveyResponse> call = api.postFormSurvey1(agentSession.getAutorization(),id_agent,id_peminjam,master_loan_id,product_title,nama,alamat,no_ktp);
        call.enqueue(new Callback<FormSurveyResponse>() {
            @Override
            public void onResponse(Call<FormSurveyResponse> call, Response<FormSurveyResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        formSurvey1Bridge.onSuccessFromSuervey1(response.body());
                    } else {
                        formSurvey1Bridge.onFailureFromSurvey1(response.body().getResponse());
                        Log.d(TAG, "onFailureFormSurvey1: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<FormSurveyResponse> call, Throwable t) {
                formSurvey1Bridge.onFailureFromSurvey1(t.getMessage());
                Log.d(TAG, "onFailureFormSurvey1: " + t.getMessage());
            }
        });
    }
}
