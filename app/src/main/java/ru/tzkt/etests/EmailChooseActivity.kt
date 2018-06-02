package ru.tzkt.etests

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_email_choose.*
import kotlinx.android.synthetic.main.email_view_holder.view.*
import ru.tzkt.etests.db.EmailsDBHelper
import ru.tzkt.etests.utils.getCurrentEmail

class EmailChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_choose)

        recyclerView.adapter = EmailsAdapter(loadEmails())
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnEmailOk.setOnClickListener {
            if (etNewEmail.text.toString().isEmpty()) {
                Toast.makeText(this, "Заполните email или выберите существующий", Toast.LENGTH_LONG).show()
            } else {

                val cv = ContentValues()
                cv.put(EmailsDBHelper.EmailsContract.EMAIL, etNewEmail.text.toString())
                contentResolver.insert(Uri.parse(EmailsDBHelper.EmailsContract.CONTENT_URI), cv)


                setOkAndFinish(etNewEmail.text.toString())
            }
        }
    }

    private fun loadEmails(): ArrayList<String> {

        val emails = ArrayList<String>()
        emails.add(getCurrentEmail(this))

        val cursor = contentResolver.query(Uri.parse(EmailsDBHelper.EmailsContract.CONTENT_URI),
                null,
                null,
                null,
                null)

        while (cursor.moveToNext()) {

            val email = cursor.getString(cursor.getColumnIndex(EmailsDBHelper.EmailsContract.EMAIL))
            emails.add(email)
        }

        cursor.close()

        return emails
    }

    private fun setOkAndFinish(email: String) {

        val i = Intent()
        i.putExtra(SendingResultsActivity.SELECTED_EMAIL, email)
        setResult(Activity.RESULT_OK, i)
        finish()

    }

    inner class EmailsAdapter(private val emails: ArrayList<String>) : RecyclerView.Adapter<EmailViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {

            val vh = EmailViewHolder(LayoutInflater.from(this@EmailChooseActivity).inflate(R.layout.email_view_holder, parent, false))
            return vh
        }

        override fun getItemCount(): Int {
            return emails.size
        }

        override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
            holder.tvAnimalType.setOnClickListener {
                if (it is TextView) {
                    setOkAndFinish(it.text.toString())
                }
            }
            holder.tvAnimalType.text = emails[position]
        }

    }


    inner class EmailViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val tvAnimalType = v.tvEmailVH
    }
}