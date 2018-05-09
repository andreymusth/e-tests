package ru.tzkt.etests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_task.*
import ru.tzkt.etests.models.Task
import ru.tzkt.etests.utils.answers

/**
 */
class TaskFragment : Fragment() {

    private var task: Task? = null
    private var taskNumber: Int = -1


    companion object {

        const val TASK_KEY = "TASK_KEY"
        const val TASK_NUMBER = "TASK_NUMBER"

        fun newInstance(task: Task, taskNumber: Int): Fragment {

            val args = Bundle()
            args.putSerializable(TASK_KEY, task)
            args.putInt(TASK_NUMBER, taskNumber)
            val fragment = TaskFragment()
            fragment.allowEnterTransitionOverlap = false
            fragment.allowReturnTransitionOverlap = false


            val slideTop = Slide(Gravity.TOP)
            slideTop.duration = 150

            val slideBottom = Slide(Gravity.BOTTOM)
            slideBottom.duration = 150

            fragment.exitTransition = slideTop
            fragment.enterTransition = slideBottom
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments != null) {
            task = arguments!!.getSerializable(TASK_KEY) as Task
            taskNumber = arguments!!.getInt(TASK_NUMBER)
        }

        prepareUI()
        setListeners()
    }

    private fun prepareUI() {

        if (answers.contains(taskNumber)) {
            val ans = answers[taskNumber]

            when (ans) {
                1 -> var1.isChecked = true
                2 -> var2.isChecked = true
                3 -> var3.isChecked = true
                4 -> var4.isChecked = true
            }
        } else {
            var1.isChecked = true
            saveResult(var1)
        }

        tvTask.text = task!!.taskText

        var1.text = task!!.variants[0]
        var2.text = task!!.variants[1]
        var3.text = task!!.variants[2]

        if (task!!.variants.size == 4) {
            var4.text = task!!.variants[3]
        } else {
            var4.visibility = View.INVISIBLE
        }
    }

    private fun setListeners() {
        var1.setOnClickListener { saveResult(it) }
        var2.setOnClickListener { saveResult(it) }
        var3.setOnClickListener { saveResult(it) }
        var4.setOnClickListener { saveResult(it) }
    }

    private fun saveResult(v: View) {

        when (v.id) {
            R.id.var1 -> answers[taskNumber] = 1
            R.id.var2 -> answers[taskNumber] = 2
            R.id.var3 -> answers[taskNumber] = 3
            R.id.var4 -> answers[taskNumber] = 4
        }

    }
}
