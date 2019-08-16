package com.open.eye.mvp.contract

import com.open.eye.base.IBaseView
import com.open.eye.base.IPresenter

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
interface HomeContract {
    interface View : IBaseView{

    }

    interface Presenter : IPresenter<View>{

        //获取日报列表
        fun getDailyList()
    }
}