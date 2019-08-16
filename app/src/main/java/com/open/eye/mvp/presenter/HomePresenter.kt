package com.open.eye.mvp.presenter

import com.open.eye.net.exception.ExceptionHandle
import com.open.eye.base.BasePresenter
import com.open.eye.mvp.contract.HomeContract
import com.open.eye.mvp.model.HomeModel

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {


    private val homeModel by lazy { HomeModel() }

    override fun getDailyList(map: HashMap<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = homeModel.getDailyList(map)?.subscribe({ dailyData ->
            mRootView?.getDailySuccess(dailyData)
        }, { throwable ->
            //处理异常
            mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
        })
        disposable?.let { addSubscription(it) }
    }

}