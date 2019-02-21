package id.bkdana.agent.view.bridge;

public interface UpdateProfileBridge<T,R,U> {

    void onSuccessUpdateInformasiAkun(T response);
    void onSuccessUpdatePasswordAkun(R response);
    void onSuccesUpdateSessionProfile(U response);
    void onFailureUpdateProfile(String message);

}
