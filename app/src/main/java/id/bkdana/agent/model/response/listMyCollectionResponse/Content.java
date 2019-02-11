
package id.bkdana.agent.model.response.listMyCollectionResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("list_mycollection")
    private List<ListMycollection> mListMycollection;

    public List<ListMycollection> getListMycollection() {
        return mListMycollection;
    }

    public void setListMycollection(List<ListMycollection> listMycollection) {
        mListMycollection = listMycollection;
    }

}
