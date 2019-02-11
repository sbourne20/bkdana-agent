package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.LoginContarct;
import id.bkdana.agent.model.response.loginResponse;
import id.bkdana.agent.model.response.profileResponse.ProfileResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.LoginBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContarct {

    private BKDanaAgentSession agentSession;
    private LoginBridge loginBridge;
    private Context context;
    private String token;
    private String TAG  = LoginPresenter.class.getSimpleName();

    public LoginPresenter(BKDanaAgentSession agentSession, LoginBridge loginBridge, Context context) {
        this.agentSession = agentSession;
        this.loginBridge = loginBridge;
        this.context = context;
    }

    @Override
    public void postlogin(String username, String password) {
        BKDapi api = ServiceClient.getClient().create(BKDapi.class);
        Call<loginResponse> call = api.postLogin(username,password);
        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        loginBridge.onSuccessLogin(response.body());
                        agentSession.setAuthorization(response.body().getToken());
                    } else {
                        loginBridge.onFailure(response.body().getResponse());
                        Log.d(TAG, "onFailureLogin: " + response.body().getResponse());
                    }
                } else if(response.code() == 401){
                    Toast.makeText(context, "Username and Password wajib di isi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                loginBridge.onFailure(t.getMessage());
                Log.d(TAG, "onFailureLogin: " + t.getMessage());

            }
        });
    }

    @Override
    public void postprofile(String token) {
        BKDapi api = ServiceClient.getClient().create(BKDapi.class);
        Call<ProfileResponse> call = api.postProfile(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        loginBridge.onSuccessProfile(response.body());
                        agentSession.setProfile(response.body().getContent().getIdModAgent(),
                                response.body().getContent().getAgentFullname(),
                                response.body().getContent().getAgentEmail(),
                                response.body().getContent().getAgentPhone(),
                                response.body().getContent().getAgentUsername(),
                                response.body().getContent().getAgentStatus());
                    } else {
                        loginBridge.onFailure(response.body().getResponse());
                        Log.d(TAG, "onFailureProfile: " + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                loginBridge.onFailure(t.getMessage());
                Log.d(TAG, "onFailureProfile: " + t.getMessage());

            }
        });
    }
}
