package com.example.framework

import android.widget.Toast

fun BaseFragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}