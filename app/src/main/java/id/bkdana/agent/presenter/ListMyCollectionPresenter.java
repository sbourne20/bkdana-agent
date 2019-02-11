package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.ListMyCollectionContract;
import id.bkdana.agent.model.response.listMyCollectionResponse.ListCollectionResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.ListMyCollectionBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMyCollectionPresenter implements ListMyCollectionContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private ListMyCollectionBridge myCollectionBridge;
    private String TAG = ListMyCollectionPresenter.class.getSimpleName();

    public ListMyCollectionPresenter(BKDanaAgentSession agentSession, Context context, ListMyCollectionBridge myCollectionBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.myCollectionBridge = myCollectionBridge;
    }

    @Override
    public void getListMyCollection(String id, String page, String limit) {
        BKDapi api = ServiceClient.getClient().create(BKDapi.class);
        Call<ListCollectionResponse> call = api.getListMyCollection(agentSession.getAutorization(),id,page,limit);
        call.enqueue(new Callback<ListCollectionResponse>() {
            @Override
            public void onResponse(Call<ListCollectionResponse> call, Response<ListCollectionResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        myCollectionBridge.onSuccessListMyCollection(response.body());
                    } else {
                        myCollectionBridge.onFailureListMyCollection(response.body().getResponse());
                        Log.d(TAG, "onFailureListMyCollection: " + response.body().getResponse());
                    }
                } else if(response.code() == 401){
//                    Toast.makeText(context, "Username and Password wajib di isi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListCollectionResponse> call, Throwable t) {
                myCollectionBridge.onFailureListMyCollection(t.getMessage());
                Log.d(TAG, "onFailureListMyCollection: " + t.getMessage());
            }
        });

    }
}
