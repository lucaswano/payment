package com.project.ui.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.graphics.Typeface
import com.base.R


class UPSTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UPSTextView)
        val fontValue = typedArray.getText(R.styleable.UPSTextView_font_values)
        typedArray.recycle()
        if (fontValue != null) {
            val nutinoFont = Typeface.createFromAsset(context.assets, "fonts/$fontValue")
            this.setTypeface(nutinoFont)
        } else {
            val nutinoFont = Typeface.createFromAsset(context.assets, "fonts/Nunito-Regular.ttf")
            this.setTypeface(nutinoFont)
        }
    }


    private fun onInitTextView(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UPSTextView)
        val fontValue = typedArray.getText(R.styleable.UPSTextView_font_values)
        typedArray.recycle()
        if (fontValue != null) {
            val nutinoFont = Typeface.createFromAsset(context.assets, "fonts/$fontValue")
            this.setTypeface(nutinoFont)
        } else {
            val nutinoFont = Typeface.createFromAsset(context.assets, "fonts/Nunito-Regular.ttf")
            this.setTypeface(nutinoFont)
        }

    }

}