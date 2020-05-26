package com.wadektech.eventshub.auth

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wadektech.eventshub.R
import com.wadektech.eventshub.auth.SignUpActivity
import com.wadektech.eventshub.ui.MainActivity
import com.wadektech.eventshub.utils.toast
import timber.log.Timber
import java.util.*

class LoginActivity : AppCompatActivity() {
    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _loginButton: Button? = null
    var link_forgotpassword: TextView? = null
    var _signupLink: LinearLayout? = null
    var backbtn: ImageView? = null
    var user: HashMap<String, String>? = null
    var categoriest: String? = null
    private var mAuth: FirebaseAuth? = null
    //name strings
    var lastName = ""
    var firstName = ""

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_login)
        /*black icons on top bar like battery etc*/window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        categoriest = intent.getStringExtra("categories")

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        _emailText = findViewById(R.id.input_email)
        _passwordText = findViewById(R.id.input_password)
        val _loginButton : Button= findViewById(R.id.btn_login)
        val _signupLink :LinearLayout= findViewById(R.id.link_signup)
        val link_forgotpassword : TextView = findViewById(R.id.link_forgotpassword)
        val backbtn : ImageView = findViewById(R.id.backbtn)

        backbtn.setOnClickListener(View.OnClickListener { finish() })
        link_forgotpassword.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, ResetPasswordActivity::class.java)
            startActivity(intent)
        })
        _loginButton.setOnClickListener(View.OnClickListener { login() })
        _signupLink.setOnClickListener(View.OnClickListener {
            // Start the Signup activity
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            intent.putExtra("categories", categoriest)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
        })
    }

    private fun login() {
        /* Timber.d("login() :Login method called");
        if (!validate()) {
            onLoginFailed();
            return;
        }
            Timber.d("we are in update UI");
        */
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //_loginButton!!.isEnabled = false
        /*
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d("signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        progressDialog.dismiss();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.tag(TAG).w(task.getException(), "signInWithEmail:failure");
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        updateUI(null);
                    }

                    // ...
                });

        // On complete call either onLoginSuccess or onLoginFailed
        // onLoginSuccess();
        // onLoginFailed();
        new android.os.Handler().postDelayed(
                progressDialog::dismiss, 3000);
 */
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            /*so we have successfully logged in the user */
            Timber.e("we are updating UI")
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            toast("User login failed, please try again!")
        }
    }

    private fun getFirstName(name: String): String {
        return if (name.split("\\w+").toTypedArray().size > 1) {
            lastName = name.substring(name.lastIndexOf(" ") + 1)
            firstName = name.substring(0, name.lastIndexOf(' '))
            firstName
        } else {
            firstName = name
            firstName
        }
    }

    private fun haveLastName(name: String): Boolean {
        return if (name.split("\\w+").toTypedArray().size > 1) {
            lastName = name.substring(name.lastIndexOf(" ") + 1)
            firstName = name.substring(0, name.lastIndexOf(' '))
            true
        } else {
            firstName = name
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == Activity.RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                finish()
            }
        }
    }

    override fun onBackPressed() {
        // disable going back to the MainActivityStudent
        moveTaskToBack(true)
    }

    fun onLoginSuccess() {
        _loginButton!!.isEnabled = true
        finish()
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
        _loginButton!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText!!.error = "enter a valid email address"
            valid = false
        } else {
            _emailText!!.error = null
        }
        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            _passwordText!!.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            _passwordText!!.error = null
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        if (mAuth!!.currentUser != null) {
            Timber.e("onStart: user exists")
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
        private const val REQUEST_SIGNUP = 0
    }
}