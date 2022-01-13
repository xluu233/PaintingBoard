package com.bugmaker.paintingboard

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bugmaker.paintingboard.bean.PaintSet
import com.bugmaker.paintingboard.databinding.FragmentDrawBinding
import com.bugmaker.paintingboard.dialog.DeleteDialog
import com.bugmaker.paintingboard.dialog.LayerDialog
import com.bugmaker.paintingboard.dialog.PaintDialog
import com.bugmaker.paintingboard.dialog.SaveDialog
import com.bugmaker.paintingboard.util.*
import com.bugmaker.paintingboard.view.DrawInterface
import com.hi.dhl.binding.viewbind


class DrawFragment : Fragment(R.layout.fragment_draw) {

    companion object{
        @JvmStatic
        fun newInstance() = DrawFragment()
    }

    private val TAG = "DrawFragment"
    private val binding : FragmentDrawBinding by viewbind()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {

        //新建画布
        binding.createCanvas.bringToFront()
        binding.createCanvas.click {
            //CanvasCreateDialog.getInstance().show(parentFragmentManager,"create")
            //initLayoutSize()
        }

        binding.surfaceView.setListener(object :DrawInterface{
            override fun refreshBack() {
                //刷新撤销图标
                refreshBackIcon()
            }
        })

        binding.ivBackLeft.click {
            Log.d(TAG, "initView: ${binding.surfaceView.canBack}")
            if (binding.surfaceView.canBack){
                binding.surfaceView.back()
                refreshBackIcon()
            }
        }

        binding.ivBackRight.click {
            if (binding.surfaceView.canReBack){
                binding.surfaceView.reBack()
                refreshBackIcon()
            }
        }

        //保存
        binding.ivSave.click {
            SaveDialog.instance.show(parentFragmentManager,"save")
        }

        //选择画笔
        binding.ivPaint.click {
            PaintDialog.instance.show(parentFragmentManager,"paint")
            PaintDialog.instance.setCurPaint(binding.surfaceView.curPaint)
        }

        //橡皮
        binding.ivDelete.click {
            DeleteDialog.instance.show(parentFragmentManager,"delete")
        }

        //图层
        binding.ivLayer.click {
            LayerDialog.instance.show(parentFragmentManager,"layer")
        }

        //更多工具
        binding.ivMore.click {

        }

    }


    private fun initData() {
        LiveDataBus.with<PaintSet>(Constant.SetPaintParams).observe(this, Observer {
            binding.surfaceView.setPaint(it)
        })

    }

    private fun refreshBackIcon() {
        binding.ivBackLeft.alpha = if (binding.surfaceView.canBack) 1.0f else 0.5f
        binding.ivBackRight.alpha = if (binding.surfaceView.canReBack) 1.0f else 0.5f
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