
package id.bkdana.agent.model.response.listSurveyResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ListPeminjam implements Parcelable {

    @SerializedName("date_close")
    private String mDateClose;
    @SerializedName("id_mod_type_business")
    private String mIdModTypeBusiness;
    @SerializedName("id_peminjam")
    private String mIdPeminjam;
    @SerializedName("jml_kredit")
    private String mJmlKredit;
    @SerializedName("Loan_term")
    private String mLoanTerm;
    @SerializedName("nama_peminjam")
    private String mNamaPeminjam;
    @SerializedName("peringkat_pengguna")
    private String mPeringkatPengguna;
    @SerializedName("product_title")
    private String mProductTitle;
    @SerializedName("tgl_approve")
    private String mTglApprove;
    @SerializedName("tgl_transaksi")
    private String mTglTransaksi;
    @SerializedName("total_approve")
    private String mTotalApprove;
    @SerializedName("total_lender")
    private String mTotalLender;
    @SerializedName("total_pinjam")
    private String mTotalPinjam;
    @SerializedName("transaksi_id")
    private String mTransaksiId;
    @SerializedName("transaksi_status")
    private String mTransaksiStatus;
    @SerializedName("type_business_name")
    private String mTypeBusinessName;

    public ListPeminjam() {
    }

    protected ListPeminjam(Parcel in) {
        mDateClose = in.readString();
        mIdModTypeBusiness = in.readString();
        mIdPeminjam = in.readString();
        mJmlKredit = in.readString();
        mLoanTerm = in.readString();
        mNamaPeminjam = in.readString();
        mPeringkatPengguna = in.readString();
        mProductTitle = in.readString();
        mTglApprove = in.readString();
        mTglTransaksi = in.readString();
        mTotalApprove = in.readString();
        mTotalLender = in.readString();
        mTotalPinjam = in.readString();
        mTransaksiId = in.readString();
        mTransaksiStatus = in.readString();
        mTypeBusinessName = in.readString();
    }

    public static final Creator<ListPeminjam> CREATOR = new Creator<ListPeminjam>() {
        @Override
        public ListPeminjam createFromParcel(Parcel in) {
            return new ListPeminjam(in);
        }

        @Override
        public ListPeminjam[] newArray(int size) {
            return new ListPeminjam[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDateClose);
        dest.writeString(mIdModTypeBusiness);
        dest.writeString(mIdPeminjam);
        dest.writeString(mJmlKredit);
        dest.writeString(mLoanTerm);
        dest.writeString(mNamaPeminjam);
        dest.writeString(mPeringkatPengguna);
        dest.writeString(mProductTitle);
        dest.writeString(mTglApprove);
        dest.writeString(mTglTransaksi);
        dest.writeString(mTotalApprove);
        dest.writeString(mTotalLender);
        dest.writeString(mTotalPinjam);
        dest.writeString(mTransaksiId);
        dest.writeString(mTransaksiStatus);
        dest.writeString(mTypeBusinessName);
    }

    public String getDateClose() {
        return mDateClose;
    }

    public void setDateClose(String dateClose) {
        mDateClose = dateClose;
    }

    public String getIdModTypeBusiness() {
        return mIdModTypeBusiness;
    }

    public void setIdModTypeBusiness(String idModTypeBusiness) {
        mIdModTypeBusiness = idModTypeBusiness;
    }

    public String getIdPeminjam() {
        return mIdPeminjam;
    }

    public void setIdPeminjam(String idPeminjam) {
        mIdPeminjam = idPeminjam;
    }

    public String getJmlKredit() {
        return mJmlKredit;
    }

    public void setJmlKredit(String jmlKredit) {
        mJmlKredit = jmlKredit;
    }

    public String getLoanTerm() {
        return mLoanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        mLoanTerm = loanTerm;
    }

    public String getNamaPeminjam() {
        return mNamaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        mNamaPeminjam = namaPeminjam;
    }

    public String getPeringkatPengguna() {
        return mPeringkatPengguna;
    }

    public void setPeringkatPengguna(String peringkatPengguna) {
        mPeringkatPengguna = peringkatPengguna;
    }

    public String getProductTitle() {
        return mProductTitle;
    }

    public void setProductTitle(String productTitle) {
        mProductTitle = productTitle;
    }

    public String getTglApprove() {
        return mTglApprove;
    }

    public void setTglApprove(String tglApprove) {
        mTglApprove = tglApprove;
    }

    public String getTglTransaksi() {
        return mTglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        mTglTransaksi = tglTransaksi;
    }

    public String getTotalApprove() {
        return mTotalApprove;
    }

    public void setTotalApprove(String totalApprove) {
        mTotalApprove = totalApprove;
    }

    public String getTotalLender() {
        return mTotalLender;
    }

    public void setTotalLender(String totalLender) {
        mTotalLender = totalLender;
    }

    public String getTotalPinjam() {
        return mTotalPinjam;
    }

    public void setTotalPinjam(String totalPinjam) {
        mTotalPinjam = totalPinjam;
    }

    public String getTransaksiId() {
        return mTransaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        mTransaksiId = transaksiId;
    }

    public String getTransaksiStatus() {
        return mTransaksiStatus;
    }

    public void setTransaksiStatus(String transaksiStatus) {
        mTransaksiStatus = transaksiStatus;
    }

    public String getTypeBusinessName() {
        return mTypeBusinessName;
    }

    public void setTypeBusinessName(String typeBusinessName) {
        mTypeBusinessName = typeBusinessName;
    }

}
