package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.SubmitCollectionContract;
import id.bkdana.agent.model.response.submitCollectionResponse.SubmitCollectionReponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.SubmitCollectionBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitCollectionPresenter implements SubmitCollectionContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private SubmitCollectionBridge submitCollectionBridge;
    private String TAG = SubmitCollectionPresenter.class.getSimpleName();

    public SubmitCollectionPresenter(BKDanaAgentSession agentSession, Context context, SubmitCollectionBridge submitCollectionBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.submitCollectionBridge = submitCollectionBridge;
    }

    @Override
    public void postSubmitCollection(String userId, String iDModAgent, String masterLoanId, String jmlTagihan, String sisaTagihan, String borrower_code) {
        Call<SubmitCollectionReponse> call = ServiceClient.getClient().create(BKDapi.class).postSubmitCollection(agentSession.getAutorization(),userId,iDModAgent,masterLoanId,jmlTagihan,sisaTagihan,borrower_code);
        call.enqueue(new Callback<SubmitCollectionReponse>() {
            @Override
            public void onResponse(Call<SubmitCollectionReponse> call, Response<SubmitCollectionReponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        submitCollectionBridge.onSuccessSubmitCollection(response.body());
                    } else {
                        submitCollectionBridge.onFailureSubmitCollection(response.body().getResponse());
                        Log.d(TAG, "onFailureSubmitCollection: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<SubmitCollectionReponse> call, Throwable t) {
                submitCollectionBridge.onFailureSubmitCollection(t.getMessage());
                Log.d(TAG, "onFailureSubmitCollection: " + t.getMessage());
            }
        });
    }
}
