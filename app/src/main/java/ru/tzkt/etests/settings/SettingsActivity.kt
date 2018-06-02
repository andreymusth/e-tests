package ru.tzkt.etests.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ru.tzkt.etests.R

/**
 * Created by andrey on 19/04/2018.
 */
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
                .add(R.id.settingsRoot, SettingsFragment())
                .commit()

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}