package id.bkdana.agent.Util.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class WorkSansSemiBoldTextView extends AppCompatTextView {

    public WorkSansSemiBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public WorkSansSemiBoldTextView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"WorkSans-SemiBold.otf"));
    }
}
