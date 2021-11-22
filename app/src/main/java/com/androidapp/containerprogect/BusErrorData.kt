package com.androidapp.containerprogect

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal data class BusErrorData(
    @DrawableRes val image: Int? = null,
    @StringRes val textTitle: Int? = null,
    @StringRes val text: Int,
    @StringRes val textBtnError: Int? = null,
)