package id.bkdana.agent.view.bridge;

public interface DashboardBridge<T> {

    void onSuccessDashboard(T reponse);

    void onFailureDashboard(String message);

}
