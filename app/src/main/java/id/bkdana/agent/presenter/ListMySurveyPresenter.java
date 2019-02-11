package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.ListMySurveyContract;
import id.bkdana.agent.model.response.listMySurveyResponse.ListMySurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.ListMySurveyBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMySurveyPresenter implements ListMySurveyContract {

    private Context context;
    private BKDanaAgentSession agentSession;
    private ListMySurveyBridge  listMySurveyBridge;
    private String TAG = ListMySurveyPresenter.class.getSimpleName();

    public ListMySurveyPresenter(BKDanaAgentSession agentSession,Context context, ListMySurveyBridge listMySurveyBridge) {
        this.context = context;
        this.agentSession = agentSession;
        this.listMySurveyBridge = listMySurveyBridge;
    }

    @Override
    public void getListMySurvey(String id, String page, final String limit) {
        BKDapi api = ServiceClient.getClient().create(BKDapi.class);
        Call<ListMySurveyResponse> call = api.getListMySurvey(agentSession.getAutorization(),id,page,limit);
        call.enqueue(new Callback<ListMySurveyResponse>() {
            @Override
            public void onResponse(Call<ListMySurveyResponse> call, Response<ListMySurveyResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        listMySurveyBridge.onSuccessListMySurvey(response.body());
                    } else {
                        listMySurveyBridge.onFailureListMySurvey(response.body().getResponse());
                        Log.d(TAG, "onFailureMySurvey: " + response.body().getResponse());
                    }
                } else if(response.code() == 401){
//                    Toast.makeText(context, "Username and Password wajib di isi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListMySurveyResponse> call, Throwable t) {
                listMySurveyBridge.onFailureListMySurvey(t.getMessage());
                Log.d(TAG, "onFailureMySurvey: " + t.getMessage());
            }
        });
    }
}
