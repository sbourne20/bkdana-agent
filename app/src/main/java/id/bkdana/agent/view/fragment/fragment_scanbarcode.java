package id.bkdana.agent.view.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.ScanBarcodeContract;
import id.bkdana.agent.model.response.scanBarcodeResponse.DataBorrower;
import id.bkdana.agent.model.response.scanBarcodeResponse.ScanBarcodeResponse;
import id.bkdana.agent.model.temp;
import id.bkdana.agent.presenter.ScanBarcodePresenter;
import id.bkdana.agent.view.activity.DetailScanBarcodeActivity;
import id.bkdana.agent.view.activity.MainActivity;
import id.bkdana.agent.view.bridge.ScanBarcodeBridge;

public class fragment_scanbarcode extends AppCompatActivity implements ScanBarcodeBridge<ScanBarcodeResponse>, View.OnClickListener {

    private ObjectAnimator animator;
    private SurfaceView surfaceView;
    private View scannerLayout;
    private View scannerBar;
    private TextView txtBarcodeValue;
    private String uniqueid;
    private ImageView iv_back_scanbarcode;
    private temp temp = new temp();

    private BKDanaAgentSession agentSession;
    private ScanBarcodeContract scanBarcodeContract;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;


    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_scanbarcode);

        agentSession = new BKDanaAgentSession(this);
        scanBarcodeContract = new ScanBarcodePresenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        scannerLayout = findViewById(R.id.scannerLayout);
        scannerBar = findViewById(R.id.scannerBar);
        surfaceView = findViewById(R.id.cameraView);
        iv_back_scanbarcode = findViewById(R.id.iv_back_scanbarcode);
        animator = null;

        iv_back_scanbarcode.setOnClickListener(this);

        ViewTreeObserver vto = scannerLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scannerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    scannerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    scannerLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                float destination = (float) (scannerLayout.getY() + scannerLayout.getHeight());
                animator = ObjectAnimator.ofFloat(scannerBar, "translationY", scannerLayout.getY(), destination);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(3000);
                animator.start();
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            return;
        }

        initialiseDetectorsAndSources();
    }


    private void initialiseDetectorsAndSources() {

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(fragment_scanbarcode.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(fragment_scanbarcode.this, new
                                String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(fragment_scanbarcode.this, "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).displayValue != null) {
                                uniqueid = barcodes.valueAt(0).displayValue;
//                                txtBarcodeValue.setText(intentData);
                                if(!uniqueid.equals(temp.getTmp())) {
                                    onSendData(uniqueid);
                                    temp.setTmp(uniqueid);
                                }

                            }
                        }
                    });

                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Here call or Open your camera;
                    initialiseDetectorsAndSources();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    void onSendData(String id){
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            scanBarcodeContract.postScanBarcode(id);
        }  else if (isInternetPresent.equals(false)) {
            Toast.makeText(this, "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_scanbarcode:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public void onSuccessScanBarcode(ScanBarcodeResponse response) {
        ArrayList<DataBorrower> dataBorrower = new ArrayList<>();
        Intent menuDetailScan = new Intent(fragment_scanbarcode.this,DetailScanBarcodeActivity.class);
        for (int i = 0; i < response.getContent().getDataBorrower().size() ; i++) {
            dataBorrower.add(response.getContent().getDataBorrower().get(i));
            menuDetailScan.putParcelableArrayListExtra("dataBorrower",dataBorrower);
        }

        startActivity(menuDetailScan);
        finish();
    }

    @Override
    public void onFailureScanBarcode(String message) {

    }
}
