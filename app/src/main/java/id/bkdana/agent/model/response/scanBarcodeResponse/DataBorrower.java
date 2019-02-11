
package id.bkdana.agent.model.response.scanBarcodeResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataBorrower implements Parcelable {

    @SerializedName("id_ktp")
    private String mIdKtp;
    @SerializedName("id_peminjam")
    private String mIdPeminjam;
    @SerializedName("Loan_term")
    private String mLoanTerm;
    @SerializedName("nama_peminjam")
    private String mNamaPeminjam;
    @SerializedName("product_title")
    private String mProductTitle;
    @SerializedName("tgl_approve")
    private String mTglApprove;
    @SerializedName("total_pinjaman")
    private String mTotalPinjaman;
    @SerializedName("total_pinjaman_disetujui")
    private String mTotalPinjamanDisetujui;
    @SerializedName("transaksi_id")
    private String mTransaksiId;
    @SerializedName("transaksi_status")
    private String mTransaksiStatus;
    @SerializedName("type_business_name")
    private String mTypeBusinessName;

    public DataBorrower() {
    }

    protected DataBorrower(Parcel in) {
        mIdKtp = in.readString();
        mIdPeminjam = in.readString();
        mLoanTerm = in.readString();
        mNamaPeminjam = in.readString();
        mProductTitle = in.readString();
        mTglApprove = in.readString();
        mTotalPinjaman = in.readString();
        mTotalPinjamanDisetujui = in.readString();
        mTransaksiId = in.readString();
        mTransaksiStatus = in.readString();
        mTypeBusinessName = in.readString();
    }

    public static final Creator<DataBorrower> CREATOR = new Creator<DataBorrower>() {
        @Override
        public DataBorrower createFromParcel(Parcel in) {
            return new DataBorrower(in);
        }

        @Override
        public DataBorrower[] newArray(int size) {
            return new DataBorrower[size];
        }
    };

    public String getIdKtp() {
        return mIdKtp;
    }

    public void setIdKtp(String idKtp) {
        mIdKtp = idKtp;
    }

    public String getIdPeminjam() {
        return mIdPeminjam;
    }

    public void setIdPeminjam(String idPeminjam) {
        mIdPeminjam = idPeminjam;
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

    public String getTotalPinjaman() {
        return mTotalPinjaman;
    }

    public void setTotalPinjaman(String totalPinjaman) {
        mTotalPinjaman = totalPinjaman;
    }

    public String getTotalPinjamanDisetujui() {
        return mTotalPinjamanDisetujui;
    }

    public void setTotalPinjamanDisetujui(String totalPinjamanDisetujui) {
        mTotalPinjamanDisetujui = totalPinjamanDisetujui;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mIdKtp);
        dest.writeString(mIdPeminjam);
        dest.writeString(mLoanTerm);
        dest.writeString(mNamaPeminjam);
        dest.writeString(mProductTitle);
        dest.writeString(mTglApprove);
        dest.writeString(mTotalPinjaman);
        dest.writeString(mTotalPinjamanDisetujui);
        dest.writeString(mTransaksiId);
        dest.writeString(mTransaksiStatus);
        dest.writeString(mTypeBusinessName);
    }
}
