package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/3/16
 */


public class TextViewAgudaBold extends TextView {

    private static final String FONT = "fonts/aguda_bold.ttf";

    public TextViewAgudaBold(Context context) {
        super(context);

        configureView(context);
    }

    public TextViewAgudaBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public TextViewAgudaBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    public void configureView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));
    }
}