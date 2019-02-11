package id.bkdana.agent.view.bridge;

public interface LoginBridge<T,V> {

    void onSuccessLogin(T response);
    void onSuccessProfile(V response);
    void onFailure(String message);

}
