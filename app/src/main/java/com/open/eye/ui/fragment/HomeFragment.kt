package com.open.eye.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.open.eye.R
import com.open.eye.base.BaseFragment
import com.open.eye.base.ViewPagerAdapter
import com.open.eye.mvp.model.bean.TabEntity
import com.open.eye.ui.fragment.home.DailyFragment
import com.open.eye.ui.fragment.home.FindFragment
import com.open.eye.ui.fragment.home.RecommendFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_tab.*
import java.util.ArrayList

class HomeFragment : BaseFragment() {

    private var mTitle: String? = null

    private val mTitles = arrayOf("发现", "推荐", "日报")
    private val mIcon = intArrayOf(0, 0, 0)

    private val mFragmentList = ArrayList<Fragment>()

    private val mTabEntities = ArrayList<CustomTabEntity>()

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        initTab()
    }

    override fun lazyLoad() {

    }

    private fun initTab() {
        val findFragment = FindFragment.getInstance("发现")
        val recommendFragment = RecommendFragment.getInstance("推荐")
        val dailyFragment = DailyFragment.getInstance("日报")
        mFragmentList.add(findFragment)
        mFragmentList.add(recommendFragment)
        mFragmentList.add(dailyFragment)
        view_pager.adapter = ViewPagerAdapter(childFragmentManager, mFragmentList, mTitles.toList())

        (0 until mTitles.size)
            .mapTo(mTabEntities) { TabEntity(mTitles[it], mIcon[it], mIcon[it]) }
        //为Tab赋值
        tab.setTabData(mTabEntities, this.activity, R.id.view_pager, mFragmentList)
        tab.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onNoTitleClick(position: Int) {

            }

            override fun onTabSelect(position: Int) {
                //切换Fragment
//                switchFragment(position)
                view_pager.currentItem = position
            }

            override fun onTabReselect(position: Int) {

            }
        })

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                tab.currentTab = position
            }

        })

        view_pager.currentItem = 1

    }

}