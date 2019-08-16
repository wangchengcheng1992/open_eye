package com.open.eye.base

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter : FragmentStatePagerAdapter {

    private var fragmentList: List<Fragment>? = ArrayList()
    private var titleList: List<String>? = ArrayList()
    private var fm: FragmentManager

    constructor(fm: FragmentManager, fragmentList: List<Fragment>, titleList: List<String>) : super(fm) {
        this.fragmentList = fragmentList
        this.titleList = titleList
        this.fm = fm
    }

    override fun getItem(position: Int): Fragment? = fragmentList?.get(position)

    override fun getCount(): Int = fragmentList?.size!!

    override fun getPageTitle(position: Int): CharSequence? = titleList?.get(position)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = super.instantiateItem(container, position)
        fm.beginTransaction().show(item as Fragment).commit()
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val fragment = fragmentList?.get(position)
        fm.beginTransaction().hide(fragment!!).commit()
    }
}