package com.bugmaker.paintingboard.util

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.bugmaker.paintingboard.bean.PaintType

/**
 * @ClassName PaintTypeConstruct
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/13 16:46
 */
object PaintTypeConstruct {


    /**
     * TODO 创建默认画笔
     * 参考：https://www.jianshu.com/p/9b43ad869c23
     * @return
     */
    fun getDefaultPaint():Paint{
        return Paint().apply {
            //描边
            style = Paint.Style.STROKE
            //笔触风格
            strokeCap = Paint.Cap.ROUND
            //设置接合处的状态
            strokeJoin = Paint.Join.ROUND

        }
    }


    fun constructPaint(paint: Paint,paintType: PaintType = PaintType.Normal):Paint{
        val effects = arrayOfNulls<PathEffect>(5)
        effects[0] = null
        //拐角处变得圆滑，参数决定圆滑程度
        effects[1] = CornerPathEffect(30f)
        //线段上会多出很多杂点
        effects[2] = DiscretePathEffect(3.0f, 5.0f)
        //绘制虚线，数据设置各个点之间的间隔，第二个参数是偏移量，用来设置动态效果
        effects[3] = DashPathEffect(floatArrayOf(20f, 10f, 5f, 10f), 0f)
        effects[4] = ComposePathEffect(effects[3], effects[1])

        return paint.apply {
            //pathEffect = effects[4]
        }
    }

}
