package com.bugmaker.paintingboard.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class PaintPreview : androidx.appcompat.widget.AppCompatImageView {

    private val TAG = "PaintPreview"
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private var paint:Paint ?= null
    private var path:Path ?= null

    private fun init(attrs: AttributeSet?, defStyle: Int) {

    }

    fun setPaint(paint: Paint?){
        this.paint = paint
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d(TAG, "onLayout: $changed,left:$left,right:$right,top:$top,bottom:$bottom,x:$x,y:$y")
        super.onLayout(changed, left, top, right, bottom)
        val height = bottom-top
        val width = right-left
        path = Path().apply {
//            moveTo((left-200).toFloat(),(top-50).toFloat())
//            lineTo((left+200).toFloat(),(top-50).toFloat())
            moveTo(120f, (height/2).toFloat())
            lineTo((width-120).toFloat(), (height/2).toFloat())
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (path != null && paint != null){
            canvas.drawPath(path!!, paint!!)
        }
    }

}