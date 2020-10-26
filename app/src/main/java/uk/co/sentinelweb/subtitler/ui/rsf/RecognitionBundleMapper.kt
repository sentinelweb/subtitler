package uk.co.sentinelweb.subtitler.ui.rsf

import android.os.Bundle
import android.speech.SpeechRecognizer

class RecognitionBundleMapper {

    fun mapBundle(b: Bundle):String? {
        val stringArrayList = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        return stringArrayList?.get(0)
    }
}