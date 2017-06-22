package com.example.skn.framework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.TextView as ads
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity(name: String) : BaseActivity(name) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val user = User("张", "18", "20")
        Log.i("1234", user.name)
        val a1: Any = 5 //any类似于object
        if (a1 is Int) {
        }
        if (a1 is String) {
        }
        var a2 = a1()
        var a4: ads
        var a3 = a2()


        val a5 = 1
        var a6 = a5.toDouble()

        val  a7='3'
        var  a8=a7.toInt()

    }

    fun a1(num: Int = 1): Int = 1

    fun a2(): Unit {
    }

    data class User(var name: String, var age: String, var number: String) {
        init {
            name = "傻逼"
        }
    }

    class myAdapter(var list: Array<String>) : RecyclerView.Adapter<myAdapter.holder>() {
        override fun onBindViewHolder(holder: holder?, position: Int) {
            holder?.a5?.text = list[position]
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): holder {
            val inflate = LayoutInflater.from(parent?.context).inflate(R.layout.abc_action_bar_title_item, parent, false)
            return holder(inflate)
        }

        override fun getItemCount(): Int {
            return list.size
        }


        class holder(view: View) : RecyclerView.ViewHolder(view) {
            var a5: ads? = null
        }
    }

    //给content 加上一个
//    fun Context.showMsg(msg: String, duration: Int = Toast.LENGTH_SHORT) {
//        Toast.makeText(this, msg, duration).show()
//    }
}




