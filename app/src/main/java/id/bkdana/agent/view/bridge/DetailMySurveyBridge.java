package id.bkdana.agent.view.bridge;

public interface DetailMySurveyBridge<T> {
    void onSuccessDetailMySurvey(T response);
    void onFailureDetailMySurvey(String message);
}
