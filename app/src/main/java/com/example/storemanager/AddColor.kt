package com.example.storemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.add_color_layout.*

class AddColor : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var yearSpinnerData = arrayOf("0000","2020","2021","2022","2023","2024")
    var monthSpinnerData = arrayOf("00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
    var daySpinnerData = arrayOf("00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",  "25", "26", "27", "28", "29", "30", "31")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_color_layout)
        supportActionBar?.hide()
        val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)


//浏览颜色名称点击事件
        listButton.setOnClickListener {
            val intent = Intent(this, ListColor::class.java)//显示已录入颜色名称
            startActivity(intent)
        }

 //删除颜色名称点击事件
        editDelButton.setOnClickListener {
            val intent1 =Intent(this, EditDelColor::class.java)
            startActivity(intent1)

        }


        delButton.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("警告")
                setMessage("本操作不可逆，会永久清除数据库内容！确认清除吗？")
                setCancelable(false)
                setPositiveButton("确定"){_, _ ->
                    val db = dbHelper.writableDatabase
                    db.delete("produColor", null, null)
                    Toast.makeText(context, "已清除数据库", Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("取消"){ _, _ ->}
                show()
            }

        }


        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearSpinnerData)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(yearData)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@AddColor
            prompt = "选择"
            gravity = Gravity.CENTER
        }

        var bb = ArrayAdapter(this, android.R.layout.simple_spinner_item, monthSpinnerData)
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(monthData)
        {
            adapter = bb
            setSelection(0, false)
            onItemSelectedListener = this@AddColor
            prompt = "选择"
            gravity = Gravity.CENTER
        }
        var cc = ArrayAdapter(this, android.R.layout.simple_spinner_item, daySpinnerData)
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(dayData)
        {
            adapter = cc
            setSelection(0, false)
            onItemSelectedListener = this@AddColor
            prompt = "选择"
            gravity = Gravity.CENTER
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
    //选择spinner内容后再进行的下一步操作要在下面的方法中进行（比如获取显示的内容等）
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var year = yearData.selectedItem//获取spinner显示的值
        var month = monthData.selectedItem
        var day = dayData.selectedItem
        var color = insertColor.text.toString()//获取EditText值
        val yy = "年"
        val mm = "月"
        val dd = "日"

        val dbHelper = ProduDatabaseHelper(this, "ProduStore.db",  1)
        addColorButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = contentValuesOf("produColor" to color, "date" to "${year}$yy${month}$mm${day}$dd")
            db.insert("ProduColor", null, values)

            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show()
            //初始化输入框内数据
            insertColor.setText(null)
            yearData.setSelection(0)
            monthData.setSelection(0)
            dayData.setSelection(0)
        }

    }

}