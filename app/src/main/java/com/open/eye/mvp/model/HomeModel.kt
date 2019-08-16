package com.open.eye.mvp.model

import com.open.eye.rx.scheduler.SchedulerUtils
import com.open.eye.mvp.model.bean.DailyDataEntry
import com.open.eye.net.RetrofitManager
import io.reactivex.Observable

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
class HomeModel {

    /**
     * 获取日报列表
     */
    fun getDailyList(map: HashMap<String, String>) : Observable<DailyDataEntry>? {
        return RetrofitManager.service.getDailyListData(map)
            .compose(SchedulerUtils.ioToMain())
    }
}