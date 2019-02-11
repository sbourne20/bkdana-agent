
package id.bkdana.agent.model.response.listSurveyResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("list_peminjam")
    private List<ListPeminjam> mListPeminjam;

    public List<ListPeminjam> getListPeminjam() {
        return mListPeminjam;
    }

    public void setListPeminjam(List<ListPeminjam> listPeminjam) {
        mListPeminjam = listPeminjam;
    }

}
