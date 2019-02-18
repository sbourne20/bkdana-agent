package id.bkdana.agent.view.bridge;

public interface UpdateProfileBridge<T,R> {

    void onSuccessUpdateInformasiAkun(T response);
    void onSuccessUpdatePasswordAkun(R response);
    void onFailureUpdateProfile(String message);

}
