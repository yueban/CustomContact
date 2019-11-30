package com.yueban.customcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yueban.customcontact.account.CustomAccountManger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomAccountManger.addAccount()
    }
}
