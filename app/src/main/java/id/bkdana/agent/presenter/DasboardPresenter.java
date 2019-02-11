package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.DashboardContract;
import id.bkdana.agent.model.response.dashboardResponse.DashboardResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.DashboardBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DasboardPresenter implements DashboardContract {


    private BKDanaAgentSession agentSession;
    private Context context;
    private DashboardBridge dashboardBridge;
    private String TAG = DasboardPresenter.class.getSimpleName();

    public DasboardPresenter(BKDanaAgentSession agentSession, Context context, DashboardBridge dashboardBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.dashboardBridge = dashboardBridge;
    }

    @Override
    public void getDashboard() {
        BKDapi api = ServiceClient.getClient().create(BKDapi.class);
        Call<DashboardResponse> call = api.getDashboard(agentSession.getAutorization());
        call.enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        dashboardBridge.onSuccessDashboard(response.body());
                    } else {
                        dashboardBridge.onFailureDashboard(response.body().getResponse());
                        Log.d(TAG, "onFailuredashboard: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                dashboardBridge.onFailureDashboard(t.getMessage());
                Log.d(TAG, "onFailuredashboard: " + t.getMessage());

            }
        });

    }
}
