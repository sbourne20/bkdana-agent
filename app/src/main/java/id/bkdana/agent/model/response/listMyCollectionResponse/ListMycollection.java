
package id.bkdana.agent.model.response.listMyCollectionResponse;

import com.google.gson.annotations.SerializedName;

public class ListMycollection {

    @SerializedName("collection_date")
    private String mCollectionDate;
    @SerializedName("hutang_pokok")
    private String mHutangPokok;
    @SerializedName("id_agent")
    private String mIdAgent;
    @SerializedName("id_mod_agent_collection")
    private String mIdModAgentCollection;
    @SerializedName("id_peminjam")
    private String mIdPeminjam;
    @SerializedName("jumlah_pembayaran")
    private String mJumlahPembayaran;
    @SerializedName("master_loan_id")
    private String mMasterLoanId;
    @SerializedName("nama")
    private String mNama;
    @SerializedName("no_ktp")
    private String mNoKtp;
    @SerializedName("product_title")
    private String mProductTitle;
    @SerializedName("sisa_hutang_pokok")
    private String mSisaHutangPokok;
    @SerializedName("status")
    private String mStatus;

    public String getCollectionDate() {
        return mCollectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        mCollectionDate = collectionDate;
    }

    public String getHutangPokok() {
        return mHutangPokok;
    }

    public void setHutangPokok(String hutangPokok) {
        mHutangPokok = hutangPokok;
    }

    public String getIdAgent() {
        return mIdAgent;
    }

    public void setIdAgent(String idAgent) {
        mIdAgent = idAgent;
    }

    public String getIdModAgentCollection() {
        return mIdModAgentCollection;
    }

    public void setIdModAgentCollection(String idModAgentCollection) {
        mIdModAgentCollection = idModAgentCollection;
    }

    public String getIdPeminjam() {
        return mIdPeminjam;
    }

    public void setIdPeminjam(String idPeminjam) {
        mIdPeminjam = idPeminjam;
    }

    public String getJumlahPembayaran() {
        return mJumlahPembayaran;
    }

    public void setJumlahPembayaran(String jumlahPembayaran) {
        mJumlahPembayaran = jumlahPembayaran;
    }

    public String getMasterLoanId() {
        return mMasterLoanId;
    }

    public void setMasterLoanId(String masterLoanId) {
        mMasterLoanId = masterLoanId;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama = nama;
    }

    public String getNoKtp() {
        return mNoKtp;
    }

    public void setNoKtp(String noKtp) {
        mNoKtp = noKtp;
    }

    public String getProductTitle() {
        return mProductTitle;
    }

    public void setProductTitle(String productTitle) {
        mProductTitle = productTitle;
    }

    public String getSisaHutangPokok() {
        return mSisaHutangPokok;
    }

    public void setSisaHutangPokok(String sisaHutangPokok) {
        mSisaHutangPokok = sisaHutangPokok;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
