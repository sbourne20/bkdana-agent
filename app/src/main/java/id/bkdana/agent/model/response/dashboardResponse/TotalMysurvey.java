
package id.bkdana.agent.model.response.dashboardResponse;

import com.google.gson.annotations.SerializedName;

public class TotalMysurvey {

    @SerializedName("itotal")
    private String mItotal;

    public String getItotal() {
        return mItotal;
    }

    public void setItotal(String itotal) {
        mItotal = itotal;
    }

}
