package uk.co.sentinelweb.subtitler.ui.rsf

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_service.*
import uk.co.sentinelweb.subtitler.R
import uk.co.sentinelweb.subtitler.wrapper.LogWrapper
import java.util.*

/**
 * Speech recognition using [android.speech.SpeechRecognizer]
 */
class RecogniserServiceFragment : Fragment(), RecogniserServiceContract.View {
    private lateinit var presenter: RecogniserServiceContract.Presenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RecogniserServicePresenter(
            this,
            SpeechRecognitionListener(RecognitionBundleMapper()),
            SpeechRecognizerCreator(view.context),
            RecognizerServiceState(),
            LogWrapper()
        )
        speak_start.setOnClickListener{ presenter.startRecognition() }
        speak_finish.setOnClickListener{ presenter.stopRecognition() }

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_Service_to_First)
        }
    }

    override fun textResults(text: String) {
        text_recognized.text = text
    }

    override fun recognitionStarted() {
        speak_start.isVisible = false
        speak_finish.isVisible = true
    }

    override fun recognitionFinished() {
        speak_start.isVisible = true
        speak_finish.isVisible = false
    }

}