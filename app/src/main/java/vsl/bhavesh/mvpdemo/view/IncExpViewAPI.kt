package vsl.bhavesh.mvpdemo.view

import android.database.Cursor

interface IncExpViewAPI {

    // what you want to do when user click on Add button
    fun addResponse(msg:String)

    // What you want to present when user click on Read button
    fun readResponse(c:Cursor)
}