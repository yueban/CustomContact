package com.yueban.customcontact.contact

import android.content.ContentProviderOperation
import android.content.OperationApplicationException
import android.net.Uri
import android.os.RemoteException
import android.provider.ContactsContract
import android.widget.Toast
import com.yueban.customcontact.MyApp
import com.yueban.customcontact.account.CustomAccountManger
import java.util.*

/**
 * @author yueban fbzhh007@gmail.com
 * @date 2019-11-30
 */
class CustomContactManager {
    companion object {
        const val CONTACT_MIME_TYPE_MESSAGE =
            "vnd.android.cursor.item/vnd.com.yueban.customcontact.message"
        const val CONTACT_MIME_TYPE_CALL =
            "vnd.android.cursor.item/vnd.com.yueban.customcontact.call"

        fun addContact(
            name: String?,
            phoneNumber: String
        ) {
            val ops =
                ArrayList<ContentProviderOperation>()
            ops.add(
                ContentProviderOperation.newInsert(
                    addCallerIsSyncAdapterParameter(
                        ContactsContract.RawContacts.CONTENT_URI
                    )
                ).withValue(
                    ContactsContract.RawContacts.ACCOUNT_NAME,
                    CustomAccountManger.ACCOUNT_NAME
                ).withValue(
                    ContactsContract.RawContacts.ACCOUNT_TYPE,
                    CustomAccountManger.ACCOUNT_TYPE
                ).build()
            )
            ops.add(
                ContentProviderOperation.newInsert(
                    addCallerIsSyncAdapterParameter(
                        ContactsContract.Settings.CONTENT_URI
                    )
                ).withValue(
                    ContactsContract.RawContacts.ACCOUNT_NAME,
                    CustomAccountManger.ACCOUNT_NAME
                ).withValue(
                    ContactsContract.RawContacts.ACCOUNT_TYPE,
                    CustomAccountManger.ACCOUNT_TYPE
                ).withValue(ContactsContract.Settings.UNGROUPED_VISIBLE, 1).build()
            )
            ops.add(
                ContentProviderOperation.newInsert(
                    addCallerIsSyncAdapterParameter(
                        ContactsContract.Data.CONTENT_URI
                    )
                )
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                    ).withValue(
                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        name
                    ).build()
            )
            ops.add(
                ContentProviderOperation.newInsert(
                    addCallerIsSyncAdapterParameter(
                        ContactsContract.Data.CONTENT_URI
                    )
                ).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                ).withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber).build()
            )

            // custom mimeType
            ops.add(
                ContentProviderOperation.newInsert(
                    addCallerIsSyncAdapterParameter(
                        ContactsContract.Data.CONTENT_URI
                    )
                ).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(
                    ContactsContract.Data.MIMETYPE,
                    CONTACT_MIME_TYPE_MESSAGE
                ).withValue(ContactsContract.Data.DATA1, phoneNumber).withValue(
                    ContactsContract.Data.DATA2,
                    "message: $phoneNumber"
                ).build()
            )

            ops.add(
                ContentProviderOperation.newInsert(
                    addCallerIsSyncAdapterParameter(ContactsContract.Data.CONTENT_URI)
                )
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                        ContactsContract.Data.MIMETYPE,
                        CONTACT_MIME_TYPE_CALL
                    )
                    .withValue(ContactsContract.Data.DATA1, phoneNumber)
                    .withValue(
                        ContactsContract.Data.DATA2,
                        "call: $phoneNumber"
                    )
                    .build()
            )

            try {
                MyApp.getContext().contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
                Toast.makeText(MyApp.getContext(), "add contact success", Toast.LENGTH_SHORT).show()
            } catch (e: OperationApplicationException) {
                e.printStackTrace()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        fun clearAll() {
            val resolver = MyApp.getContext().contentResolver
            resolver.delete(
                ContactsContract.RawContacts.CONTENT_URI,
                ContactsContract.RawContacts.ACCOUNT_TYPE + " = ?",
                arrayOf(CustomAccountManger.ACCOUNT_TYPE)
            )
        }

        private fun addCallerIsSyncAdapterParameter(uri: Uri): Uri? =
            uri.buildUpon()
                .appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true")
                .build()
    }
}