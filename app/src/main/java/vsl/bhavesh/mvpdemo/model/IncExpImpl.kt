package vsl.bhavesh.mvpdemo.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import vsl.bhavesh.mvpdemo.beans.IncExtBean
import vsl.bhavesh.mvpdemo.presenter.IncExcPresenterAPI
import vsl.bhavesh.mvpdemo.view.MainActivity

class IncExpImpl:IncExcPresenterAPI {

    //globle define
    var mActivity:MainActivity? = null
    var sqlite:SQLiteDatabase? = null
    //database and table login write inside the constructor
    constructor(mActivity:MainActivity){
        this.mActivity = mActivity

        // Create database
        sqlite = mActivity.openOrCreateDatabase("incexp", Context.MODE_PRIVATE, null)

        // Create table and define fields
        sqlite!!.execSQL("create table if not exists incexp(_id integer primary key autoincrement, date varchar(20), money integer, description varchar(100), type varchar(100))")
    }


    override fun addInput(bean: IncExtBean) {

        // Set Content Value
        var cv = ContentValues()
        cv.put("date",bean.date)
        cv.put("money", bean.money)
        cv.put("description", bean.description)
        cv.put("type", bean.type)


        var status = sqlite!!.insert("incexp",null,cv)

        // Check the status of insert query [ START ]
        if(status == -1.toLong()){
            mActivity!!.addResponse("Data Inserted Failed...!")
        }else{
            mActivity!!.addResponse("Data Inserted Successfully...!")
        }
        // Check the status of insert query [ END ]


    }

    override fun readInput() {

        var cursor = sqlite!!.query("incexp",null,null,null,
                    null,null,null)
        mActivity!!.readResponse(cursor)

    }
}