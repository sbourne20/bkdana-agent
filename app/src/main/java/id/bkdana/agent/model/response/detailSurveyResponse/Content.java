
package id.bkdana.agent.model.response.detailSurveyResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("data_survey")
    private List<DataSurvey> mDataSurvey;

    public List<DataSurvey> getDataSurvey() {
        return mDataSurvey;
    }

    public void setDataSurvey(List<DataSurvey> dataSurvey) {
        mDataSurvey = dataSurvey;
    }

}
