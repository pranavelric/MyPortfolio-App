package com.portfolio.pranavelric.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home_fragment.*
import kotlinx.android.synthetic.main.fragment_home_fragment.view.*


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}


fun Context.transitionAnimationBundle(): Bundle {

    return ActivityOptions.makeCustomAnimation(
        this,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    ).toBundle()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}


fun Activity.setFullScreen() {


    this.window.decorView.setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_IMMERSIVE
    )


}

fun Activity.setFullScreenWithBtmNav() {


    this.window.decorView.setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_IMMERSIVE
    )


}

fun Context.share(link: String?) {

    val shareIntent = Intent()

    shareIntent.action = Intent.ACTION_SEND
    shareIntent.setType("text/plain");




    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "Check out the App at: https://play.google.com/store/apps/developer?id=Pranav+Elric"
    )
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Pranav Elric");


    this.startActivity(Intent.createChooser(shareIntent, "Share to"))

}


fun Activity.setFullScreenForNotch() {
    this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        this.window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
    }

}


fun Activity.adjustToolBarMarginForNotchDevices(toolbar: Toolbar) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val windowInsets = this.window.decorView.rootWindowInsets
        if (windowInsets != null) {
            val displayCutout = windowInsets.displayCutout
            if (displayCutout != null) {
                val safeInsetTop = displayCutout.safeInsetTop
                val newLayoutParams = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                newLayoutParams.setMargins(0, safeInsetTop, 0, 0)
                toolbar.layoutParams = newLayoutParams

            }
        }

    }

}


fun ViewPager2.setShowSideItems2(pageMarginPx: Int, offsetPx: Int) {

    clipToPadding = false
    clipChildren = false
    offscreenPageLimit = 3


    setPageTransformer { page, position ->

        val myOffset: Float = position * (-(2 * offsetPx + pageMarginPx))
        if (position < -1) {
            page.translationX = -myOffset

        } else if (position <= 1) {
            val scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f))
            page.translationX = myOffset
            page.scaleY = scaleFactor
            page.alpha = scaleFactor
        } else {
            page.alpha = 0F;
            page.translationX = myOffset;
        }

    }


}


@SuppressLint("SetJavaScriptEnabled")
fun loadWeb(
    context: Context,
    view: View,
    web_view: WebView,
    swipeRefreshLayout: SwipeRefreshLayout,
    progressBar: ProgressBar,
    url: String
) {


    val webSettings = web_view.settings
    webSettings.javaScriptEnabled = true
    webSettings.builtInZoomControls = false
    web_view.loadUrl(url)
    web_view.webViewClient = object : WebViewClient() {


        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith("mailto:")) {
                //Handle mail Urls
                context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse(url)))
            } else if (url.startsWith("tel:")) {
                //Handle telephony Urls
                context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(url)))
            } else {
                view.loadUrl(url)
            }
            return true
        }


        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val uri: Uri? = request?.url
            if (uri.toString().startsWith("mailto:")) {
                //Handle mail Urls
                context.startActivity(Intent(Intent.ACTION_SENDTO, uri))
            } else if (uri.toString().startsWith("tel:")) {
                //Handle telephony Urls
                context.startActivity(Intent(Intent.ACTION_DIAL, uri))
            } else {
                //Handle Web Urls
                view?.loadUrl(uri.toString())
            }
            return true
        }

        override fun onPageStarted(mView: WebView?, url: String?, favicon: Bitmap?) {
        if(!progressBar.isVisible)
            progressBar.show()
            super.onPageStarted(mView, url, favicon)
            Log.d("RRR", "onPageStarted: ")
        }

        override fun onPageFinished(mView: WebView?, url: String?) {
            Log.d("RRR", "onPageFinished: ")
            progressBar.hide()
            swipeRefreshLayout.isRefreshing = false

            super.onPageFinished(mView, url)
        }


        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceivedError(
            mView: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {

            val errorMessage = "Got Error! ${error.description} ${error.errorCode}"
            view.snackbar(errorMessage)
            progressBar.hide()
            swipeRefreshLayout.isRefreshing = false
            super.onReceivedError(mView, request, error)
        }

    }




    web_view.setOnKeyListener { v, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP
            && web_view.canGoBack()
        ) {
            web_view.goBack()
            return@setOnKeyListener true
        }
        false
    }


}

fun Context.openInBrowser(url: String) {
    var myUrl = url
    if (!myUrl.startsWith("http://") && !myUrl.startsWith("https://"))
        myUrl = "http://$url";
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(myUrl))
    this.startActivity(browserIntent)

}




