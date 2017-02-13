package app.media.opp.partytonight.presentation.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.media.opp.partytonight.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TextSection extends LinearLayout {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvContent)
    TextView tvContent;

    private String mTitle = "";
    private String mContent = "";

    public TextSection(Context context) {
        super(context);

        configureView(context);
    }

    public TextSection(Context context, AttributeSet attrs) {
        super(context, attrs);

        handleAttributes(context, attrs);
        configureView(context);
    }

    public TextSection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        handleAttributes(context, attrs);
        configureView(context);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextSection, 0, 0);

        try {
            mTitle = array.getString(R.styleable.TextSection_title);
            mContent = array.getString(R.styleable.TextSection_content);
        } finally {
            array.recycle();
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }

        mTitle = title;
        tvTitle.setText(title);
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
        }

        mContent = content;
        tvTitle.setText(content);
    }

    public void configureView(Context context) {
        inflate(context, R.layout.view_text_section, this);
        ButterKnife.bind(this);

        tvTitle.setText(getTitle());
        tvContent.setText(getContent());
    }
}
