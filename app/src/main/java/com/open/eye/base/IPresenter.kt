package com.open.eye.base

/**
 * @author: witness
 * created: 2019-08-16
 * desc: IPresenter接口
 */
interface IPresenter <in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()
}