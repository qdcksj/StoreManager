package com.example.storemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_manager_home.*

class ManagerHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_home)
        supportActionBar?.hide()

        val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase

            Toast.makeText(this, "已操作完成！", Toast.LENGTH_SHORT).show()
        }
    }
}