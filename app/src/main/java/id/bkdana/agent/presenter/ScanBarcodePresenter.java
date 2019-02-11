package id.bkdana.agent.presenter;

import android.content.Context;
import android.util.Log;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.ScanBarcodeContract;
import id.bkdana.agent.model.response.scanBarcodeResponse.ScanBarcodeResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.ScanBarcodeBridge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanBarcodePresenter implements ScanBarcodeContract {

    private BKDanaAgentSession agentSession;
    private Context context;
    private ScanBarcodeBridge scanBarcodeBridge;
    private String TAG = ScanBarcodePresenter.class.getSimpleName();

    public ScanBarcodePresenter(BKDanaAgentSession agentSession, Context context, ScanBarcodeBridge scanBarcodeBridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.scanBarcodeBridge = scanBarcodeBridge;
    }

    @Override
    public void postScanBarcode(String idPeminjam) {
        Call<ScanBarcodeResponse> call = ServiceClient.getClient().create(BKDapi.class).postScanBarcode(agentSession.getAutorization(),idPeminjam);
        call.enqueue(new Callback<ScanBarcodeResponse>() {
            @Override
            public void onResponse(Call<ScanBarcodeResponse> call, Response<ScanBarcodeResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        scanBarcodeBridge.onSuccessScanBarcode(response.body());
                    } else {
                        scanBarcodeBridge.onFailureScanBarcode(response.body().getResponse());
                        Log.d(TAG, "onFailureScanBarcode :" + response.body().getResponse());
                    }
                }
            }

            @Override
            public void onFailure(Call<ScanBarcodeResponse> call, Throwable t) {
                scanBarcodeBridge.onFailureScanBarcode(t.getMessage());
                Log.d(TAG, "onFailureScanBarcode :" + t.getMessage());
            }
        });

    }
}
