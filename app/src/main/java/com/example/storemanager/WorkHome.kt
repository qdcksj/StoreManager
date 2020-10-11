package com.example.storemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.work_home_layout.*

class WorkHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_home_layout)
        supportActionBar?.hide()
        intoStore.setOnClickListener {
            val intent = Intent(this, IntoStore::class.java)
            startActivity(intent)
        }
        managerData.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}