package id.bkdana.agent.view.bridge;

public interface ListMyCollectionBridge<T> {

    void onSuccessListMyCollection(T response);

    void onFailureListMyCollection(String message);
}
