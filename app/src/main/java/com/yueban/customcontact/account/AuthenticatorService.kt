package com.yueban.customcontact.account

import android.accounts.*
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder

/**
 * @author yueban fbzhh007@gmail.com
 * @date 2019-11-30
 */
class AuthenticatorService : Service() {
    private var authenticator: Authenticator? = null

    protected fun getAuthenticator(): Authenticator {
        if (authenticator == null) {
            authenticator = Authenticator(this)
        }
        return authenticator!!
    }

    override fun onBind(intent: Intent): IBinder? {
        return if (AccountManager.ACTION_AUTHENTICATOR_INTENT == intent.action) {
            getAuthenticator().iBinder
        } else {
            null
        }
    }

    class Authenticator internal constructor(context: Context?) :
        AbstractAccountAuthenticator(context) {
        override fun addAccount(
            response: AccountAuthenticatorResponse,
            accountType: String,
            authTokenType: String,
            requiredFeatures: Array<String>,
            options: Bundle
        ): Bundle? {
            return null
        }

        @Throws(NetworkErrorException::class)
        override fun getAccountRemovalAllowed(
            response: AccountAuthenticatorResponse,
            account: Account
        ): Bundle {
            return super.getAccountRemovalAllowed(response, account)
        }

        override fun confirmCredentials(
            response: AccountAuthenticatorResponse?,
            account: Account?,
            options: Bundle?
        ): Bundle? {
            return null
        }

        override fun editProperties(
            response: AccountAuthenticatorResponse,
            accountType: String
        ): Bundle? {
            return null
        }

        override fun getAuthToken(
            response: AccountAuthenticatorResponse,
            account: Account,
            authTokenType: String,
            options: Bundle
        ): Bundle? {
            return null
        }

        override fun getAuthTokenLabel(authTokenType: String): String? {
            return null
        }

        override fun hasFeatures(
            response: AccountAuthenticatorResponse,
            account: Account,
            features: Array<String>
        ): Bundle? {
            return null
        }

        override fun updateCredentials(
            response: AccountAuthenticatorResponse, account: Account, authTokenType: String,
            options: Bundle
        ): Bundle? {
            return null
        }
    }

}