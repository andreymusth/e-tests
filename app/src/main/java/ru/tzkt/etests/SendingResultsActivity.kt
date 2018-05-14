package ru.tzkt.etests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sending_results.*
import retrofit2.adapter.rxjava2.Result
import ru.tzkt.etests.models.ResultMessage
import ru.tzkt.etests.network.ApiRepositoryProvider

class SendingResultsActivity : AppCompatActivity() {


    companion object {
        const val RESULT_KEY = "RESULT_KEY"
    }

    private var res: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sending_results)

        if (intent.hasExtra(RESULT_KEY)) {
            res = intent.getStringExtra(RESULT_KEY)
        }


        btnSendResults.setOnClickListener {
            val msg = ResultMessage("Результаты тестов", "sisQa", etEmail.text.toString(), res!!)
            ApiRepositoryProvider.provideApiRepository(this)
                    .sendTestResults(msg)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {handleRes(it)},
                            {run {
                                Toast.makeText(this, "При отправке результатов произошли ошибки", Toast.LENGTH_SHORT).show()
                                it.printStackTrace()}}
                    )
        }
    }

    private fun handleRes(res: Result<Void>) {
        if (res.response()?.code() == 200) {
            Toast.makeText(this, "Результаты успешно отправлены", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "При отправке результатов произошли ошибки", Toast.LENGTH_SHORT).show()
        }
    }
}
