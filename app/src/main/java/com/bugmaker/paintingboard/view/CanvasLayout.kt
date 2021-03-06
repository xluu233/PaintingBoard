package com.bugmaker.paintingboard.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.bugmaker.paintingboard.R
import com.bugmaker.paintingboard.bean.Line
import com.bugmaker.paintingboard.bean.PaintSet
import com.bugmaker.paintingboard.util.PaintTypeConstruct
import com.bugmaker.paintingboard.util.dp
import com.bugmaker.paintingboard.util.screenHeight
import com.bugmaker.paintingboard.util.screenWidth
import java.util.*

/**
 * @ClassName CanvasLayout
 * @Description TODO 画布
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 15:21
 */
class CanvasLayout: View {

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

    //注意这里是全局对象，因为设计只有一个view
    companion object{

        //画布设置
        var defaultWidth = screenWidth
        var defaultHeight = screenHeight-150

        //画笔设置
        var paintColor = Color.rgb(255, 0, 0)
        //单位px
        var strokeWith:Int = 21

    }

    //全部画线
    private val pathStack = Stack<Line>()
    private val backPathArray = LinkedList<Line>()

    //是否能够撤销
    val canBack:Boolean
        get() = !pathStack.empty()

    //撤销恢复
    val canReBack:Boolean
        get() = !backPathArray.isEmpty()

    //当前画笔
    var curPaint = PaintTypeConstruct.getDefaultPaint().apply {
        color = paintColor
        strokeWidth = strokeWith.toFloat()
    }

    //当前路径
    private var curPath: Path ?= null

    //当前画笔操作
    private var curLine: Line?= null

    private var listener:DrawInterface ?= null

    private fun init() {
        setBackgroundResource(R.color.white)
    }

    fun initSize(width:Int = 0,height:Int = 0){
        if (width == 0){
            defaultWidth = screenWidth
            defaultHeight = screenHeight-150
        }else{
            defaultWidth = width
            defaultHeight = height
        }
        invalidate()
    }


    fun setPaint(paintSet: PaintSet){
        curPaint = PaintTypeConstruct.getDefaultPaint().apply {
            color = paintSet.color ?: curPaint.color
            strokeWidth = paintSet.size.toFloat()
            alpha = paintSet.alpha
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                //创建新的路径
                curPath = Path()
                //当前触摸点就是这个路径的起始点
                curPath?.moveTo(event.x, event.y)
                curPath?.lineTo(event.x, event.y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE ->{
                curPath?.lineTo(event.x, event.y)
                //马上画出来
                invalidate()
            }
            MotionEvent.ACTION_UP ->{
                //保存当前路径
                curLine = Line(curPath!!,curPaint)
                pathStack.push(curLine)
                curPath = null
            }
        }
        listener?.refreshBack()
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = View.MeasureSpec.getSize(heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        Log.d(TAG, "onMeasure: $width,$height")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d(TAG, "onLayout: $changed,left:$left,right:$right,top:$top,bottom:$bottom")
        super.onLayout(changed, left, top, right, bottom)

    }

    private var canvas:Canvas ?= null
    override fun onDraw(canvas: Canvas?) {
        Log.d(TAG, "onDraw")
        this.canvas = canvas
        super.onDraw(canvas)

        if (pathStack.isNotEmpty()){
            pathStack.forEach { line ->
                canvas?.drawPath(line.path,line.paint)
                Log.d(TAG, "onDraw: $line")
            }
        }
        if (curPath != null){
            //正在画画
            canvas?.drawPath(curPath!!,curPaint)
        }else{
            //画完了
        }
    }


    fun setListener(listener:DrawInterface){
        this.listener = listener
    }

    //撤销操作
    fun back(){
        curLine = pathStack.pop()
        backPathArray.offer(curLine)
        //刷新
        invalidate()
        listener?.refreshBack()
    }

    //反撤销操作
    fun reBack(){
        curLine = backPathArray.pollLast()
        pathStack.push(curLine)
        invalidate()
        listener?.refreshBack()
    }

}