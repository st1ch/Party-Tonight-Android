package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import app.media.opp.partytonight.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/2/16
 */

public class TextViewHeader2 extends TextView {

    private static final String FONT = "fonts/aguda.ttf";
    private static final int RID_COLOR_TEXT_COLOR = R.color.colorTextHeader2;

    public TextViewHeader2(Context context) {
        super(context);

        configureView(context);
    }

    public TextViewHeader2(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public TextViewHeader2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    public void configureView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));

        setTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));
    }
}
