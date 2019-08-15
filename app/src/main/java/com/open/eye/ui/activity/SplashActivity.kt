package com.open.eye.ui.activity

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.ScaleAnimation
import com.open.eye.MyApplication
import com.open.eye.R
import com.open.eye.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    private var textTypeface: Typeface?=null
    private var descTypeFace: Typeface?=null

    private var alphaAnimation: AlphaAnimation? = null
    private var shadowAnimation: AlphaAnimation? = null
    private var scaleAnimation: ScaleAnimation? = null
    private var textAnimation: AlphaAnimation? = null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    override fun layoutId(): Int = R.layout.activity_splash

    override fun initData() {

    }

    @SuppressLint("CheckResult")
    override fun initView() {

        tv2.typeface = textTypeface
        tv1.typeface = descTypeFace

        //图标的渐变动画
        alphaAnimation = AlphaAnimation(0.3f, 2.0f)
        alphaAnimation?.duration = 3500
        alphaAnimation?.fillAfter = true

        //黑色阴影层渐变动画
        shadowAnimation = AlphaAnimation(1.0f, 0.3f)
        shadowAnimation?.duration = 2000
        shadowAnimation?.fillAfter = true

        //图片的缩放动画
        scaleAnimation = ScaleAnimation(
            1.0f, 1.1f,
            1.0f, 1.1f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation?.duration = 3000
        scaleAnimation?.fillAfter = true

        //文字的渐变动画
        textAnimation = AlphaAnimation(0.0f, 1.0f)
        textAnimation?.duration = 2500
        textAnimation?.fillAfter = true

        iv_icon.startAnimation(alphaAnimation)

        Observable
            .timer(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Long) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {
                    tv1.text = "每日精选视频推荐，让你大开眼界"
                    tv2.text = "Daily appetizers for your eyes.Bon eyepetit"


                    view_shadow.startAnimation(shadowAnimation)
                    iv_bg.startAnimation(scaleAnimation)
                    tv1.startAnimation(textAnimation)
                    tv2.startAnimation(textAnimation)
                }

            })
    }

    override fun start() {

    }

}

