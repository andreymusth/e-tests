package ru.tzkt.etests.startfragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.tzkt.etests.R

/**
 * Created by andrey on 09/05/2018.
 */
class RulesFragment: Fragment() {

    companion object {

        fun newInstance(): Fragment {
            return RulesFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_rules, container, false)
        return v
    }

}