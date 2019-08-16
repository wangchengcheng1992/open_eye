package com.open.eye.mvp.contract

import com.open.eye.base.IBaseView
import com.open.eye.base.IPresenter
import com.open.eye.mvp.model.bean.DailyDataEntry

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
interface HomeContract {
    interface View : IBaseView{
        fun getDailySuccess(dataEntry: DailyDataEntry)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter : IPresenter<View>{

        //获取日报列表
        fun getDailyList(map: HashMap<String, String>)
    }
}