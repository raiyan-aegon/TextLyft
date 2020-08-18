package com.mukhtarinc.textlyft.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.mukhtarinc.textlyft.R
import com.mukhtarinc.textlyft.databinding.ActivitySplashScreenBinding
import io.reactivex.Completable.timer
import io.reactivex.Observable.switchOnNext
import io.reactivex.Observable.timer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit


class SplashScreen : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable =  CompositeDisposable()
    lateinit var topAnim : Animation
    lateinit var bottomAnim : Animation
    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        //Initialize the animation variables
        topAnim = AnimationUtils.loadAnimation(this,R.anim.splash_top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.splash_bottom_anim);


        //Attaching animation to image and text
        binding.imageView.animation = topAnim
        binding.appName.animation = bottomAnim
        binding.appDesc.animation = bottomAnim



        //start mainActivity
        startActivity()
    }

    private fun startActivity(){


        //start new activity after 5 Seconds
        compositeDisposable.add(io.reactivex.Observable.timer(5,TimeUnit.SECONDS)
            .subscribeBy(

               onComplete = {

                   //Start mainActivity
                   val intent  = Intent (this,MainActivity::class.java)
                   startActivity(intent)
                   finish()

               }


        ))

    }

    override fun onStop() {
        super.onStop()
        //Dispose the observable
        compositeDisposable.dispose()
    }
}