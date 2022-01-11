package com.bugmaker.paintingboard.dialog

import android.view.Gravity
import android.view.ViewGroup
import com.bugmaker.paintingboard.databinding.DialogCreateCanvasBinding
import com.bugmaker.paintingboard.util.dp

/**
 * @ClassName CanvasCreateDialog
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/10 16:25
 */
class DeleteDialog : BaseBottomSheetDialogFragment<DialogCreateCanvasBinding>(DialogCreateCanvasBinding::inflate) {

    companion object{
        @JvmStatic
        fun getInstance() = DeleteDialog()
    }

    init {
        cancel = true
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        peekHeight = 300.dp.toInt()
    }

    override suspend fun initView() {

    }

    override suspend fun initData() {

    }
}