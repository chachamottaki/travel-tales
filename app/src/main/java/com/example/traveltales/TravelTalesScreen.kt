package com.example.traveltales

import androidx.annotation.StringRes

enum class TravelTalesScreen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Home(title = R.string.home),
    Entries(title = R.string.entries)
}
