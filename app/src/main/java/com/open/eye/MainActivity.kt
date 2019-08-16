package com.open.eye

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.gyf.immersionbar.ImmersionBar.getStatusBarHeight
import com.gyf.immersionbar.ktx.immersionBar
import com.open.eye.MyApplication.Companion.context
import com.open.eye.base.BaseActivity
import com.open.eye.mvp.model.bean.TabEntity
import com.open.eye.ui.fragment.CommunityFragment
import com.open.eye.ui.fragment.HomeFragment
import com.open.eye.ui.fragment.MineFragment
import com.open.eye.ui.fragment.NoticeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("首页", "社区", "", "通知", "我的")
    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(
        R.mipmap.ic_tab_strip_icon_feed,
        R.mipmap.ic_tab_strip_icon_follow,
        R.mipmap.publish_add,
        R.mipmap.ic_tab_strip_icon_category,
        R.mipmap.ic_tab_strip_icon_profile
    )
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(
        R.mipmap.ic_tab_strip_icon_feed_selected,
        R.mipmap.ic_tab_strip_icon_follow_selected,
        R.mipmap.publish_add,
        R.mipmap.ic_tab_strip_icon_category_selected,
        R.mipmap.ic_tab_strip_icon_profile_selected
    )

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mCommunityFragment: CommunityFragment? = null
    private var mNoticeFragment: NoticeFragment? = null
    private var mMineFragment: MineFragment? = null

    //默认为0
    private var mIndex = 0

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    override fun initData() {
    }

    override fun initView() {
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(true)
            navigationBarColor(R.color.colorPrimary)
        }
        ll.setPadding(ll.paddingLeft, ll.paddingTop + getStatusBarHeight(this),
            ll.paddingRight, ll.paddingBottom)
    }

    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onNoTitleClick(position: Int) {

            }

            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 //首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1 //社区
            -> mCommunityFragment?.let {
                transaction.show(it)
            } ?: CommunityFragment.getInstance(mTitles[position]).let {
                mCommunityFragment = it
                transaction.add(R.id.fl_container, it, "community")
            }
            3 //通知
            -> mNoticeFragment?.let {
                transaction.show(it)
            } ?: NoticeFragment.getInstance(mTitles[position]).let {
                mNoticeFragment = it
                transaction.add(R.id.fl_container, it, "notice")
            }
            4 //我的
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }
            else -> {
            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mCommunityFragment?.let { transaction.hide(it) }
        mNoticeFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun start() {
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(MyApplication.context, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
