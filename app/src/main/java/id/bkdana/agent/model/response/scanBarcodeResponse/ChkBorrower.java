
package id.bkdana.agent.model.response.scanBarcodeResponse;

import com.google.gson.annotations.SerializedName;

public class ChkBorrower {

    @SerializedName("sisa_tagihan")
    private Long mSisaTagihan;

    public Long getSisaTagihan() {
        return mSisaTagihan;
    }

    public void setSisaTagihan(Long sisaTagihan) {
        mSisaTagihan = sisaTagihan;
    }

}
