package com.example.ekhata.util

import android.content.Context
import cn.pedant.SweetAlert.SweetAlertDialog

class SweetAlertDialogProvider {

    private var dialog: SweetAlertDialog? = null

    fun SweetAlertDialogBox(context: Context, title: String, message: String) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(title)
            .setContentText(message)
            .setConfirmText("OK")
            .show()
    }

    fun showErrorDialog(message: String, context: Context){
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Error")
            .setContentText(message)
            .setConfirmText("OK")
            .show()
    }
    
    fun showProgressDialog(message:String, context: Context){

        dialog = SweetAlertDialog(
            context,
            SweetAlertDialog.PROGRESS_TYPE
        )

        dialog?.titleText = message
        dialog?.setCancelable(false)
        dialog?.show()
    }

    fun dismissProgressDialog(){
        dialog?.dismissWithAnimation()
    }

    fun showSuccessDialog(message: String, context: Context, onConfirm: () -> Unit){

        SweetAlertDialog(
            context,
            SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("Success")
            .setContentText(message)
            .setConfirmClickListener {
                it.dismissWithAnimation()

                onConfirm()
            }
            .show()

    }

}