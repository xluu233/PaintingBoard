package com.bugmaker.paintingboard.dialog

import android.view.Gravity
import android.view.ViewGroup
import com.bugmaker.paintingboard.databinding.DialogCreateCanvasBinding
import com.bugmaker.paintingboard.databinding.DialogLayerBinding
import com.bugmaker.paintingboard.util.dp
import com.bugmaker.paintingboard.util.screenHeight

/**
 * @ClassName CanvasCreateDialog
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 16:25
 */
class LayerDialog : BaseBottomSheetDialogFragment<DialogLayerBinding>(DialogLayerBinding::inflate) {

    companion object{
        @JvmStatic
        fun getInstance() = LayerDialog()
    }

    init {
        cancel = true
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = screenHeight *4/5
        peekHeight = 300.dp.toInt()
    }

    override suspend fun initView() {

    }

    override suspend fun initData() {

    }
}