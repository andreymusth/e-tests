package ru.tzkt.etests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import ru.tzkt.etests.db.TestsDatabaseAccess

class MainActivity : AppCompatActivity() {

    private var currentTaskNumber = 0
    private val maxTasksNumber = 10
    private var tasks: ArrayList<Task>? = null
    private var bottomButtons = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tasks = TestsDatabaseAccess.getInstance(this).getTestsArray()!!

        loadTask()
        prepareUI()
        initBottomButtons()
    }

    private fun initBottomButtons() {

        bottomButtons.add(btn1)
        bottomButtons.add(btn2)
        bottomButtons.add(btn3)
        bottomButtons.add(btn4)
        bottomButtons.add(btn5)
        bottomButtons.add(btn6)
        bottomButtons.add(btn7)
        bottomButtons.add(btn8)
        bottomButtons.add(btn9)
        bottomButtons.add(btn10)

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
        setButtonTextColors()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TaskFragment.newInstance(tasks!![currentTaskNumber]))
                .commit()
    }

    fun onBottomButtonClicked(v: View) {
        currentTaskNumber = Integer.parseInt((v as Button).text.toString()) - 1
        loadTask()
    }

    fun setButtonTextColors() {

        val colorBlue = ContextCompat.getColor(this, R.color.colorBlue)
        val colorWhite = ContextCompat.getColor(this, android.R.color.white)

        for ((ind, button) in bottomButtons.withIndex()) {

            if (ind == currentTaskNumber) {
                button.setTextColor(colorBlue)
            } else {
                button.setTextColor(colorWhite)
            }
        }
    }
}
