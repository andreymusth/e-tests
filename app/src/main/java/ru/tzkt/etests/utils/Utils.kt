package ru.tzkt.etests.utils

import java.util.*

/**
 * Created by andrey on 09/05/2018.
 */
val answers: HashMap<Int, Int> = HashMap()


fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start