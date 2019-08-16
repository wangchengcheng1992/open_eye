package com.open.eye

import com.open.eye.mvp.model.bean.DailyDataEntry
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
interface ApiService {

    @GET("/api/v5/index/tab/feed")
    fun getDailyListData(@QueryMap map: HashMap<String, String>): Observable<DailyDataEntry>
}