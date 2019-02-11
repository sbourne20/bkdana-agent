
package id.bkdana.agent.model.response.submitCollectionResponse;

import com.google.gson.annotations.SerializedName;

import id.bkdana.agent.model.BaseModel;

public class SubmitCollectionReponse extends BaseModel {

    @SerializedName("content")
    private String mContent;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

}
