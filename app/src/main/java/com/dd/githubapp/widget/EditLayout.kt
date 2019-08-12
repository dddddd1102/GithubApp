package com.dd.githubapp.widget

import android.content.Context
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.dd.githubapp.R

/**
 * GithubApp
 *
 * @author daidong
 */
class EditLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private val ivIcon: ImageView

    private val tvHint: TextView

    private val edtInput: EditText

    val editText: String
        get() = edtInput.text.toString()

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_edit, this)
        ivIcon = findViewById(R.id.iv_edit)
        tvHint = findViewById(R.id.tv_hint)
        edtInput = findViewById(R.id.edt_input)
        val a = context.obtainStyledAttributes(attrs, R.styleable.EditLayout)
        val d = a.getDrawable(R.styleable.EditLayout_editIcon)
        if (d != null) {
            ivIcon.setImageDrawable(d)
        }
        val hint = a.getString(R.styleable.EditLayout_editHint)
        if (!TextUtils.isEmpty(hint)) {
            tvHint.text = hint
        }
        val editType = a.getInt(R.styleable.EditLayout_editType, 1)
        if (editType == 0) {
            edtInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
        } else {
            edtInput.inputType = InputType.TYPE_CLASS_TEXT
        }
        a.recycle()
    }


}
