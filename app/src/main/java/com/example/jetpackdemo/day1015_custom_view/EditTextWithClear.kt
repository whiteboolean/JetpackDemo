package com.example.jetpackdemo.day1015_custom_view

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.jetpackdemo.R

/**
 * @Class: EditTextWithClear
 * @Description:
 * @author: Jett
 * @Date: 10/15/20-7:29 PM
 */
class EditTextWithClear @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatEditText(context, attrs, defStyleAttr) {


    private var iconDrawable: Drawable? = null

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.EditTextWithClear,
                0, 0
        ).apply {
            try {
                val iconId = getResourceId(R.styleable.EditTextWithClear_clearIcon, 0)
                if (iconId != 0)
                    iconDrawable = ContextCompat.getDrawable(context, iconId)
            } finally {
                recycle()
            }
        }

    }


    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        toggleClearIcon()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e ->
            iconDrawable?.let {
                if (e.action == MotionEvent.ACTION_UP
                        && e.x > width - it.intrinsicWidth - 20//当前的位置在x里面 ， 只有在离开的时候才有效
                        && e.x < width + 20
                        && e.y > height / 2 - it.intrinsicHeight / 2 - 20
                        && e.y < height / 2 + it.intrinsicHeight / 2 + 20
                ) {
                    text?.clear()
                }
            }
        }
        performClick()
        return super.onTouchEvent(event)
    }


    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        toggleClearIcon()
    }

    private fun toggleClearIcon() {

        val icon = if (isFocused && text?.isNotEmpty() == true) iconDrawable else null

        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null)


    }

}