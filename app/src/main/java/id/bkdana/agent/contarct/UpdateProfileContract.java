package id.bkdana.agent.contarct;

public interface UpdateProfileContract {

    void postUpdateInformasiAkun(String fullName, String telepon);
    void postUpdatePasswordAkun(String passOld, String passNew, String passConfirm);
    void postUpdateprofile(String token);
}
