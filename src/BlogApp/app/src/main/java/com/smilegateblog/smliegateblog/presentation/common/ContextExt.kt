package com.smilegateblog.smliegateblog.presentation.common

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smilegateblog.smliegateblog.R

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showGenericAlertDialog(message: String){
    AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton("OK"){ d, _ ->
            d.cancel()
        }
    }.show()
}

