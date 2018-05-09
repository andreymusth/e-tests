package ru.tzkt.etests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sending_results.*

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
            val g = 0
        }
    }
}
