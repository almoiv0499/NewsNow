package com.application.newsnow.fragment.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.application.newsnow.R
import com.application.newsnow.util.Navigation
import com.application.newsnow.viewmodel.base.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.liveDataNavigation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navigation: Navigation) {
        if (navigation is Navigation.ToFragment) {
            navigate(navigation.fragment)
        }
    }

    private fun navigate(fragment: Fragment) {
        activity?.let {
            it.supportFragmentManager.commit {
                add(R.id.news_fragment_container, fragment)
                addToBackStack(null)
            }
        }
    }

}