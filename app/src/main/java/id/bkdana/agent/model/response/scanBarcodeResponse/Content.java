
package id.bkdana.agent.model.response.scanBarcodeResponse;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content implements Parcelable {

    @SerializedName("data_borrower")
    private List<DataBorrower> mDataBorrower;

    protected Content(Parcel in) {
        mDataBorrower = in.createTypedArrayList(DataBorrower.CREATOR);
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public List<DataBorrower> getDataBorrower() {
        return mDataBorrower;
    }

    public void setDataBorrower(List<DataBorrower> dataBorrower) {
        mDataBorrower = dataBorrower;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mDataBorrower);
    }
}
