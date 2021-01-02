package com.portfolio.pranavelric.ui.home.Webscreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.portfolio.pranavelric.R
import com.ramotion.circlemenu.CircleMenuView
import kotlinx.android.synthetic.main.fragment_social_media.*


class SocialMediaFragment : Fragment(R.layout.fragment_social_media) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        circle_menu.setEventListener(object : CircleMenuView.EventListener() {
            override fun onMenuOpenAnimationStart(view: CircleMenuView) {

            }

            override fun onMenuOpenAnimationEnd(view: CircleMenuView) {

            }

            override fun onMenuCloseAnimationStart(view: CircleMenuView) {

            }

            override fun onMenuCloseAnimationEnd(view: CircleMenuView) {

            }

            override fun onButtonClickAnimationStart(view: CircleMenuView, index: Int) {

            }

            override fun onButtonClickAnimationEnd(view: CircleMenuView, index: Int) {


                when (index) {
                    0 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_stackFragment)

                    }
                    1 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_youtubeFragment)

                    }
                    2 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_linkedinFragment)

                    }
                    3 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_githubFragment)


                    }
                    4 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_instagramFragment)


                    }

                    else -> {

                    }

                }



            }

            override fun onButtonLongClick(view: CircleMenuView, index: Int): Boolean {


                return true
            }

            override fun onButtonLongClickAnimationStart(view: CircleMenuView, index: Int) {

            }

            override fun onButtonLongClickAnimationEnd(view: CircleMenuView, index: Int) {

                when (index) {
                    0 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_stackFragment)

                    }
                    1 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_youtubeFragment)

                    }
                    2 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_linkedinFragment)

                    }
                    3 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_githubFragment)


                    }

                    4 -> {
                        findNavController().navigate(R.id.action_socialMediaFragment_to_instagramFragment)

                    }
                    else -> {

                    }

                }


            }
        })


    }
}