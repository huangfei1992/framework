package com.example.skn.framework

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by skn on 2017/6/20.

 */
abstract class BaseActivity(name :String) : AppCompatActivity() {



    fun Context.showMsg(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }
}


