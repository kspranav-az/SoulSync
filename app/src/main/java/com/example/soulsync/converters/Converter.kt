package com.example.soulsync.converters

import java.text.SimpleDateFormat

class Converter {
    companion object{
        fun longToDate(milisec : Long) : String{
            return (SimpleDateFormat("dd-mm-yyyy")).format(milisec)
        }

        fun longToTime(milisec : Long) : String{
            return (SimpleDateFormat("hh:mm a")).format(milisec)
        }
    }
}

fun containsCurseWords(input: String, curseWords: List<String>): Boolean {
    val pattern = curseWords.joinToString(separator = "|") { "\\b$it\\b" }.toRegex(RegexOption.IGNORE_CASE)
    val filteredText = pattern.replace(input) {
        val match = it.value
        "*".repeat(match.length)
    }
    return input != filteredText
}