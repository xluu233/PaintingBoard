package com.bugmaker.paintingboard.dialog

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bugmaker.paintingboard.databinding.DialogCreateCanvasBinding
import com.bugmaker.paintingboard.util.*
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * @ClassName CanvasCreateDialog
 * @Description TODO 创建布局
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 16:25
 */
class CreateDialog : BaseBottomSheetDialogFragment<DialogCreateCanvasBinding>(DialogCreateCanvasBinding::inflate) {

    companion object{

        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CreateDialog()
        }

    }

    init {
        cancel = true
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = screenHeight *4/5
        peekHeight = 300.dp.toInt()
        defaultState = BottomSheetBehavior.STATE_EXPANDED

    }


    override suspend fun initView() {
        binding.layoutBackgroundColor.click {
            ColorPickDialog.getInstance(Constant.CreateLayoutColor).show(parentFragmentManager,"color_pick")
        }


    }

    override suspend fun initData() {
        LiveDataBus.with<Int>(Constant.CreateLayoutColor).observe(this, Observer {
            binding.layoutBackgroundColor.setBackgroundColor(it)
        })


    }
}