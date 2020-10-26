package uk.co.sentinelweb.subtitler.ui.rsf

import uk.co.sentinelweb.subtitler.domain.TimedText


interface RecogniserServiceContract {

    interface Presenter {
        fun startRecognition()
        fun stopRecognition()
    }

    interface View {
        fun textResults(text:String)
        fun recognitionStarted()
        fun recognitionFinished()
    }

    interface Recognition {
        fun partialTextResults(text:String)
        fun textResults(text:String)
        fun onFinishedSpeaking()
        fun onRecognizerReady()
    }

}