package com.open.eye.ui.fragment

import android.os.Bundle
import com.open.eye.R
import com.open.eye.base.BaseFragment

class NoticeFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): NoticeFragment {
            val fragment = NoticeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_notice

    override fun initView() {
    }

    override fun lazyLoad() {
    }

}