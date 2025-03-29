package kr.ac.hallym.portfolio

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table if not exists USER_TB("+
                "_id integer primary key autoincrement," +
                "name not null," +
                "id not null," + "password not null," + "email TEXT,"+"ph_num TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists USER_TB")
        onCreate(p0)
    }
}