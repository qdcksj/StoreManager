package com.example.storemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_color_layout.*

class ListColor : AppCompatActivity() {

//显示已编辑的颜色名称
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_color_layout)
        supportActionBar?.hide()
        val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)

        checkButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("ProduColor", null, null, null, null, null, null)

            if (cursor.moveToFirst()){
                var colorData: ArrayList<String> = ArrayList()
                val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorData)
                listView.adapter = myAdapter   //列表显示
                do {
                    val colorName =  cursor.getString(cursor.getColumnIndex("produColor"))
                    colorData.add(colorName)
                    Log.d("ListColor", "颜色是：$colorName。\n")
                }while (cursor.moveToNext())
            }

            cursor.close()

        }

    }

}