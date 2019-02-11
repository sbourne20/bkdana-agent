package id.bkdana.agent.view.bridge;

public interface FormSurvey1Bridge<T> {

    void onSuccessFromSuervey1 (T response);
    void onFailureFromSurvey1 (String message);
}
