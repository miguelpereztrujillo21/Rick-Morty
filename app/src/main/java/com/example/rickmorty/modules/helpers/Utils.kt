package com.example.rickmorty.modules.helpers

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.example.rickmorty.R

object Utils {
    fun addEllipsisToSentence(sentence: String): String {
        return if (sentence.length > 12 && !sentence.endsWith(" ")) {
            sentence.substring(0, 12).trimEnd() + "..."
        } else {
            sentence.trimEnd()
        }
    }

    fun setStatusIcon(isAlive: String?): Int {
        return if (isAlive?.equals(Constants.STATUS_ALIVE) == true) {
            R.drawable.ic_live
        } else if (isAlive?.equals(Constants.STATUS_DEAD) == true) {
            R.drawable.ic_dead
        } else {
            R.drawable.ic_wanted
        }
    }

    fun setGenderIcon(gender: String?): Int {
        return if (gender?.equals(Constants.GENDER_MALE) == true) {
            R.drawable.ic_male
        } else if (gender?.equals(Constants.GENDER_FEMALE) == true) {
            R.drawable.ic_female
        } else {
            R.drawable.ic_question_mark
        }
    }

    fun showDialog(
        context: Context,
        title: String,
        description: String,
        activity: Activity,
        finishBtn: Boolean
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(description)
        if (finishBtn){
            builder.setPositiveButton(context.getString(R.string.error_dialog_close_btn)) { dialog, _ ->
                activity.finishAffinity()
            }
        }else{
            builder.setPositiveButton(context.getString(R.string.error_dialog_acept_btn_not_results)) { dialog, _ ->
                dialog.dismiss()
            }
        }



        val dialog = builder.create()
        dialog.show()
    }


}