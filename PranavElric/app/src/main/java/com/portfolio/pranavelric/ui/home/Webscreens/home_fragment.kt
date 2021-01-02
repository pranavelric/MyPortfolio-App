package com.portfolio.pranavelric.ui.home.Webscreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.portfolio.pranavelric.R
import com.portfolio.pranavelric.ui.home.WebviewActivity
import com.portfolio.pranavelric.utils.Constants
import com.portfolio.pranavelric.utils.CoroutinesHelper
import com.portfolio.pranavelric.utils.loadWeb
import com.portfolio.pranavelric.utils.show
import kotlinx.android.synthetic.main.fragment_home_fragment.view.*


class home_fragment : Fragment(R.layout.fragment_home_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        CoroutinesHelper.delayWithMain(1000) {
            view.progress_circular.show()
        }
        val networkConnection = (activity as WebviewActivity).networkConnection

        networkConnection.observe(viewLifecycleOwner, Observer {
            if (it) {

                view.noint_lay.visibility = View.GONE
                view.web_view.visibility = View.VISIBLE

                // loadWeb(view,)
                context?.let { it1 ->
                    loadWeb(
                        it1,
                        view,
                        view.web_view,
                        view.refresh,
                        view.progress_circular,
                        Constants.SITE_URL
                    )
                }
                view.refresh.setOnRefreshListener {
                    context?.let { it1 ->
                        loadWeb(
                            it1,
                            view,
                            view.web_view,
                            view.refresh,
                            view.progress_circular,
                            Constants.SITE_URL
                        )
                    }
                }

            } else {
                view.noint_lay.visibility = View.VISIBLE
                view.web_view.visibility = View.GONE
            }
        })


    }


}

