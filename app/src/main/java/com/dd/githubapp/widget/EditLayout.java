package com.dd.githubapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dd.githubapp.R;

/**
 * GithubApp
 *
 * @author daidong
 */
public class EditLayout extends RelativeLayout {

    private ImageView ivIcon;

    private TextView tvHint;

    public EditLayout(Context context) {
        this(context, null);
    }

    public EditLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_edit, this);
        ivIcon = findViewById(R.id.iv_edit);
        tvHint = findViewById(R.id.tv_hint);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditLayout);
        Drawable d = a.getDrawable(R.styleable.EditLayout_editIcon);
        if (d != null) {
            ivIcon.setImageDrawable(d);
        }
        String hint = a.getString(R.styleable.EditLayout_editHint);
        if (!TextUtils.isEmpty(hint)) {
            tvHint.setText(hint);
        }
        a.recycle();
    }
}
