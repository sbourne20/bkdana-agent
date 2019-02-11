package id.bkdana.agent.presenter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

import id.bkdana.agent.Util.Session.BKDanaAgentSession;
import id.bkdana.agent.contarct.FormSurvey2Contract;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.service.BKDapi;
import id.bkdana.agent.service.ServiceClient;
import id.bkdana.agent.view.bridge.FormSurvey2Bridge;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSurvey2Presenter implements FormSurvey2Contract {

    private BKDanaAgentSession agentSession;
     Context context;
    private FormSurvey2Bridge formSurvey2Bridge;
    private String TAG = FormSurvey2Presenter.class.getSimpleName();

    public FormSurvey2Presenter(BKDanaAgentSession agentSession, Context context, FormSurvey2Bridge formSurvey2Bridge) {
        this.agentSession = agentSession;
        this.context = context;
        this.formSurvey2Bridge = formSurvey2Bridge;
    }

    @Override
    public void postFormSurvey2(String idAgentSurvey, String alamatUsaha, String jenisUsaha, Bitmap infoUsahaFile) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Bitmap.createScaledBitmap(infoUsahaFile,800,800,false);
        infoUsahaFile.compress(Bitmap.CompressFormat.JPEG, 70, bos);


        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), bos.toByteArray());
        MultipartBody.Part uploadImageBody = MultipartBody.Part.createFormData(
                "info_usaha_file",
                "Lokasi_Usaha.jpg",
                requestFile);
        RequestBody idAgentSurveyBody = RequestBody.create(MediaType.parse("text/plain"), idAgentSurvey);
        RequestBody alamatUsahaBody = RequestBody.create(MediaType.parse("text/plain"), alamatUsaha);
        RequestBody jenisUsahaBody = RequestBody.create(MediaType.parse("text/plain"), jenisUsaha);

        Call<FormSurveyResponse> call = ServiceClient.getClient().create(BKDapi.class).postFormSurvey2(agentSession.getAutorization(),idAgentSurveyBody,alamatUsahaBody,jenisUsahaBody,uploadImageBody);
        call.enqueue(new Callback<FormSurveyResponse>() {
            public void onResponse(Call<FormSurveyResponse> call, Response<FormSurveyResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        formSurvey2Bridge.onSuccessFromSuervey2(response.body());
                    } else {
                        formSurvey2Bridge.onFailureFromSurvey2(response.body().getResponse());
                        Log.d(TAG, "onFailureFromSurvey2: " + response.body().getResponse());
                    }

                }

            }

            public void onFailure(Call<FormSurveyResponse> call, Throwable t) {
                formSurvey2Bridge.onFailureFromSurvey2(t.getMessage());
                Log.d(TAG, "onFailureFromSurvey2: " + t.getMessage());
            }
        });
    }
}
