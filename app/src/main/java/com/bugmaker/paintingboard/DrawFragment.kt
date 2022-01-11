package com.bugmaker.paintingboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bugmaker.paintingboard.databinding.FragmentDrawBinding
import com.bugmaker.paintingboard.dialog.CanvasCreateDialog
import com.bugmaker.paintingboard.util.click
import com.bugmaker.paintingboard.util.screenHeight
import com.bugmaker.paintingboard.util.screenWidth
import com.hi.dhl.binding.viewbind

class DrawFragment : Fragment(R.layout.fragment_draw) {

    companion object{
        @JvmStatic
        fun newInstance() = DrawFragment()
    }

    private val binding : FragmentDrawBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        binding.createCanvas.bringToFront()
        binding.createCanvas.click {
            //CanvasCreateDialog.getInstance().show(parentFragmentManager,"create")
            //initLayoutSize()
        }

    }

    /**
     * TODO 初始化布局大小
     */
    fun initLayoutSize(){
        binding.surfaceView.apply {
            //initSize()
            val layoutParam = ViewGroup.LayoutParams(screenWidth, screenHeight-150)
            this.layoutParams = layoutParam
        }

    }

}