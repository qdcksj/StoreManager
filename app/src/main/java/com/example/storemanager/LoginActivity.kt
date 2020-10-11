package com.example.storemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login_layout.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        supportActionBar?.hide()

        loginButton.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passWordEdit.text.toString()
            if (account == "qdcksj" && password == "ckbz87426619"){
                val intent = Intent(this, ManagerHome::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show()
            }
        }
    }
}