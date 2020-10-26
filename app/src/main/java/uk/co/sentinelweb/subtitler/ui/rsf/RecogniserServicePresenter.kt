package uk.co.sentinelweb.subtitler.ui.rsf

import android.content.Intent
import android.speech.RecognizerIntent
import uk.co.sentinelweb.subtitler.wrapper.LogWrapper

class RecogniserServicePresenter(
    private val view: RecogniserServiceContract.View,
    private val listener: SpeechRecognitionListener,
    private val creator: SpeechRecognizerCreator,
    private val state: RecognizerServiceState,
    private val log: LogWrapper
) : RecogniserServiceContract.Presenter, RecogniserServiceContract.Recognition {

    init {
        listener.setRecognition(this)
    }

    override fun startRecognition() {
        val create = creator.create(listener)
        create.startListening(creator.createRecognizerIntent())
        state.speechRecognizer = create
        state.startTimeMillis = System.currentTimeMillis() // todo use LocalDateTime
        log.d("Recognizer created")
    }

    override fun stopRecognition() {
        state.speechRecognizer?.stopListening()
        state.speechRecognizer?.destroy()
    }

    // region  RecogniserServiceContract.Recognition
    override fun textResults(text: String) {

    }

    override fun partialTextResults(text: String) {
        view.textResults(text)
        log.d("partial result")
    }


    override fun onFinishedSpeaking() {
        view.recognitionFinished()
        log.d("finished speaking")
    }

    override fun onRecognizerReady() {
        view.recognitionStarted()
        log.d("Recognizer ready")
    }
    // endregion
}