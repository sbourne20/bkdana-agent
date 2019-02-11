
package id.bkdana.agent.model.response.scanBarcodeResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("data_borrower")
    private List<DataBorrower> mDataBorrower;

    public List<DataBorrower> getDataBorrower() {
        return mDataBorrower;
    }

    public void setDataBorrower(List<DataBorrower> dataBorrower) {
        mDataBorrower = dataBorrower;
    }

}
