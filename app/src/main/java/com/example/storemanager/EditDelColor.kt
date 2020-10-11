package com.example.storemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_del_color_layout.*
import kotlinx.android.synthetic.main.edit_del_color_layout.listView
import kotlinx.android.synthetic.main.list_color_layout.*

class EditDelColor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_del_color_layout)
        supportActionBar?.hide()

        val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)
        val db = dbHelper.writableDatabase
        val cursor = db.query("ProduColor",null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            val colorList:ArrayList<String> = ArrayList()
            val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colorList)
            listView.adapter = myAdapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val color = colorList[position]
                delColorBtn.setOnClickListener {
                    db.delete("ProduColor", "produColor == ?", arrayOf(color))
                    colorList.remove(color)
                    //myAdapter.notifyDataSetChanged()
                    myAdapter.notifyDataSetChanged()
                }
            }

            do {
                val colorName = cursor.getString(cursor.getColumnIndex("produColor"))
                colorList.add(colorName)
            }while (cursor.moveToNext())
        }
        cursor.close()
    }
}