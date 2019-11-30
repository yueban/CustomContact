package com.yueban.customcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yueban.customcontact.contact.CustomContactManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        when (intent.type) {
            CustomContactManager.CONTACT_MIME_TYPE_MESSAGE -> {
                text_view.text = "message from contact"
            }
            CustomContactManager.CONTACT_MIME_TYPE_CALL -> {
                text_view.text = "call from contact"
            }
        }
    }
}