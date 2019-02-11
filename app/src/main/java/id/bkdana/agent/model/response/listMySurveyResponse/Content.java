
package id.bkdana.agent.model.response.listMySurveyResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("list_mysurvey")
    private List<ListMysurvey> mListMysurvey;

    public List<ListMysurvey> getListMysurvey() {
        return mListMysurvey;
    }

    public void setListMysurvey(List<ListMysurvey> listMysurvey) {
        mListMysurvey = listMysurvey;
    }

}
