package com.open.eye.net

/**
 * @author: witness
 * created: 2019-08-16
 * desc:
 */
class BaseResponse<T>(val code :Int,
                      val msg:String,
                      val data:T)