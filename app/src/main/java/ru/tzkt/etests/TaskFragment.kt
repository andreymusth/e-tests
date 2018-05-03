package ru.tzkt.etests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_task.*

/**
 * Created by andrey on 01/05/2018.
 */
class TaskFragment : Fragment() {

    var task: Task? = null

    companion object {

        const val TASK_KEY = "TASK_KEY"

        fun newInstance(task: Task): Fragment {

            val args = Bundle()
            args.putSerializable(TASK_KEY, task)
            val fragment = TaskFragment()
            fragment.allowEnterTransitionOverlap = false
            fragment.allowReturnTransitionOverlap = false
            fragment.exitTransition = Slide(Gravity.TOP)
            fragment.enterTransition = Slide(Gravity.BOTTOM)
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
        }

        prepareUI()
    }

    private fun prepareUI() {

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
}