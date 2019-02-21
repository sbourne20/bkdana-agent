
package id.bkdana.agent.model.response.listMyCollectionResponse;


import com.google.gson.annotations.SerializedName;

import id.bkdana.agent.model.BaseModel;


public class ListMyCollectionResponse extends BaseModel {

    @SerializedName("content")
    private Content mContent;


    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        mContent = content;
    }


}
