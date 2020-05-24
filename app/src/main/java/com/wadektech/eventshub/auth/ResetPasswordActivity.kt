package com.wadektech.eventshub.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.wadektech.eventshub.R
import com.wadektech.eventshub.utils.toast

class ResetPasswordActivity : AppCompatActivity() {
    private var inputEmail: EditText? = null
    private var btnReset: Button? = null
    private var btnBack: TextView? = null
    private var auth: FirebaseAuth? = FirebaseAuth.getInstance()
    private var progressBar: ProgressBar? = null
    private var backbtn: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        val inputEmail : EditText = findViewById(R.id.email)
        val btnReset : Button= findViewById(R.id.btn_reset_password)
        val btnBack : TextView = findViewById(R.id.link_login)
        val progressBar : ProgressBar= findViewById(R.id.progressBar)
        val backbtn : ImageView= findViewById(R.id.backbtn)
        backbtn.setOnClickListener { finish() }
        btnBack.setOnClickListener { finish() }
        btnReset.setOnClickListener(View.OnClickListener {

            val email = inputEmail.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                toast("Enter your registered email id")
                return@OnClickListener
            }
            progressBar.visibility = View.VISIBLE
            auth?.sendPasswordResetEmail(email)
                    ?.addOnCompleteListener { task: Task<Void?> ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ResetPasswordActivity, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@ResetPasswordActivity, "Failed to send reset email!", Toast.LENGTH_SHORT).show()
                        }
                        progressBar.visibility = View.GONE
                    }
        })
    }
}