package com.example.storemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.add_produ_name_layout.*
import org.jetbrains.anko.selector

class AddProduName : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)

    var yearSpinnerData1 = arrayOf("2020","2021","2022","2023","2024")
    var monthSpinnerData1 = arrayOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
    var daySpinnerData1 = arrayOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",  "25", "26", "27", "28", "29", "30", "31")
    var lineArray = listOf("选择", "注塑", "吹塑", "挤出", "其他")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_produ_name_layout)
        supportActionBar?.hide()


        queryButton.setOnClickListener {
            val intent = Intent(this, ListProduName::class.java)//显示已录入颜色名称
            startActivity(intent)
        }


        lineSpiner1.text = lineArray[0]
        lineSpiner1.setOnClickListener {
            selector("工序名称", lineArray) { i ->
                lineSpiner1.text = lineArray[i]
            }
        }


        var ee = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearSpinnerData1)
        ee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(yearData1)
        {
            adapter = ee
            setSelection(0, false)
            onItemSelectedListener = this@AddProduName
            prompt = "选择"
            gravity = Gravity.CENTER
        }

        var ff = ArrayAdapter(this, android.R.layout.simple_spinner_item, monthSpinnerData1)
        ff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(monthData1)
        {
            adapter = ff
            setSelection(0, false)
            onItemSelectedListener = this@AddProduName
            prompt = "选择"
            gravity = Gravity.CENTER
        }
        var gg = ArrayAdapter(this, android.R.layout.simple_spinner_item, daySpinnerData1)
        gg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(dayData1)
        {
            adapter = gg
            setSelection(0, false)
            onItemSelectedListener = this@AddProduName
            prompt = "选择"
            gravity = Gravity.CENTER
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


    //选择spinner内容后再进行的下一步操作要在下面的方法中进行（比如获取显示的内容等）
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var year = yearData1.selectedItem//获取spinner显示的值
        var month = monthData1.selectedItem
        var day = dayData1.selectedItem
        var name = insertName.text.toString()//获取EditText值
        val yy = "年"
        val mm = "月"
        val dd = "日"

//写入产品名称

        addProduButton.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认")
                setMessage("请确认工序名称${lineSpiner1.text}？\n请确认产品名称是$name")
                setCancelable(false)

                setPositiveButton("确定"){_, _ ->
                    val db = dbHelper.writableDatabase
                    val values =
                        contentValuesOf("produName" to name, "date" to "${year}$yy${month}$mm${day}$dd")
                    if (lineSpiner1.text == "注塑") {
                        db.insert("ZhusuProduName", null, values)
                    }else if (lineSpiner1.text == "吹塑"){
                        db.insert("ChuisuProduName", null, values)
                    }else if (lineSpiner1.text == "挤出"){
                        db.insert("JichuProduName", null, values)
                    }else{
                        db.insert("OtherProduName", null, values)
                    }
                    //各编辑框返回初始值
                    insertName.setText("")
                    yearData1.setSelection(0, true)
                    monthData1.setSelection(0, true)
                    dayData1.setSelection(0, true)
                    lineSpiner1.text = lineArray[0]
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("取消"){ _, _ ->}
                show()
            }

        }


    }

}
