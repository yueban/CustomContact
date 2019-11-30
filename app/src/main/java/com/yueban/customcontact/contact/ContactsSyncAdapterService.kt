package com.yueban.customcontact.contact

import android.accounts.Account
import android.accounts.OperationCanceledException
import android.app.Service
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log

/**
 * @author yueban fbzhh007@gmail.com
 * @date 2019-11-30
 */
class ContactsSyncAdapterService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        return getSyncAdapter()!!.syncAdapterBinder
    }

    private fun getSyncAdapter(): SyncAdapterImpl? {
        if (sSyncAdapter == null) {
            sSyncAdapter =
                SyncAdapterImpl(this)
        }
        return sSyncAdapter
    }

    private class SyncAdapterImpl internal constructor(private val mContext: Context) :
        AbstractThreadedSyncAdapter(mContext, true) {
        override fun onPerformSync(
            account: Account,
            extras: Bundle,
            authority: String,
            provider: ContentProviderClient,
            syncResult: SyncResult
        ) {
            try {
                performSync(
                    mContext,
                    account,
                    extras,
                    authority,
                    provider,
                    syncResult
                )
            } catch (e: OperationCanceledException) {
                Log.e(TAG, "", e)
            }
        }

    }

    companion object {
        private val TAG = ContactsSyncAdapterService::class.java.simpleName
        private var sSyncAdapter: SyncAdapterImpl? = null
        @Throws(OperationCanceledException::class)
        private fun performSync(
            context: Context,
            account: Account,
            extras: Bundle,
            authority: String,
            provider: ContentProviderClient,
            syncResult: SyncResult
        ) {
            Log.d(
                TAG,
                "performSync: $account"
            )
        }
    }
}