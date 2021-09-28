package com.imadev.noteapp.other

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(isInvisible: Boolean = false) {
    this.visibility = if (isInvisible) View.INVISIBLE else View.GONE
}