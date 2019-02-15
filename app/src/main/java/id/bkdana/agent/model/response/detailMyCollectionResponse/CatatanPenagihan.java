
package id.bkdana.agent.model.response.detailMyCollectionResponse;

import com.google.gson.annotations.SerializedName;

public class CatatanPenagihan {

    @SerializedName("jml_tagihan")
    private String mJmlTagihan;
    @SerializedName("Master_loan_id")
    private String mMasterLoanId;
    @SerializedName("tgl_collection")
    private String mTglCollection;

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

    public String getTglCollection() {
        return mTglCollection;
    }

    public void setTglCollection(String tglCollection) {
        mTglCollection = tglCollection;
    }

}
