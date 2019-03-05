
package id.bkdana.agent.model.response.scanBarcodeResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("chk_borrower")
    private ChkBorrower mChkBorrower;
    @SerializedName("data_borrower")
    private List<DataBorrower> mDataBorrower;

    public ChkBorrower getChkBorrower() {
        return mChkBorrower;
    }

    public void setChkBorrower(ChkBorrower chkBorrower) {
        mChkBorrower = chkBorrower;
    }

    public List<DataBorrower> getDataBorrower() {
        return mDataBorrower;
    }

    public void setDataBorrower(List<DataBorrower> dataBorrower) {
        mDataBorrower = dataBorrower;
    }

}
