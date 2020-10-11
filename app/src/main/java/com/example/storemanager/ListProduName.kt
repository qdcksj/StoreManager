package com.example.storemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_color_layout.checkButton
import kotlinx.android.synthetic.main.list_color_layout.listView
import kotlinx.android.synthetic.main.list_produ_name_lyout.*
import org.jetbrains.anko.selector

class ListProduName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_produ_name_lyout)
        supportActionBar?.hide()
        val dbHelper = ProduDatabaseHelper(this, "ProduStore.db", 1)

        val lineArray2 = listOf("注塑", "吹塑", "挤出", "其他")
        lineSpiner2.text = lineArray2[0]
        lineSpiner2.setOnClickListener {
            selector("工序名称", lineArray2) { i ->
                lineSpiner2.text = lineArray2[i]
            }
        }
        checkButton.setOnClickListener {
            val db = dbHelper.writableDatabase

            if (lineSpiner2.text == "注塑") {
                val cursor = db.query("ZhusuProduName", null, null, null, null, null, null)
                if (cursor.moveToFirst()) {
                    var nameData: ArrayList<String> = ArrayList()
                    val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameData)
                    listView.adapter = myAdapter  //列表显示

                    listView.setOnItemClickListener { _, _, position, _ ->
                        val name = nameData[position]
                        delProduNameBtn.setOnClickListener {
                            db.delete("ZhusuProduName", "produName == ?", arrayOf(name))
                            nameData.remove(name)
                            myAdapter.notifyDataSetChanged()
                        }
                    }
                    do {
                        val produName = cursor.getString(cursor.getColumnIndex("produName"))
                        nameData.add(produName)
                    } while (cursor.moveToNext())
                }
                cursor.close()
            } else if (lineSpiner2.text == "吹塑") {
                val cursor = db.query("ChuisuProduName", null, null, null, null, null, null)
                if (cursor.moveToFirst()) {
                    var nameData: ArrayList<String> = ArrayList()
                    val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameData)
                    listView.adapter = myAdapter  //列表显示

                    listView.setOnItemClickListener { _, _, position, _ ->
                        val name =nameData[position]
                        delProduNameBtn.setOnClickListener {
                            db.delete("ChuisuProduName", "produName == ?", arrayOf(name))
                            nameData.remove(name)
                            myAdapter.notifyDataSetChanged()
                        }
                    }
                    do {
                        val produName = cursor.getString(cursor.getColumnIndex("produName"))
                        nameData.add(produName)
                    } while (cursor.moveToNext())
                }
                cursor.close()
            } else if (lineSpiner2.text == "挤出") {
                val cursor = db.query("JichuProduName", null, null, null, null, null, null)
                if (cursor.moveToFirst()) {
                    var nameData: ArrayList<String> = ArrayList()
                    val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameData)
                    listView.adapter = myAdapter  //列表显示
                    listView.setOnItemClickListener { _, _, position, _ ->
                        val name = nameData[position]
                        delProduNameBtn.setOnClickListener {
                            db.delete("JichuProduName", "produName == ?", arrayOf(name))
                            nameData.remove(name)
                            myAdapter.notifyDataSetChanged()
                        }
                    }
                        do {
                            val produName = cursor.getString(cursor.getColumnIndex("produName"))
                            nameData.add(produName)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
            } else if (lineSpiner2.text == "其他"){
                    val cursor = db.query("OtherProduName", null, null, null, null, null, null)
                    if (cursor.moveToFirst()) {
                        var nameData: ArrayList<String> = ArrayList()
                        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameData)
                        listView.adapter = myAdapter  //列表显示
                        listView.setOnItemClickListener { _, _, position, _ ->
                            val name = nameData[position]
                            delProduNameBtn.setOnClickListener {
                                db.delete("OtherProduName", "produName == ?", arrayOf(name))
                                nameData.remove(name)
                                myAdapter.notifyDataSetChanged()
                            }
                        }

                        do {
                           val produName = cursor.getString(cursor.getColumnIndex("produName"))
                           nameData.add(produName)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
            }

        }
    }
}

