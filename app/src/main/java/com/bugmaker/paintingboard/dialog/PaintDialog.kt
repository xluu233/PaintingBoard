package com.bugmaker.paintingboard.dialog

import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.SeekBar
import com.bugmaker.paintingboard.bean.PaintSet
import com.bugmaker.paintingboard.bean.PaintType
import com.bugmaker.paintingboard.databinding.DialogCreateCanvasBinding
import com.bugmaker.paintingboard.databinding.DialogPaintBinding
import com.bugmaker.paintingboard.util.Constant
import com.bugmaker.paintingboard.util.LiveDataBus
import com.bugmaker.paintingboard.util.dp
import com.bugmaker.paintingboard.util.screenHeight
import kotlin.math.roundToInt

/**
 * @ClassName CanvasCreateDialog
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 16:25
 */
class PaintDialog : BaseBottomSheetDialogFragment<DialogPaintBinding>(DialogPaintBinding::inflate) {

    companion object{

        val instance:PaintDialog by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PaintDialog()
        }

        //单位px
        var maxPaintSize:Int = 200
        var minPaintSize:Int = 1

        var size = minPaintSize
        var alpha:Int = 255

        var curColor:Int ?= null
        var curPaintType = PaintType.Normal
    }

    init {
        cancel = true
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = screenHeight*4/5
        peekHeight = 300.dp.toInt()
    }

    override suspend fun initView() {
        Log.d(TAG, "initView")

        binding.paintSeekbar1.apply {
            max = 100
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                //seekbar状态改变
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    refreshPaintSize(progress)
                }

                //按住seekbar触发
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                //放开seekbar触发
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    refreshPaintSize(seekBar!!.progress)
                }
            })
            progress = 10
        }


        binding.paintSeekbar2.apply {
            max = 255
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                //seekbar状态改变
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    refreshPaintAlpha(progress)
                }

                //按住seekbar触发
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                //放开seekbar触发
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    refreshPaintAlpha(seekBar!!.progress)
                }
            })
            progress = 255
        }

        binding.colorPickView.setOnColorChangeListener {
            curColor = it
            //val color = ColorDrawable(curColor)
        }


    }

    //刷新画笔大小textview
    private fun refreshPaintSize(progress: Int) {
        Log.d(TAG, "refreshPaintSize: $progress")
        size = minPaintSize + ((progress.toFloat()/100f)*maxPaintSize).roundToInt()
        if (size > maxPaintSize) size = maxPaintSize
        if (size < minPaintSize) size = minPaintSize
        binding.paintSizeText.text = "${size}px"
    }

    private fun refreshPaintAlpha(progress: Int){
        Log.d(TAG, "refreshPaintAlpha: $progress")
        alpha = progress
        if (alpha > 255) alpha = 255
        if (alpha < 0) alpha = 0
        var tempAlpha:Double  = alpha.toDouble()/255
        if (tempAlpha > 1) tempAlpha = 1.0
        if (tempAlpha < 0) tempAlpha = 0.0
        binding.paintAlphaText.text = "${(tempAlpha*100).toInt()}%"
    }


    override suspend fun initData() {

    }

    override fun onDismiss(dialog: DialogInterface) {
        val paintSet = PaintSet(size = size, alpha = alpha, color = curColor, paintType = curPaintType)
        LiveDataBus.with<PaintSet>(Constant.SetPaintParams).postData(paintSet)
        super.onDismiss(dialog)
    }


}