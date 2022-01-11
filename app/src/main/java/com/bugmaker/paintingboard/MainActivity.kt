package com.bugmaker.paintingboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = DrawFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).show(fragment).commit()

//        CanvasCreateDialog.getInstance().show(supportFragmentManager,"create")
    }


}