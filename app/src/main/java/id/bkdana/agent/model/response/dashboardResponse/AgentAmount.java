package id.bkdana.agent.model.response.dashboardResponse;

import com.google.gson.annotations.SerializedName;

public class AgentAmount {

    @SerializedName("saldo")
    private String mSaldo;


    public String getmSaldo() {
        return mSaldo;
    }

    public void setmSaldo(String mSaldo) {
        this.mSaldo = mSaldo;
    }
}
