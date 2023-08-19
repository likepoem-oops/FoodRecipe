package com.czp.recipe.util

import android.content.Context
import android.util.Log
import android.widget.Toast

fun showToastShort(context: Context, message: String) {
    Toast.makeText(context, "Get Recipe failed: $message", Toast.LENGTH_SHORT).show()
}
fun showToastLong(context: Context, message: String) {
    Toast.makeText(context, "Get Recipe failed: $message", Toast.LENGTH_LONG).show()
}

fun showLog(message: String) {
    Log.v("dao_fu", message)
}