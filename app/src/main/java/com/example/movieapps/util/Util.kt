package com.example.movieapps.util

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.movieapps.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


fun checkIsMaterialVersion() = true

fun ChipGroup.addNewFilterChips(title: String, layoutInflater: LayoutInflater, checked: Boolean, enable: Boolean = true) {
    if (title.isNotEmpty()) {
        val newChip = layoutInflater.inflate(R.layout.item_chips_filter, this, false) as Chip
        newChip.isChecked = checked
        newChip.text = title
        newChip.isEnabled = enable
        this.addView(newChip)
    }
}

fun AppCompatActivity.simpleToolbarWithHome(toolbar: Toolbar, title_: String = "") {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        title = title_
    }
}

fun AppCompatActivity.applyToolbarMargin(toolbar: Toolbar) {
    toolbar.layoutParams = (toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams).apply {
        topMargin = getStatusBarSize()
    }
}

@SuppressLint("InternalInsetResource", "DiscouragedApi")
private fun AppCompatActivity.getStatusBarSize(): Int {
    val idStatusBarHeight = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (idStatusBarHeight > 0) {
        resources.getDimensionPixelSize(idStatusBarHeight)
    } else 0
}
