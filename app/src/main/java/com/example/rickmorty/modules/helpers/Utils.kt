package com.example.rickmorty.modules.helpers

import com.example.rickmorty.R

object Utils {
    fun addEllipsisToSentence(sentence: String): String {
        return if (sentence.length > 15 && !sentence.endsWith(" ")) {
            sentence.substring(0, 15).trimEnd() + "..."
        } else {
            sentence.trimEnd()
        }
    }

    fun getIsAlive(isAlive: String?): Int {

        return if (isAlive?.equals(Constants.STATUS_ALIVE) == true) {
            R.drawable.ic_live
        } else if (isAlive?.equals(Constants.STATUS_DEAD) == true) {
            R.drawable.ic_dead
        } else {
            R.drawable.ic_wanted
        }

    }
}