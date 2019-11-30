package com.yueban.customcontact.account

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Build
import com.yueban.customcontact.MyApp
import com.yueban.customcontact.R

/**
 * @author yueban fbzhh007@gmail.com
 * @date 2019-11-30
 */
class CustomAccountManger {
    companion object {
        val ACCOUNT_NAME = MyApp.getContext().getString(R.string.account_name)
        val ACCOUNT_TYPE = MyApp.getContext().getString(R.string.account_type)

        fun addAccount() {
            val account = Account(ACCOUNT_NAME, ACCOUNT_TYPE)
            AccountManager.get(MyApp.getContext()).addAccountExplicitly(account, "", null)
        }

        fun deleteAccount() {
            val account = Account(ACCOUNT_NAME, ACCOUNT_TYPE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                AccountManager.get(MyApp.getContext()).removeAccountExplicitly(account)
            } else {
                AccountManager.get(MyApp.getContext()).removeAccount(account, null, null)
            }
        }
    }
}