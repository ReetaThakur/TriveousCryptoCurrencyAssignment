package com.reeta.triveouscryptocurrencyassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.reeta.triveouscryptocurrencyassignment.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

/*
This activity use for Sign in purpose by firebase
 */
class SignInActivity : AppCompatActivity() {

    lateinit var edtEmail: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.title = "SignIn"
        edtEmail = findViewById(R.id.signinemail)
        edtPassword = findViewById(R.id.singinpassword)
        mAuth = FirebaseAuth.getInstance()

        btnSignIn.setOnClickListener {
            singinProgressBar.visibility = View.VISIBLE
            var email = edtEmail.editableText.toString()
            var password = edtPassword.editableText.toString()
            if (email.isEmpty() || password.isEmpty()) {
                singinProgressBar.visibility = View.INVISIBLE
                Toast.makeText(
                    this@SignInActivity, "Please Enter valid email and password", Toast.LENGTH_LONG
                ).show()
            } else {
                mAuth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@SignInActivity,
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                singinProgressBar.visibility = View.INVISIBLE
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)

                            } else {
                                singinProgressBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this,
                                    "Invalid Email and password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        var user: FirebaseUser? =mAuth?.currentUser
        if (user!=null){
            startActivity(Intent(this,MainActivity::class.java))
            Toast.makeText(this,"User already Login",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this,"First Login",Toast.LENGTH_LONG).show()
        }
    }
}
