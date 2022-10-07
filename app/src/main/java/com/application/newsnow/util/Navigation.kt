package com.application.newsnow.util

import androidx.fragment.app.Fragment

sealed class Navigation {
    data class ToFragment(val fragment: Fragment) : Navigation()
}
