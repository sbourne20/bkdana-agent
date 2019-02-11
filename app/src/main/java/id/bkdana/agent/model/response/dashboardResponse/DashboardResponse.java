
package id.bkdana.agent.model.response.dashboardResponse;

import com.google.gson.annotations.SerializedName;

import id.bkdana.agent.model.BaseModel;

public class DashboardResponse extends BaseModel {

    @SerializedName("content")
    private Content mContent;

    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        mContent = content;
    }

}
