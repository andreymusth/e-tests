package ru.tzkt.etests.startfragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_start_tests.*
import ru.tzkt.etests.R
import ru.tzkt.etests.TestActivity

/**
 * Created by andrey on 09/05/2018.
 */
class StartTestsFragment: Fragment() {

    companion object {

        fun newInstance(): Fragment {
            return StartTestsFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_start_tests, container, false)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartTests.setOnClickListener {
            startActivity(Intent(activity!!, TestActivity::class.java))
        }
    }

}