package uk.co.sentinelweb.subtitler.ui.rsf

import android.os.Bundle
import android.speech.RecognitionListener

class SpeechRecognitionListener(
    private val mapper: RecognitionBundleMapper
) : RecognitionListener {
    private lateinit var recognition: RecogniserServiceContract.Recognition

    fun setRecognition(recognition: RecogniserServiceContract.Recognition) {
        this.recognition = recognition
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        recognition.onRecognizerReady()
    }

    override fun onBeginningOfSpeech() {}

    override fun onPartialResults(p0: Bundle?) {
        p0?.let {
            mapper.mapBundle(it)
        }?.apply {
            recognition.partialTextResults(this)
        }
    }

    override fun onEndOfSpeech() {
        recognition.onFinishedSpeaking()
    }

    override fun onResults(p0: Bundle?) {
        p0?.let {
            mapper.mapBundle(it)
        }?.apply {
            recognition.partialTextResults(this)
        }
    }

    override fun onEvent(p0: Int, p1: Bundle?) {}

    override fun onRmsChanged(p0: Float) {}

    override fun onBufferReceived(p0: ByteArray?) {}

    override fun onError(p0: Int) {}
}