package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;

import app.media.opp.partytonight.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/1/16
 */

public class EditTextAguda extends EditText {

    private static final int RID_COLOR_TEXT_COLOR = R.color.colorTextSignIn;
    private static final int RID_DIMEN_PADDING = R.dimen.edittext_curved_translucent_padding;
    private static final int RID_DIMEN_HEIGHT = R.dimen.edittext_curved_translucent_height;

    private static final String FONT = "fonts/aguda.ttf";

    public EditTextAguda(Context context) {
        super(context);

        configureView(context);
    }

    public EditTextAguda(Context context, AttributeSet attrs) {
        super(context, attrs);

        configureView(context);
    }

    public EditTextAguda(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        configureView(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        updateHeight(super.getContext());
    }

    public void updateHeight(Context context) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = (int) context.getResources().getDimension(RID_DIMEN_HEIGHT);
        setLayoutParams(lp);
    }

    private void configureView(Context context) {
        int padding = (int) context.getResources().getDimension(RID_DIMEN_PADDING);

        setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));

        setTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));

        setHintTextColor(ContextCompat.getColor(context, RID_COLOR_TEXT_COLOR));

        setPadding(padding, padding, padding, padding);

        setMaxLines(1);
    }
}
