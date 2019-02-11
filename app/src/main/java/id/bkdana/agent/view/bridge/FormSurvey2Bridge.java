package id.bkdana.agent.view.bridge;

public interface FormSurvey2Bridge<T> {

    void onSuccessFromSuervey2 (T response);
    void onFailureFromSurvey2 (String message);
}
