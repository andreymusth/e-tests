package ru.tzkt.etests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_start.*
import ru.tzkt.etests.startfragments.RulesFragment
import ru.tzkt.etests.startfragments.StartTestsFragment
import ru.tzkt.etests.startfragments.WelcomeFragment

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        pager.adapter = PreviewPagerAdapter(supportFragmentManager)
        tabDots.setupWithViewPager(pager, true)
    }

    private inner class PreviewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            val fragment: Fragment

            when (position) {
                0 -> fragment = WelcomeFragment.newInstance()
                1 -> fragment = RulesFragment.newInstance()
                2 -> fragment = StartTestsFragment.newInstance()
                else -> fragment = WelcomeFragment.newInstance()
            }

            return fragment
        }

        override fun getCount(): Int {
            return 3
        }
    }
}
