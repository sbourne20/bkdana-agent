
package id.bkdana.agent.model.response.detailMyCollectionResponse;

import com.google.gson.annotations.SerializedName;

public class SummaryPenagihan {

    @SerializedName("id_collection")
    private String mIdCollection;
    @SerializedName("id_mod_agent")
    private String mIdModAgent;
    @SerializedName("jml_tagihan")
    private String mJmlTagihan;
    @SerializedName("Master_loan_id")
    private String mMasterLoanId;
    @SerializedName("sisa_tagihan")
    private String mSisaTagihan;
    @SerializedName("tgl_collection")
    private String mTglCollection;
    @SerializedName("User_id")
    private String mUserId;

    public String getIdCollection() {
        return mIdCollection;
    }

    public void setIdCollection(String idCollection) {
        mIdCollection = idCollection;
    }

    public String getIdModAgent() {
        return mIdModAgent;
    }

    public void setIdModAgent(String idModAgent) {
        mIdModAgent = idModAgent;
    }

    public String getJmlTagihan() {
        return mJmlTagihan;
    }

    public void setJmlTagihan(String jmlTagihan) {
        mJmlTagihan = jmlTagihan;
    }

    public String getMasterLoanId() {
        return mMasterLoanId;
    }

    public void setMasterLoanId(String masterLoanId) {
        mMasterLoanId = masterLoanId;
    }

    public String getSisaTagihan() {
        return mSisaTagihan;
    }

    public void setSisaTagihan(String sisaTagihan) {
        mSisaTagihan = sisaTagihan;
    }

    public String getTglCollection() {
        return mTglCollection;
    }

    public void setTglCollection(String tglCollection) {
        mTglCollection = tglCollection;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
