package com.open.eye.ui.fragment.home

import android.os.Bundle
import com.open.eye.R
import com.open.eye.base.BaseFragment

class RecommendFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): RecommendFragment {
            val fragment = RecommendFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_community

    override fun initView() {

    }

    override fun lazyLoad() {

    }

}