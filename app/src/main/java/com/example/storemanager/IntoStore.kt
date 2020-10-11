package com.example.storemanager

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.into_store_layout.*
import org.jetbrains.anko.selector

class IntoStore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.into_store_layout)
        supportActionBar?.hide()

            //启动添加产品颜色
         addProduColor.setOnClickListener {
            val intent = Intent(this, AddColor::class.java)
            startActivity(intent)
            }

   //启动添加产品名称
       addNewProdu.setOnClickListener {
            val intent = Intent(this, AddProduName::class.java)
                startActivity(intent)
       }

        //年度选择
        var yearSpinnerData = listOf("0000","2020","2021","2022","2023","2024")
        var monthSpinnerData = listOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
        var daySpinnerData = listOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",  "25", "26", "27", "28", "29", "30", "31")

        yearSpiner1.text = yearSpinnerData[0]
        yearSpiner1.setOnClickListener {
            selector("选择",yearSpinnerData){i ->
                yearSpiner1.text = yearSpinnerData[i]
            }
        }

        //月度选择
        monthSpiner1.text = monthSpinnerData[0]
        monthSpiner1.setOnClickListener {
            selector("选择", monthSpinnerData){i ->
                monthSpiner1.text = monthSpinnerData[i]
            }
        }

        //单日选择
        daySpiner1.text = daySpinnerData[0]
        daySpiner1.setOnClickListener {
            selector("选择", daySpinnerData){i ->
                daySpiner1.text = daySpinnerData[i]
            }
        }

        //颜色选择Spinner
         val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)
         val db = dbHelper.writableDatabase

        val colorList = ArrayList<String>()
        val cursor = db.query("ProduColor", null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do {
                val colorText = cursor.getString(cursor.getColumnIndex("produColor"))
                colorList.add(colorText)
            }while (cursor.moveToNext())
        }
        cursor.close()
        spiner2.text = colorList[0]
        spiner2.setOnClickListener{
            selector("选择", colorList){i ->
                spiner2.text = colorList[i]
            }
        }

        //工序选择、产品名称选择Spinner
         val unitArray = listOf("注塑", "吹塑", "挤出", "其他")
        lineSpiner.text = unitArray[0]
        lineSpiner.setOnClickListener {
            selector("工序名称",unitArray) { i ->
                lineSpiner.text = unitArray[i]

                if (lineSpiner.text == "注塑") {
                    val nameList1 = ArrayList<String>()
                    val cursor1 = db.query("ZhusuProduName", null, null, null, null, null, null)
                    if (cursor1.moveToFirst()) {
                        do {
                            val nameText = cursor1.getString(cursor1.getColumnIndex("produName"))
                            nameList1.add(nameText)
                        } while (cursor1.moveToNext())
                    }
                    cursor1.close()
                    spiner1.setText(nameList1[0])
                    spiner1.setOnClickListener {
                        selector("选择产品名称", nameList1) { i ->
                            spiner1.text = nameList1[i]
                        }
                    }


                } else if (lineSpiner.text == "吹塑") {
                    val nameList2 = ArrayList<String>()
                    val cursor2 = db.query("ChuisuProduName", null, null, null, null, null, null)
                    if (cursor2.moveToFirst()) {
                        do {
                            val nameText = cursor2.getString(cursor2.getColumnIndex("produName"))
                            nameList2.add(nameText)
                        } while (cursor2.moveToNext())
                    }
                    cursor2.close()
                    spiner1.setText(nameList2[0])
                    spiner1.setOnClickListener {
                        selector("选择产品名称", nameList2) { i ->
                            spiner1.text = nameList2[i]
                        }
                    }

                } else if (lineSpiner.text == "挤出") {
                    val nameList3 = ArrayList<String>()
                    val cursor3 = db.query("JichuProduName", null, null, null, null, null, null)
                    if (cursor3.moveToFirst()) {
                        do {
                            val nameText = cursor3.getString(cursor3.getColumnIndex("produName"))
                            nameList3.add(nameText)
                        } while (cursor3.moveToNext())
                    }
                    cursor3.close()
                    spiner1.setText(nameList3[0])
                    spiner1.setOnClickListener {
                        selector("选择产品名称", nameList3) { i ->
                            spiner1.text = nameList3[i]
                        }
                    }

                } else if (lineSpiner.text == "其他") {
                    val nameList4 = ArrayList<String>()
                    val cursor4 = db.query("OtherProduName", null, null, null, null, null, null)
                    if (cursor4.moveToFirst()) {
                        do {
                            val nameText = cursor4.getString(cursor4.getColumnIndex("produName"))
                            nameList4.add(nameText)
                        } while (cursor4.moveToNext())
                    }
                    cursor4.close()
                    spiner1.setText(nameList4[0])
                    spiner1.setOnClickListener {
                        selector("选择产品名称", nameList4) { i ->
                            spiner1.text = nameList4[i]
                        }
                    }

                }
            }
        }

/**/



//测试按键
        testButton.setOnClickListener {
            val lineName = lineSpiner.text
            Toast.makeText(this,"工序是：$lineName", Toast.LENGTH_SHORT).show()
        }

/*
       //刷新屏幕
        sysButton.setOnClickListener {
            daySpiner1.setText(daySpinnerData[0])//重置Spinner
            monthSpiner1.setText(daySpinnerData[0])
            lineSpiner.setText(unitArray[0])

        }*/

    }

}