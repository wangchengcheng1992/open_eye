package com.open.eye.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.classic.common.MultipleStatusView

abstract class BaseActivity : AppCompatActivity() {

    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())

        initData()

        initView()

        initListener()

        start()
    }

    abstract fun layoutId(): Int

    abstract fun initData()

    abstract fun initView()

    private fun initListener(){
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }

    abstract fun start()

}