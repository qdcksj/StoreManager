package com.example.storemanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProduDatabaseHelper(val context:Context, name:String, version: Int):SQLiteOpenHelper(context, name, null, version) {

    //用户表1
    private val createUserMsg = "create table UserMsg(" +
            "id integer primary key autoincrement," +
            "userName text," +  //用户名
            "userPass text)"   // 密码

    //注塑表2
    private val createZhusuProduStore = "create table ZhusuProduStore(" +
            "id integer primary key autoincrement," +
            "produNum text," +   //产品编号
            "makeDate text," +   //生产日期
            "lineName text," +   //工序名称
            "produName text," +     //产品名称
            "produWeight real," +     //产品克重
            "produValue integer" +     //产品容量
            "produAmount integer" +   //生产数量
            "produMenu text)"       //备注说明
    //吹塑表3
    private val createChuisuProduStore = "create table ChuisuProduStore(" +
            "id integer primary key autoincrement," +
            "produNum text," +   //产品编号
            "makeDate text," +   //生产日期
            "lineName text," +   //工序名称
            "produName text," +     //产品名称
            "produWeight real," +     //产品克重
            "produValue integer" +     //产品容量
            "produAmount integer" +   //生产数量
            "produMenu text)"       //备注说明
    //挤出表4
    private val createJichuProduStore = "create table JichuProduStore(" +
            "id integer primary key autoincrement," +
            "produNum text," +   //产品编号
            "makeDate text," +   //生产日期
            "lineName text," +   //工序名称
            "produName text," +     //产品名称
            "produWeight real," +     //产品克重
            "produValue integer" +     //产品容量
            "produAmount integer" +   //生产数量
            "produMenu text)"       //备注说明
    //其他表5
    private val createOtherProduStore = "create table OtherProduStore(" +
            "id integer primary key autoincrement," +
            "produNum text," +   //产品编号
            "makeDate text," +   //生产日期
            "lineName text," +   //工序名称
            "produName text," +     //产品名称
            "produWeight real," +     //产品克重
            "produValue integer" +     //产品容量
            "produAmount integer" +   //生产数量
            "produMenu text)"       //备注说明
    //产品名称库6、7、8、9
    private val createZhusuProduName = "create table ZhusuProduName(" +
            "id integer primary key autoincrement," +
            "date text," +//记录日期
            "produName text)"  //产品名称
    private val createChuisuProduName = "create table ChuisuProduName(" +
            "id integer primary key autoincrement," +
            "date text," +//记录日期
            "produName text)"  //产品名称
    private val createJichuProduName = "create table JichuProduName(" +
            "id integer primary key autoincrement," +
            "date text," +//记录日期
            "produName text)"  //产品名称
    private val createOtherProduName = "create table OtherProduName(" +
            "id integer primary key autoincrement," +
            "date text," +//记录日期
            "produName text)"  //产品名称
    //产品颜色库10
    private val createProduColor = "create table ProduColor(" +
            "id integer primary key autoincrement," +
            "date text," +  //记录日期
            "produColor text)"  //产品名称

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUserMsg)
        db.execSQL(createZhusuProduStore)
        db.execSQL(createChuisuProduStore)
        db.execSQL(createJichuProduStore)
        db.execSQL(createOtherProduStore)
        db.execSQL(createZhusuProduName)
        db.execSQL(createChuisuProduName)
        db.execSQL(createJichuProduName)
        db.execSQL(createOtherProduName)
        db.execSQL(createProduColor)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersoin: Int) {

    }

}