package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.DetailMyCollectionContract;
import id.bkdana.agent.contarct.DetailSurveyContract;
import id.bkdana.agent.model.response.detailMyCollectionResponse.DetailMyCollectionResponse;
import id.bkdana.agent.model.response.detailSurveyResponse.DetailSurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.DetailMyCollectionBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMyCollectionPresenter implements DetailMyCollectionContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private DetailMyCollectionBridge detailMyCollectionBridge;
    private String TAG = DetailMySurveyPresenter.class.getSimpleName();

    public DetailMyCollectionPresenter(BKDanaAgentSession agentSession, Context context, DetailMyCollectionBridge detailMyCollectionBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.detailMyCollectionBridge = detailMyCollectionBridge;
    }

    @Override
    public void postDetailMycontract (String idModAgent) {
        Call<DetailMyCollectionResponse> call = ServiceClient.getClient().create(BKDapi.class).postDetailMyCollection(agentSession.getAutorization(),idModAgent);
        call.enqueue(new Callback<DetailMyCollectionResponse>() {
            @Override
            public void onResponse(Call<DetailMyCollectionResponse> call, Response<DetailMyCollectionResponse> response) {
                if (response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        detailMyCollectionBridge.onSuccessDetailMyCollection(response.body());
                    } else {
                        detailMyCollectionBridge.onFailureDetailMyCollection(response.body().getResponse());
                        Log.d(TAG, "onFailureDetailMyCollection: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailMyCollectionResponse> call, Throwable t) {
                detailMyCollectionBridge.onFailureDetailMyCollection(t.getMessage());
                Log.d(TAG, "onFailureDetailMyCollection: " + t.getMessage());
            }
        });

    }
}
