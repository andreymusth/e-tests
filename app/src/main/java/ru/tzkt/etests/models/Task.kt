package ru.tzkt.etests.models

import java.io.Serializable

/**
 * Created by andrey on 01/05/2018.
 */
data class Task(val taskText: String, val variants: List<String>, var rightAnswerNum: Int) : Serializable