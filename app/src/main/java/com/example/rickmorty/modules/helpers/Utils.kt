package com.example.rickmorty.modules.helpers

object Utils {
    fun addEllipsisToSentence(sentence: String): String {
        return if (sentence.length > 15 && !sentence.endsWith(" ")) {
            sentence.substring(0, 15).trimEnd() + "..."
        } else {
            sentence.trimEnd()
        }
    }
}