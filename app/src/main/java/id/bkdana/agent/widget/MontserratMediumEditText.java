package id.bkdana.agent.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MontserratMediumEditText extends AppCompatEditText {

    public MontserratMediumEditText(Context context) {
        super(context);
        init(context);
    }

    public MontserratMediumEditText(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Montserrat-Medium.otf"));
    }
}
