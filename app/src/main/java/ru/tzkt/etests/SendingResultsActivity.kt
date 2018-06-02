package ru.tzkt.etests

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sending_results.*
import retrofit2.adapter.rxjava2.Result
import ru.tzkt.etests.models.ResultMessage
import ru.tzkt.etests.network.ApiRepositoryProvider
import ru.tzkt.etests.settings.SettingsActivity
import ru.tzkt.etests.utils.getCurrentEmail

class SendingResultsActivity : AppCompatActivity() {


    companion object {
        const val RESULT_KEY = "RESULT_KEY"
        const val EMAIL_CHOOSE_CODE = 1209
        const val SELECTED_EMAIL = "SELECTED_EMAIL"
    }

    private var res: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sending_results)

        if (intent.hasExtra(RESULT_KEY)) {
            res = intent.getStringExtra(RESULT_KEY)
        }

        tvEmail.text = getCurrentEmail(this)

        btnSendResults.setOnClickListener {

            val msg = ResultMessage("Результаты тестов", "sisQa", tvEmail.text.toString(), res!!)
            ApiRepositoryProvider.provideApiRepository(this)
                    .sendTestResults(msg)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { handleRes(it) },
                            {
                                run {
                                    Toast.makeText(this, "При отправке результатов произошли ошибки", Toast.LENGTH_SHORT).show()
                                    it.printStackTrace()
                                }
                            }
                    )
        }

        btnChooseAnotherEmail.setOnClickListener {
            startActivityForResult(Intent(this, EmailChooseActivity::class.java), EMAIL_CHOOSE_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == EMAIL_CHOOSE_CODE && resultCode == Activity.RESULT_OK) {
            val email = data?.getStringExtra(SELECTED_EMAIL) ?: ""
            tvEmail.text = email
        }

    }

    private fun handleRes(res: Result<Void>) {
        if (res.response()?.code() == 200) {
            Toast.makeText(this, "Результаты успешно отправлены", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "При отправке результатов произошли ошибки", Toast.LENGTH_SHORT).show()
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
}
