package id.bkdana.agent.view.bridge;

public interface SubmitCollectionBridge<T> {
    void onSuccessSubmitCollection(T response);
    void onFailureSubmitCollection(String message);
}
