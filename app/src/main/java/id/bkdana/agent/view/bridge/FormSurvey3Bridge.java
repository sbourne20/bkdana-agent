package id.bkdana.agent.view.bridge;

public interface FormSurvey3Bridge<T> {

    void onSuccessFromSuervey3 (T response);
    void onFailureFromSurvey3 (String message);
}
