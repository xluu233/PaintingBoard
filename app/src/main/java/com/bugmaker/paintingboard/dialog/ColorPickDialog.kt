package com.bugmaker.paintingboard.dialog

import android.view.Gravity
import android.view.ViewGroup
import com.bugmaker.paintingboard.R
import com.bugmaker.paintingboard.databinding.DialogColorPickBinding
import com.bugmaker.paintingboard.util.LiveDataBus
import com.bugmaker.paintingboard.util.click
import com.bugmaker.paintingboard.util.dp

/**
 * @ClassName ColorPickDialog
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/14 16:33
 */
class ColorPickDialog(val event:String) : BaseDialogFragment<DialogColorPickBinding>(DialogColorPickBinding::inflate) {

    init {
        initParams(
            cancel = true,
            width = 300.dp.toInt(),
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity = Gravity.CENTER,
            //anim =  R.style.dialogAnimation_center
        )
    }

    companion object{

        @JvmStatic
        fun getInstance(tag:String) = ColorPickDialog(tag)

    }

    private var color:Int = 0

    override suspend fun initView() {

        binding.chooseColor.click {
            LiveDataBus.with<Int>(event).postData(color)
            dismiss()
        }

        binding.colorPickView.setOnColorChangeListener {
            color = it
        }

    }

    override suspend fun initData() {
        color = binding.colorPickView.currentColor

    }
}