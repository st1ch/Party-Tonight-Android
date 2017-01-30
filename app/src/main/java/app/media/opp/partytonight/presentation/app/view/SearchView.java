package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import app.media.opp.partytonight.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

public class SearchView extends LinearLayout {

    @BindView(R.id.etContent)
    EditText etContent;

    @BindView(R.id.btnFind)
    Button btnFind;

    private String mHint;
    private String mButtonText;

    public SearchView(Context context) {
        super(context);

        configureView(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        handleAttributes(context, attrs);
        configureView(context);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        handleAttributes(context, attrs);
        configureView(context);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchView, 0, 0);

        try {
            mHint = array.getString(R.styleable.SearchView_editTextHint);
            mButtonText = array.getString(R.styleable.SearchView_buttonText);
        } finally {
            array.recycle();
        }
    }

    public String getText() {
        return etContent.getText().toString();
    }

    public void setText(String value) {
        etContent.setText(value);
    }

    public void setFindOnClickListener(OnClickListener l) {
        btnFind.setOnClickListener(l);
    }

    private void configureView(Context context) {
        inflate(context, R.layout.search_view_venue, this);
        ButterKnife.bind(this);

        if (mHint != null) {
            etContent.setHint(mHint);
        }

        if (mButtonText != null) {
            btnFind.setText(mButtonText);
        }
    }
}
