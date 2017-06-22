package com.example.skn.framework

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.alibaba.fastjson.JSONObject

import java.io.FileNotFoundException
import java.io.PrintWriter

class TestActivity : AppCompatActivity() {

    private val textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val textView: TextView
    }


    internal inner class my private constructor(private val stringList: List<String>) : RecyclerView.Adapter<my.a>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): a? {
            return null
        }

        override fun onBindViewHolder(holder: a, position: Int) {

        }

        override fun getItemCount(): Int {
            return stringList.size
        }

        internal inner class a(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

}
