package ru.tzkt.etests

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_test.*
import okhttp3.internal.Util
import ru.tzkt.etests.db.TestsDatabaseAccess
import ru.tzkt.etests.models.Task
import ru.tzkt.etests.settings.SettingsActivity
import ru.tzkt.etests.utils.answers
import ru.tzkt.etests.utils.getCurrentEmail

class TestActivity : AppCompatActivity() {

    private var currentTaskNumber = 0
    private val maxTasksNumber = 10
    private var tasks: ArrayList<Task>? = null
    private var bottomButtons = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        tasks = TestsDatabaseAccess.getInstance(this).getTestsArray()!!

        loadTask()
        prepareUI()
        initBottomButtons()

        if (getCurrentEmail(this).isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


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

            val res = prepareTextToSend()
            val i = Intent(this, SendingResultsActivity::class.java)
            i.putExtra(SendingResultsActivity.RESULT_KEY, res)

            startActivity(i)
        } else {
            loadTask()
        }

    }

    private fun prepareTextToSend(): String {

        var res = "Результат теста: \n\n\n"
        var rigntAnswers = 0
        for ((index, task) in tasks!!.withIndex()) {

            val answerVariant = if (answers.contains(index)) answers[index] else 1

//            try {
//                getLetteredAnswerVariant(task.rightAnswerNum)
//                task.variants[task.rightAnswerNum - 1]
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }

            res += "Вопрос ${index + 1} \n" +
                    "Текст вопроса: \n" +
                    "${task.taskText} \n" +
                    "Ваш вариант ответа: ${getLetteredAnswerVariant(answerVariant!!)} ${task.variants[answerVariant - 1]}\n" +
                    "Правильный вариант ответа: ${getLetteredAnswerVariant(task.rightAnswerNum)} ${task.variants[task.rightAnswerNum - 1]} \n \n \n"

            if (answerVariant == task.rightAnswerNum) {
                rigntAnswers++
            }

        }

        res += "Правильных ответов $rigntAnswers/10 \n\n\n"

        var mark = ""

        if (rigntAnswers in (0..4)) {
            mark = "Оценка за тест: 2"
        }

        if (rigntAnswers in (5..6)) {
            mark = "Оценка за тест: 3"
        }

        if (rigntAnswers in (7..8)) {
            mark = "Оценка за тест: 4"
        }

        if (rigntAnswers in (9..10)) {
            mark = "Оценка за тест: 5"
        }

        res += mark

        return res
    }

    private fun getLetteredAnswerVariant(variant: Int): String {

        when (variant) {
            1 -> return "a)"
            2 -> return "b)"
            3 -> return "c)"
            4 -> return "d)"
            else -> return "a)"
        }
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
                .replace(R.id.fragmentContainer, TaskFragment.newInstance(tasks!![currentTaskNumber], currentTaskNumber))
                .commit()
    }

    fun onBottomButtonClicked(v: View) {
        currentTaskNumber = Integer.parseInt((v as Button).text.toString()) - 1
        loadTask()
    }

    private fun setButtonTextColors() {

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
