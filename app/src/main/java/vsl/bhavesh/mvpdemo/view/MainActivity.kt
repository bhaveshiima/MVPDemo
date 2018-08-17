package vsl.bhavesh.mvpdemo.view

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.InCallService
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import vsl.bhavesh.mvpdemo.R
import vsl.bhavesh.mvpdemo.beans.IncExtBean
import vsl.bhavesh.mvpdemo.model.IncExpImpl
import vsl.bhavesh.mvpdemo.presenter.IncExcPresenterAPI

class MainActivity : IncExpViewAPI,AppCompatActivity() {
    override fun addResponse(msg: String) {
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show()
    }

    override fun readResponse(c: Cursor) {

        var inc_sum = 0
        var exp_sum = 0

        var list:MutableList<String> = mutableListOf()
        while (c.moveToNext()){
            list.add(c.getString(1)+"\t"+c.getInt(2)+"\n"
                    +c.getString(3)+"\t"+c.getString(4))
            if(c.getString(4).equals("Income")) {
                inc_sum = inc_sum + c.getInt(2)
            }else{
                exp_sum = exp_sum + c.getInt(2)
            }
        }

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,list)
        lview.adapter = adapter
        isum.text = inc_sum.toString()
        esum.text = exp_sum.toString()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var api:IncExcPresenterAPI = IncExpImpl(mActivity = this)


        // When user click on Add button then call the Add method of Model class
        add.setOnClickListener {

            var bean = IncExtBean(et1.text.toString(),
                                  et2.text.toString().toInt(),
                                  et3.text.toString(),
                                  sp1.selectedItem.toString())
            api.addInput(bean)
        }


        // All the Read method of Model class
        read.setOnClickListener {
            api.readInput()
        }

    }
}
