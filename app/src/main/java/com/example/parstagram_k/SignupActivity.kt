package com.example.parstagram_k

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser
import com.parse.SignUpCallback

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var signupButton = findViewById<Button>(R.id.signup_button)
        signupButton.setOnClickListener {
            var username = findViewById<EditText>(R.id.username_et).text.toString()
            var email = findViewById<EditText>(R.id.email_et).text.toString()
            var password = findViewById<EditText>(R.id.password_et).text.toString()

            signupUser(username,email,password)
        }



    }

    private fun signupUser(username: String, email: String, password: String) {
        val user = ParseUser();
        // Set the user's username and password, which can be obtained by a forms
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(SignUpCallback() {
            if (it == null) {
                Log.i(LoginActivity.TAG, "Successfully logged in user")
                showAlert("Welcome to Parstagram!", "Thank you for selling your soul!!")
                goToMainActivity()
            } else {
                ParseUser.logOut();
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show();
            }
        });
    }
    private fun goToMainActivity() {
        val intent = Intent(this@SignupActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
                // don't forget to change the line below with the names of your Activities
//                val intent = Intent(this, LogoutActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
            }
        val ok = builder.create()
        ok.show()
    }
}