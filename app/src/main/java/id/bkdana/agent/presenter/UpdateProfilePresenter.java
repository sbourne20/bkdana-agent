package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.UpdateProfileContract;
import id.bkdana.agent.model.response.UpdateProfileResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.UpdateProfileBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfilePresenter implements UpdateProfileContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private UpdateProfileBridge updateProfileBridge;
    private String TAG = UpdateProfilePresenter.class.getSimpleName();

    public UpdateProfilePresenter(BKDanaAgentSession agentSession, Context context, UpdateProfileBridge updateProfileBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.updateProfileBridge = updateProfileBridge;
    }

    @Override
    public void postUpdateInformasiAkun(String fullName, String telepon) {
        Call<UpdateProfileResponse> call = ServiceClient.getClient().create(BKDapi.class).postUpdateInformasiAkun(agentSession.getAutorization(),fullName,telepon);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        updateProfileBridge.onSuccessUpdateInformasiAkun(response.body());
                    } else {
                        updateProfileBridge.onFailureUpdateProfile(response.body().getResponse());
                        Log.d(TAG, "onFailureUpdateProfile: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                updateProfileBridge.onFailureUpdateProfile(t.getMessage());
                Log.d(TAG, "onFailureUpdateProfile: " + t.getMessage());
            }
        });
    }

    @Override
    public void postUpdatePasswordAkun(String passOld, String passNew, String passConfirm) {
        Call<UpdateProfileResponse> call = ServiceClient.getClient().create(BKDapi.class).postUpdatePasswordAkun(agentSession.getAutorization(),passOld,passNew,passConfirm);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        updateProfileBridge.onSuccessUpdatePasswordAkun(response.body());
                    } else {
                        updateProfileBridge.onFailureUpdateProfile(response.body().getResponse());
                        Log.d(TAG, "onFailureUpdateProfile: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                updateProfileBridge.onFailureUpdateProfile(t.getMessage());
                Log.d(TAG, "onFailureUpdateProfile: " + t.getMessage());
            }
        });

    }
}
