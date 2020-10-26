package uk.co.sentinelweb.subtitler.wrapper

import android.util.Log
import java.lang.Exception

class LogWrapper {

    val tag = TAG_DEFAULT

    fun d(msg:String) {
        Log.d(tag, msg)
    }

    fun e(ex: Exception, msg:String? = null) {
        Log.e(tag, msg, ex)
    }

    companion object {
        const val TAG_DEFAULT : String=  "Subtitler"
    }
}