package com.open.eye.ui.fragment.home

import android.os.Bundle
import com.open.eye.R
import com.open.eye.base.BaseFragment

class FindFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): FindFragment {
            val fragment = FindFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
    }

    override fun lazyLoad() {
    }

}