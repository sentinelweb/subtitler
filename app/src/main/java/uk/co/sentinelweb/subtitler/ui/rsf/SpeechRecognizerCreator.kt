package uk.co.sentinelweb.subtitler.ui.rsf

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.lang.IllegalStateException
import java.util.*

class SpeechRecognizerCreator(val c: Context) {

    fun create(listener: SpeechRecognitionListener): SpeechRecognizer =
        if (SpeechRecognizer.isRecognitionAvailable(c)) {
            val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(c)
            speechRecognizer.setRecognitionListener(listener)
            speechRecognizer
        } else throw IllegalStateException("Cannot start recognition")

    fun createRecognizerIntent(): Intent =
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            .putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true)
            .putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            .putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
}