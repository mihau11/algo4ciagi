package com.example.algorytmynaiwne

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.util.function.BiFunction


const val PRIME: Int = 101

class Algorytmy(wzorzec: String,iloscznakow: Int) {
    var wzorzec = wzorzec
    var iloscznakow = iloscznakow
    //BF
    @RequiresApi(Build.VERSION_CODES.O)
    fun bruteForce(): Long {
        val czasTeraz = LocalDateTime.now()
        val patternLength = wzorzec.length
        val textLength = minOf(wzorzec.length, iloscznakow)


        for (i in 0..textLength - patternLength) {
            var j = 0

            while (j < patternLength && wzorzec[i + j] == wzorzec[j]) {
                j++
            }

            if (j == patternLength) {
                val czasPoWykonaniu1 = LocalDateTime.now()
                val roznicaCzasow1 = Duration.between(czasTeraz, czasPoWykonaniu1)
                val czasWykonaniaMillis1 = roznicaCzasow1.toMillis()
                return czasWykonaniaMillis1
            }
        }
        val czasPoWykonaniu = LocalDateTime.now()
        val roznicaCzasow = Duration.between(czasTeraz, czasPoWykonaniu)
        val czasWykonaniaMillis = roznicaCzasow.toMillis()
        return czasWykonaniaMillis
    }
    //KMP

    fun lps(): IntArray {
        val m = wzorzec.length
        val lps = IntArray(m) { 0 }
        var len = 0
        var i = 1

        while (i < m) {
            if (wzorzec[i] == wzorzec[len]) {
                len++
                lps[i] = len
                i++
            } else {
                if (len > 0) {
                    len = lps[len - 1]
                } else {
                    lps[i] = 0
                    i++
                }
            }
        }

        return lps
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun kmp(): Long {
        val czasTeraz = LocalDateTime.now()
        val m = wzorzec.length
        val n = minOf(iloscznakow, wzorzec.length)
        val lps = lps()
        var i = 0
        var j = 0
        while (i < n) {
            if (wzorzec[j] == wzorzec[i]) {
                i++
                j++
            }
            if (j == m) {
                val czasPoWykonaniu1 = LocalDateTime.now()
                val czasWykonaniaMillis1 = Duration.between(czasTeraz, czasPoWykonaniu1).toMillis()
                return czasWykonaniaMillis1
            } else if (i < n && wzorzec[j] != wzorzec[i]) {
                if (j > 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }
        val czasPoWykonaniu = LocalDateTime.now()
        val czasWykonaniaMillis = Duration.between(czasTeraz, czasPoWykonaniu).toMillis()
        return czasWykonaniaMillis
    }
    //BM
    @RequiresApi(Build.VERSION_CODES.O)
    fun boyerMoore(): Long {
        val czasTeraz = LocalDateTime.now()
        val patternLength = wzorzec.length
        val textLength = wzorzec.length
        val badChar = IntArray(256) { patternLength }
        for (i in 0 until patternLength - 1) {
            badChar[wzorzec[i].toInt()] = patternLength - 1 - i
        }
        var shift = 0
        var foundIndex = -1
        while (shift <= textLength - patternLength) {
            var j = patternLength - 1
            while (j >= 0 && wzorzec[j] == wzorzec[shift + j]) {
                j--
            }
            if (j < 0) {
                foundIndex = shift
                break
            } else {
                shift += Math.max(1, badChar[wzorzec[shift + j].toInt()] - patternLength + 1 + j)
            }
            if (shift > iloscznakow) {
                break
            }
        }
        val czasPoWykonaniu = LocalDateTime.now()
        val roznicaCzasow = Duration.between(czasTeraz, czasPoWykonaniu)
        val czasWykonaniaMillis = roznicaCzasow.toMillis()
        return czasWykonaniaMillis
    }
    //RK

    @RequiresApi(Build.VERSION_CODES.O)
    fun rabinKarp(): Long {
        val czasTeraz = LocalDateTime.now()
        val patternLength = wzorzec.length
        val textLength = wzorzec.length
        var patternHash = 0
        var textHash = 0
        var power = 1
        for (i in 0 until patternLength) {
            patternHash = (patternHash * PRIME + wzorzec[i].toInt()) % Int.MAX_VALUE
            textHash = (textHash * PRIME + wzorzec[i].toInt()) % Int.MAX_VALUE
            if (i < patternLength - 1) {
                power = (power * PRIME) % Int.MAX_VALUE
            }
        }
        var foundIndex = -1
        var shift = 0
        while (shift <= textLength - patternLength) {
            if (patternHash == textHash) {
                var j = 0
                while (j < patternLength && wzorzec[j] == wzorzec[shift + j]) {
                    j++
                }
                if (j == patternLength) {
                    val czasPoWykonaniu1 = LocalDateTime.now()
                    val roznicaCzasow1 = Duration.between(czasTeraz, czasPoWykonaniu1)
                    val czasWykonaniaMillis1 = roznicaCzasow1.toMillis()
                    return czasWykonaniaMillis1
                }
            }
            if (shift < textLength - patternLength) {
                textHash = (PRIME * (textHash - wzorzec[shift].toInt() * power) + wzorzec[shift + patternLength].toInt()) % Int.MAX_VALUE
                if (textHash < 0) {
                    textHash += Int.MAX_VALUE
                }
            }
            shift++
            if (shift > iloscznakow) {
                break
            }
        }
        val czasPoWykonaniu = LocalDateTime.now()
        val roznicaCzasow = Duration.between(czasTeraz, czasPoWykonaniu)
        val czasWykonaniaMillis = roznicaCzasow.toMillis()
        return czasWykonaniaMillis
    }

}