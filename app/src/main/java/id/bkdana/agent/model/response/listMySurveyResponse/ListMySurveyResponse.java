
package id.bkdana.agent.model.response.listMySurveyResponse;


import com.google.gson.annotations.SerializedName;

import id.bkdana.agent.model.BaseModel;


public class ListMySurveyResponse extends BaseModel {

    @SerializedName("content")
    private Content mContent;
    public Content getContent() {
        return mContent;
    }


}
