package ru.tzkt.etests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentTaskNumber = 0
    private val maxTasksNumber = 2
    private val tasks = arrayListOf(
            Task("I __ driving right now.", arrayListOf("being", "am", "is", "today"), 1),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0),
            Task("I went for a walk every __ ..", arrayListOf("day", "cow", "train", "today"), 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadTask()
        prepareUI()
    }

    private fun prepareUI() {

        //btnPrev.visibility = if (currentTaskNumber == 0) View.INVISIBLE else View.VISIBLE

        btnPrev.setOnClickListener { loadPrevTask() }
        btnNext.setOnClickListener { loadNextTask() }
    }

    private fun loadNextTask() {
        currentTaskNumber++
        if (currentTaskNumber == maxTasksNumber) {
            currentTaskNumber = 0
        }
        loadTask()

    }

    private fun loadPrevTask() {
        currentTaskNumber--
        if (currentTaskNumber == -1) {
            currentTaskNumber = maxTasksNumber - 1
        }
        loadTask()
    }

    private fun loadTask() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TaskFragment.newInstance(tasks[currentTaskNumber]))
                .commit()
    }

    fun onBottomButtonClicked(v: View) {
        currentTaskNumber = Integer.parseInt((v as Button).text.toString()) - 1
        loadTask()
    }
}
