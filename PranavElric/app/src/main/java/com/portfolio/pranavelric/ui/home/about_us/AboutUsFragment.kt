package com.portfolio.pranavelric.ui.home.about_us

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.portfolio.pranavelric.R
import com.portfolio.pranavelric.utils.Constants
import kotlinx.android.synthetic.main.fragment_about_us.*
import kotlinx.android.synthetic.main.fragment_about_us.view.*


class AboutUsFragment : Fragment(R.layout.fragment_about_us) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.instagram.setOnClickListener {
            findNavController().navigate(R.id.action_about_us_to_instagramFragment)

        }
        view.linkedin.setOnClickListener {
            findNavController().navigate(R.id.action_about_us_to_linkedinFragment)

        }
        view.youtube.setOnClickListener {
            findNavController().navigate(R.id.action_about_us_to_youtubeFragment)

        }
        view.stack_overflow.setOnClickListener {
            findNavController().navigate(R.id.action_about_us_to_stackFragment)
        }
        view.github.setOnClickListener {
            findNavController().navigate(R.id.action_about_us_to_githubFragment)

        }


    }
}