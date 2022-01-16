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
import com.reeta.triveouscryptocurrencyassignment.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var edtEmail: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.title="SignUp"
        edtEmail=findViewById(R.id.email)
        edtPassword=findViewById(R.id.password)

        btnSignUp.setOnClickListener {
            profileProgressBar.visibility= View.VISIBLE
            var email=edtEmail.editableText.toString()
            var password=edtPassword.editableText.toString()
            mAuth=FirebaseAuth.getInstance()

            if (email.isEmpty() || password.isEmpty()){
                profileProgressBar.visibility= View.INVISIBLE
                Toast.makeText(this@SignUpActivity, "Please Enter valid email and password", Toast.LENGTH_LONG
                ).show()
            }else {
                mAuth!!.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@SignUpActivity,
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {

                                profileProgressBar.visibility = View.INVISIBLE

                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Authentication SuccessFul",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)

                            } else {

                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Authentication Failed",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        })
            }
        }

        tvAlreadyUser.setOnClickListener {
            val intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
    }
}