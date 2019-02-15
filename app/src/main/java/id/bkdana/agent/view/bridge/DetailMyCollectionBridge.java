package id.bkdana.agent.view.bridge;

public interface DetailMyCollectionBridge<T> {
    void onSuccessDetailMyCollection(T response);
    void onFailureDetailMyCollection(String message);
}
