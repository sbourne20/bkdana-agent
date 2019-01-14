package id.bkdana.agent.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class MontserratSemiBoldButton extends AppCompatButton {

    public MontserratSemiBoldButton(Context context) {
        super(context);
        init(context);
    }

    public MontserratSemiBoldButton(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Montserrat-SemiBold.otf"));
    }
}
