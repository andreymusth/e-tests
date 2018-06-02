package ru.tzkt.etests.utils

import android.content.Context
import android.preference.PreferenceManager
import java.util.*

/**
 * Created by andrey on 09/05/2018.
 */
val answers: HashMap<Int, Int> = HashMap()


fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start

fun getCurrentEmail(context: Context): String {

    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    return prefs.getString("defaultEmail", "")

}