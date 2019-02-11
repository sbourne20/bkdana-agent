package id.bkdana.agent.contarct;

import android.graphics.Bitmap;

import java.io.File;

public interface FormSurvey2Contract {

    void postFormSurvey2(String idAgentSurvey, String alamatUsaha, String jenisUsaha, Bitmap infoUsahaFile);
}
