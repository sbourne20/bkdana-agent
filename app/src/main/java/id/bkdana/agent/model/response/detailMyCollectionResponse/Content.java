
package id.bkdana.agent.model.response.detailMyCollectionResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("catatan_penagihan")
    private List<CatatanPenagihan> mCatatanPenagihan;
    @SerializedName("summary_penagihan")
    private SummaryPenagihan mSummaryPenagihan;

    public List<CatatanPenagihan> getCatatanPenagihan() {
        return mCatatanPenagihan;
    }

    public void setCatatanPenagihan(List<CatatanPenagihan> catatanPenagihan) {
        mCatatanPenagihan = catatanPenagihan;
    }

    public SummaryPenagihan getSummaryPenagihan() {
        return mSummaryPenagihan;
    }

    public void setSummaryPenagihan(SummaryPenagihan summaryPenagihan) {
        mSummaryPenagihan = summaryPenagihan;
    }

}
