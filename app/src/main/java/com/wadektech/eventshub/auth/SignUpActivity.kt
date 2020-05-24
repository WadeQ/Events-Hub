package com.wadektech.eventshub.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.firestore.FirebaseFirestore
import com.mukesh.OtpView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.auth.SignUpActivity
import com.wadektech.eventshub.models.User
import com.wadektech.eventshub.ui.MainActivity
import com.wadektech.eventshub.utils.toast
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class SignUpActivity : AppCompatActivity() {
    private lateinit var verificationcodeet: OtpView
    private lateinit var input_phonenumber: EditText
    private lateinit var _passwordText: EditText
    private lateinit var _nameText: EditText
    private lateinit var _emailText: EditText
    private lateinit var _signupButton: Button
    private lateinit var backbtn: ImageView
    private lateinit var emailst: String
    var passst: String? = null
    var namest: String? = null
    var phonenumber: String? = null
    var phonenumbertxtll: TextView? = null
    var titletxt: TextView? = null
    var desctxt: TextView? = null
    var loginBack: TextView? = null
    var userinfo_tab: LinearLayout? = null
    var _loginLink: LinearLayout? = null
    var namell: LinearLayout? = null
    var emailll: LinearLayout? = null
    var passwordll: LinearLayout? = null
    var phonenumberll: LinearLayout? = null
    var step = 1
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var codeSent: String? = null
    private lateinit var  verification_tab : LinearLayout

    //name strings
    var lastName = ""
    var firstName = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        // Initialize Firebase Auth

        _passwordText = findViewById(R.id.input_password)
        _nameText = findViewById(R.id.input_name)
        _emailText = findViewById(R.id.input_email)
        _signupButton = findViewById(R.id.btn_signup)
        input_phonenumber = findViewById(R.id.input_phonenumber)
        val _loginLink : LinearLayout = findViewById(R.id.link_login)
        val namell = findViewById<LinearLayout>(R.id.namell)
        val emailll : LinearLayout = findViewById(R.id.emailll)
        val passwordll : LinearLayout = findViewById(R.id.passwordll)
        val phonenumberll : LinearLayout = findViewById(R.id.phonenumberll)
        val phonenumbertxtll : TextView = findViewById(R.id.phonenumbertextll)
        val titletxt : TextView = findViewById(R.id.titletxt)
        val desctxt : TextView = findViewById(R.id.desctxt)
        userinfo_tab = findViewById(R.id.userinfo_tab)
        val verification_tab : LinearLayout= findViewById(R.id.verification_tab)
        verification_tab.visibility = View.GONE
        verificationcodeet = findViewById(R.id.verificationcodeet)
        val btn = findViewById<Button>(R.id.verificationcodebtn)
        backbtn = findViewById(R.id.backbtn)
        hideAll()
        namell.visibility = View.VISIBLE

        backbtn.setOnClickListener { finish() }
        btn.setOnClickListener { verifyCode() }
        _signupButton.setOnClickListener(View.OnClickListener {
            if (step == 1) {
                namest = _nameText.text.toString()
                if (namest!!.isEmpty() || haveLastName(namest!!)) {
                    if (namest!!.isEmpty()) {
                        //ToastKt.toast(getApplicationContext(),"Please write your name!");
                    } else if (haveLastName(namest!!)) {
                        //ToastKt.toast(getApplicationContext(),"Please add full name");
                    }
                } else {
                    namell.visibility = View.GONE
                    emailll.visibility = View.VISIBLE
                    titletxt.text = "Choose email"
                    desctxt.text = "enter your email address below."
                    step++
                }
            } else if (step == 2) {
                emailst = _emailText.text.toString()
                if (emailst.isEmpty()) {
                    //ToastKt.toast(getApplicationContext(),"Please write your email!");
                } else {
                    emailll.visibility = View.GONE
                    passwordll.visibility = View.VISIBLE
                    titletxt.text = "Create Password"
                    desctxt.text = "Your password must have at least one symbol & 4 or more characters."
                    step++
                }
            } else if (step == 3) {
                passst = _passwordText.text.toString()
                if (passst!!.isEmpty()) {
                    toast("Password cannot be blank!")
                } else {
                    passwordll.visibility = View.GONE
                    phonenumberll.visibility = View.VISIBLE
                    phonenumbertxtll.visibility = View.VISIBLE
                    titletxt.text = "Let's Get Started"
                    desctxt.text = "Enter your mobile number to enable 2-step verification."
                    step++
                }
            } else if (step == 4) {
                phonenumber = input_phonenumber.text.toString()
                if (phonenumber!!.isEmpty()) {
                    toast("Phone number cannot be blank!")
                } else {
                    phonenumberll.visibility = View.GONE
                    phonenumbertxtll.visibility = View.GONE
                    titletxt.text = "Verification"
                    desctxt.text = "We sent you a code to verify your phone number."
                    signUp()
                    step++
                }
            }
        })
        _loginLink.setOnClickListener(View.OnClickListener { // Finish the registration screen and return to the Login activity
            finish()
        })
        mAuth.useAppLanguage()
    }

    private fun hideAll() {
        //removing all the edit text will be opened one by one
        namell!!.visibility = View.GONE
        emailll!!.visibility = View.GONE
        passwordll!!.visibility = View.GONE
        phonenumberll!!.visibility = View.GONE
        phonenumbertxtll!!.visibility = View.GONE
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                mCallbacks) // OnVerificationStateChangedCallbacks
    }

    private var mCallbacks: OnVerificationStateChangedCallbacks = object : OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            //getting the code sent by sms
            val smsCode = phoneAuthCredential.smsCode

            //sometimes the code is not detected immediately
            //in that case, the code will be null
            //so user has to manually enter the code
            if (smsCode != null) {
                verificationcodeet.setText(smsCode)
                //verifying the code
                verifyCode()
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Timber.d("onVerificationFailed: reason %s", e.message)
            //ToastKt.toast(getApplicationContext(), "SMS Code verification failed");
        }

        override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
            super.onCodeSent(s, forceResendingToken)
            codeSent = s
        }
    }

    private fun verifyCode() {
        val code = Objects.requireNonNull(verificationcodeet!!.text).toString()
        val progressDialog = ProgressDialog(this@SignUpActivity)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()
        if (code.isEmpty()) {
            //ToastKt.toast(getApplicationContext(), "Code Required");
            progressDialog.dismiss()
        } else {
            try {
                progressDialog.dismiss()
                val credential = PhoneAuthProvider.getCredential(codeSent!!, code)
                signInWithPhoneAuthCredential(credential)
            } catch (e: Exception) {
                val toast = Toast.makeText(this, "Verification Code is wrong$e", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d("signInWithCredential:success")
                        val user = Objects.requireNonNull(task.result)?.user
                        updateUI(user)
                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        Timber.tag(TAG).w(task.exception, "signInWithCredential:failure")
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            // as we already have the user id you can add this code to signup the user with email because we can reset password with email
            mAuth.createUserWithEmailAndPassword(emailst, passst!!)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("createUserWithEmail:success")
                            val user = mAuth.currentUser
                            updateUI(user)
                            var userId: String? = null
                            if (user != null) {
                                userId = user.uid
                            }
                            assert(user != null)
                            println(user!!.displayName)
                            println(user.email)
                            println(user.phoneNumber)
                            val userProfile = User(user.displayName!!, user.phoneNumber!!, user.phoneNumber!!)
                            FirebaseFirestore
                                    .getInstance()
                                    .collection("Users")
                                    .document(userId!!)
                                    .set(userProfile)
                        } else {
                            // If sign in fails, display a message to the user.
                            Timber.tag(TAG).w(task.exception, "createUserWithEmail:failure")
                            //ToastKt.toast(getApplicationContext(), "Authentication failed.");
                            updateUI(null)
                        }
                    }
        } else {
            onSignUpFailed()
        }
    }

    fun signUp() {
        Timber.d("signUp")
        if (!validate()) {
            onSignUpFailed()
            return
        } else {
            userinfo_tab!!.visibility = View.GONE
            verification_tab!!.visibility = View.VISIBLE
            startPhoneNumberVerification("+254" + input_phonenumber.text.toString())
        }
        _signupButton!!.isEnabled = false
        val progressDialog = ProgressDialog(this@SignUpActivity)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Sending Verification Code...")
        progressDialog.show()
        val name = _nameText.text.toString()

        // TODO: Implement your own signup logic here.
        Handler().postDelayed(
                { // On complete call either onSignupSuccess or onSignupFailed
                    // depending on success
                    // onSignupFailed();
                    progressDialog.dismiss()
                }, 3000)
    }

    fun onSignUpSuccess() {
        _signupButton!!.isEnabled = true
        setResult(Activity.RESULT_OK, null)
        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onSignUpFailed() {
        //ToastKt.toast(getApplicationContext(),"Login failed");
        _signupButton!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true
        val name = _nameText!!.text.toString()
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()
        if (name.isEmpty() || name.length < 3) {
            _nameText!!.error = "at least 3 characters"
            valid = false
        } else {
            _nameText!!.error = null
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText!!.error = "enter a valid email address"
            _emailText!!.isFocusable = true
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

    private fun haveLastName(UserFullName: String): Boolean {
        Timber.d("i am here and have last name function")
        return try {
            val firstSpace = UserFullName.indexOf(" ") // detect the first space character
            firstName = UserFullName.substring(0, firstSpace) // get everything upto the first space character
            lastName = UserFullName.substring(firstSpace).trim { it <= ' ' } // get everything after the first space, trimming the spaces off
            Timber.d("i am here i got first name and last name and firstName is %s", firstName)
            false
        } catch (e: Exception) {
            Timber.d("i am here i did not got first name and last name")
            true
        }
    }

    companion object {
        private const val TAG = "SignUpActivity"
    }
}