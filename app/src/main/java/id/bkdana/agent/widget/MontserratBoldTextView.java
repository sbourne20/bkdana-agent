package id.bkdana.agent.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MontserratBoldTextView extends AppCompatTextView {

    public MontserratBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public MontserratBoldTextView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Montserrat-Bold.otf"));
    }
}
