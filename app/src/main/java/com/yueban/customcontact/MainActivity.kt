package com.yueban.customcontact

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yueban.customcontact.account.CustomAccountManger
import com.yueban.customcontact.contact.CustomContactManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomAccountManger.addAccount()

        RxPermissions(this).request(
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS
        ).filter { it }.subscribe {
            CustomContactManager.clearAll()
            CustomContactManager.addContact("Bob", "010-1234")
        }
    }
}
