package com.bugmaker.paintingboard.dialog

import android.content.DialogInterface
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.SeekBar
import com.bugmaker.paintingboard.databinding.DialogCreateCanvasBinding
import com.bugmaker.paintingboard.databinding.DialogPaintBinding
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
        @JvmStatic
        fun getInstance() = PaintDialog()

        //单位px
        var maxPaintSize:Int = 200
        var minPaintSize:Int = 1

        var size = minPaintSize
        var alpha = 0.6f
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
            max = 100
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
            progress = 60
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
        alpha = progress.toFloat()/100f
        var size = (alpha*100).toInt()
        if (size > 100) size = 100
        if (size < 0) size = 0
        binding.paintAlphaText.text = "${size}%"
    }
    override suspend fun initData() {

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }


}