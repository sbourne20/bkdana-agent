package id.bkdana.agent.view.bridge;

public interface ListSurveyBridge<T> {

    void onSuccessListSurvey(T response);
    void onFailureListSurvey(String message);
}
