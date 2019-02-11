package id.bkdana.agent.contarct;

public interface LoginContarct {


    void postlogin(String username,String password);

    void postprofile(String token);
}
