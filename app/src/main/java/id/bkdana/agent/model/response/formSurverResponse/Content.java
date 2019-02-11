
package id.bkdana.agent.model.response.formSurverResponse;

import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("last_id")
    private Long mLastId;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("proses")
    private String mProses;

    public Long getLastId() {
        return mLastId;
    }

    public void setLastId(Long lastId) {
        mLastId = lastId;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getProses() {
        return mProses;
    }

    public void setProses(String proses) {
        mProses = proses;
    }

}
