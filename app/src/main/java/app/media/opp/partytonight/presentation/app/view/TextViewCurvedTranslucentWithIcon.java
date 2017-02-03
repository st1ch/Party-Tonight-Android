package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import app.media.opp.partytonight.R;

public class TextViewCurvedTranslucentWithIcon extends TextView {

    private static final int RID_BACKGROUND_DRAWABLE = R.drawable.edittext_curved_translucent_with_icon;
    private static final int RID_COLOR_TEXT_COLOR = R.color.colorTextSignIn;
    private static final String FONT = "fonts/aguda.ttf";

    public TextViewCurvedTranslucentWithIcon(Context context) {
        super(context);

        configureView(context);
    }

    public TextViewCurvedTranslucentWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public TextViewCurvedTranslucentWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    public void configureView(Context context) {
        setBackground(ContextCompat.getDrawable(context, RID_BACKGROUND_DRAWABLE));

        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));

        setTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));

        setHintTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));
    }
}
