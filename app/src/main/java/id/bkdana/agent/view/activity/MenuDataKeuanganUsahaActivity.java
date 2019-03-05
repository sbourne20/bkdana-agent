package id.bkdana.agent.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import id.bkdana.agent.R;
import id.bkdana.agent.Util.ConnectionDetector;
import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.FormSurvey2Contract;
import id.bkdana.agent.contarct.FormSurvey3Contract;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.presenter.FormSurvey3Presenter;
import id.bkdana.agent.view.bridge.FormSurvey3Bridge;

public class MenuDataKeuanganUsahaActivity extends AppCompatActivity implements FormSurvey3Bridge<FormSurveyResponse>, View.OnClickListener, LocationListener {


    private Button btn_kirim_survey_peminjam;
    private TextView tv_dataKeungan_produktitle, tv_dataKeungan_masterLoadId;
    private EditText et_omzet_peminjam, et_biaya_peminjam, et_laba_peminjam;
    private ImageView iv_back_survey;
    private String id_agent,id_peminjam,master_loan_id,product_title,omset,biaya,laba;
    private BKDanaAgentSession agentSession;
    private FormSurvey3Contract formSurvey3Contract;
    private Double lat, longi;
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_keuangan_usaha);

        agentSession = new BKDanaAgentSession(this);
        formSurvey3Contract = new FormSurvey3Presenter(agentSession,this,this);
        cd = new ConnectionDetector(this);

        btn_kirim_survey_peminjam = findViewById(R.id.btn_kirim_survey_peminjam);
        iv_back_survey = findViewById(R.id.iv_back_survey);
        tv_dataKeungan_masterLoadId = findViewById(R.id.tv_dataKeuangan_masterLoadId);
        tv_dataKeungan_produktitle = findViewById(R.id.tv_dataKeuangan_produktitle);
        et_omzet_peminjam = findViewById(R.id.et_omzet_peminjam);
        et_biaya_peminjam = findViewById(R.id.et_biaya_peminjam);
        et_laba_peminjam = findViewById(R.id.et_laba_peminjam);

        btn_kirim_survey_peminjam.setOnClickListener(this);
        iv_back_survey.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        onSetData();
        getLocation();

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    void onSetData(){
        id_peminjam = getIntent().getStringExtra("intent_idPeminjam");
        master_loan_id = getIntent().getStringExtra("intent_masterLoadId");
        product_title = getIntent().getStringExtra("intent_productTitle");

        tv_dataKeungan_produktitle.setText(product_title);
        tv_dataKeungan_masterLoadId.setText(master_loan_id);
    }

    void onSendData(){

        omset = et_omzet_peminjam.getText().toString();
        biaya = et_biaya_peminjam.getText().toString();
        laba = et_laba_peminjam.getText().toString();
        String latitude = String.valueOf(lat);
        String longitude = String.valueOf(longi);

        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            formSurvey3Contract.postFormSurvey3(agentSession.getidMod(),omset,biaya,laba,latitude,longitude);
        }  else if (isInternetPresent.equals(false)) {
            Toast.makeText(this, "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessFromSuervey3(FormSurveyResponse response) {
        Toast.makeText(this, response.getResponse(), Toast.LENGTH_SHORT).show();
        Intent data_usaha = new Intent(this,NotifSurveyActivity.class);
        startActivity(data_usaha);
        finish();
    }

    @Override
    public void onFailureFromSurvey3(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_kirim_survey_peminjam :
                onSendData();
                break;
            case  R.id.iv_back_survey :
                finish();
                break;

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        longi = location.getLongitude();

        Log.i("ini", "onLocationChanged: " + lat + "," + longi);


        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
//                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(MenuDataKeuanganUsahaActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
