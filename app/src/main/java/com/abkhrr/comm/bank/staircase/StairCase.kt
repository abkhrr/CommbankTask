package com.abkhrr.comm.bank.staircase

import com.abkhrr.comm.bank.utils.log.MyLog

class StairCase {
    fun stairCaseLogic(n: Int){
        for (x in 1..n) {
            for (y in 1..n ) {
                val data = if ((x+y) > n) '#' else ' '
                print(data)
                MyLog.d("DATA STAIRCASE", data.toString())
            }
            println()
        }
    }
}