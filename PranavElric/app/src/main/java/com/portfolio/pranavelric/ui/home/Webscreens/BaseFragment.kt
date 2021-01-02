package com.portfolio.pranavelric.ui.home.Webscreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.portfolio.pranavelric.ui.home.WebviewActivity
import com.portfolio.pranavelric.utils.loadWeb
import kotlinx.android.synthetic.main.fragment_home_fragment.view.*

abstract class BaseFragment(layout: Int, val url: String) : Fragment(layout) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val networkConnection = (activity as WebviewActivity).networkConnection

        networkConnection.observe(viewLifecycleOwner, Observer {
            if (it) {

                view.noint_lay.visibility = View.GONE
                view.web_view.visibility = View.VISIBLE

                context?.let { it1 ->
                    loadWeb(
                        it1,
                        view,
                        view.web_view,
                        view.refresh,
                        view.progress_circular,
                        url
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
                            url
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