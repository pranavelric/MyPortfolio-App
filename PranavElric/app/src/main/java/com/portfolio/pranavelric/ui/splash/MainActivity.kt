package com.portfolio.pranavelric.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.portfolio.pranavelric.R
import com.portfolio.pranavelric.ui.home.WebviewActivity
import com.portfolio.pranavelric.utils.CoroutinesHelper
import com.portfolio.pranavelric.utils.setFullScreen
import com.portfolio.pranavelric.utils.setFullScreenForNotch
import com.portfolio.pranavelric.utils.transitionAnimationBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        setFullScreen()
        setFullScreenForNotch()




        CoroutinesHelper.delayWithMain(3000L) {
            Intent(this@MainActivity, WebviewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this, this@MainActivity.transitionAnimationBundle())
            }
        }

    }


}
