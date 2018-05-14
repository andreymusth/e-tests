package ru.tzkt.etests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
            val msg = ResultMessage("Тепло", "sisQa", "andreyrabo@mail.ru", res!!)
            ApiRepositoryProvider.provideApiRepository(this)
                    .sendTestResults(msg)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {handleRes(it)},
                            {it.printStackTrace()}
                    )
        }
    }

    private fun handleRes(res: Result<Void>) {
        val g = 0
    }
}
