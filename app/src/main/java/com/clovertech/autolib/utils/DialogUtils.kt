package com.clovertech.autolib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.clovertech.autolib.R
import kotlinx.coroutines.Job


class DialogUtils {

    companion object {
        private var singleton: DialogUtils? = null
        private lateinit var builder: AlertDialog.Builder

        fun with(context: Context): DialogUtils {
            if (null == singleton)
                singleton = Builder(context).build()
            return singleton as DialogUtils
        }

    }

    constructor()

    @SuppressLint("AlertDialog")
    constructor(context: Context) {
        builder = AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_round_check_24)
    }

    fun showDialog(
        title: String, message: String,
        onConfirm: DialogInterface.OnClickListener,
        onCancel: DialogInterface.OnClickListener? = null)
    {
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.close, onConfirm)
            .setNegativeButton(R.string.cancel, onCancel)
            .show()

    }

    private class Builder(val context: Context) {
        fun build(): DialogUtils {
            return DialogUtils(context)
        }
    }

}