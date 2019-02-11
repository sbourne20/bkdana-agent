package id.bkdana.agent.view.bridge;

public interface ScanBarcodeBridge<T> {
    void onSuccessScanBarcode(T response);
    void onFailureScanBarcode(String message);
}

