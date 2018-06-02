package ru.tzkt.etests

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_login.*
import ru.tzkt.etests.settings.SettingsActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btnEmailOk.setOnClickListener { saveEmail() }
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

    private fun saveEmail() {

        if (etEmailFirst.text.toString().isNotEmpty()) {

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = prefs.edit()

            editor.putString("defaultEmail", etEmailFirst.text.toString())
            editor.apply()

            finish()
        }


    }
}
