package id.bkdana.agent.view.bridge;

public interface ListMySurveyBridge<T> {

    void onSuccessListMySurvey(T response);

    void onFailureListMySurvey(String message);
}
