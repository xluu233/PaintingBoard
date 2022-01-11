package com.bugmaker.paintingboard.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.FrameLayout
import com.bugmaker.paintingboard.R

/**
 * @ClassName CanvasLayout
 * @Description TODO 画布
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 15:21
 */
class CanvasLayout: FrameLayout {

    private val TAG = "CanvasLayout"

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        setBackgroundResource(R.color.transparent)
        initPaint()
    }


    private fun initPaint() {

    }

    fun addBitmap(bitmap: Bitmap){

    }

}