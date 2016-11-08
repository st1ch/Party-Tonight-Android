package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/7/16
 */

public class TouchableMapWrapper extends FrameLayout {

    private OnTouchListener mOnTouchListener;

    public TouchableMapWrapper(Context context) {
        super(context);
    }

    public TouchableMapWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchableMapWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mOnTouchListener != null) {
            mOnTouchListener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public interface OnTouchListener {
        public void onTouch(MotionEvent motionEvent);
    }
}
