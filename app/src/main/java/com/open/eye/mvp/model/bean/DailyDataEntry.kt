package com.open.eye.mvp.model.bean

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
data class DailyDataEntry(
    var itemList: List<Item>,
    var count: Int,// 8
    var total: Int,// 0
    var nextPageUrl: String,// http://baobab.kaiyanapp.com/api/v5/index/tab/feed?date=1565485200000&num=2
    var adExist: Boolean// false
)

data class Item(
    var type: String,// textCard
    var data: Data,
    var tag: String,
    var id: Int,// 0
    var adIndex: Int// -1
)

data class Data(
    var dataType: String,// TextCard
    var id: Int,// 0
    var type: String,// header5
    var text: String,// 星期二
    var subTitle: String,
    var actionUrl: String,
    var adTrack: String,
    var follow: String
)