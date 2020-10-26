package uk.co.sentinelweb.subtitler.ui.rsf

import android.speech.SpeechRecognizer

data class RecognizerServiceState(
    var speechRecognizer: SpeechRecognizer? = null,
    var startTimeMillis:Long = -1
)