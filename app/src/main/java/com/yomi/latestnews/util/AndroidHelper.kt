package com.yomi.latestnews.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
object AndroidHelper {
    fun showDialog(ctx: Context, title: String, bodyText: String, postiveTxt: String, negativeTxt: String,
        positiveAction: () -> Unit, negativeAction: () -> Unit) {
        AlertDialog.Builder(ctx)
            .setTitle(title)
            .setMessage(bodyText)
            .setPositiveButton(postiveTxt) { _, _ -> positiveAction }
            .setNegativeButton(negativeTxt) { _, _ -> negativeAction }
            .show()
    }

    fun showToast(ctx: Context, message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }
}