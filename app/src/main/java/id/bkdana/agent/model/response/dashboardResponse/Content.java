
package id.bkdana.agent.model.response.dashboardResponse;

import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("total_mycollection")
    private TotalMycollection mTotalMycollection;
    @SerializedName("total_mysurvey")
    private TotalMysurvey mTotalMysurvey;

    public TotalMycollection getTotalMycollection() {
        return mTotalMycollection;
    }

    public void setTotalMycollection(TotalMycollection totalMycollection) {
        mTotalMycollection = totalMycollection;
    }

    public TotalMysurvey getTotalMysurvey() {
        return mTotalMysurvey;
    }

    public void setTotalMysurvey(TotalMysurvey totalMysurvey) {
        mTotalMysurvey = totalMysurvey;
    }

}
