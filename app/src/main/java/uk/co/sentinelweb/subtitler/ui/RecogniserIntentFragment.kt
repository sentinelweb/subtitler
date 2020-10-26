package uk.co.sentinelweb.subtitler.ui

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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uk.co.sentinelweb.subtitler.R
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RecogniserIntentFragment : Fragment() {
    private lateinit var speak: ImageView
    private lateinit var text: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val inflate = inflater.inflate(R.layout.fragment_second, container, false)

        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        speak = view.findViewById(R.id.speak)
        text = view.findViewById(R.id.text)
        speak.setOnClickListener{
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")
            try {
                startActivityForResult(intent,
                    REQ_CODE
                )
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    it.context.applicationContext,
                    "Sorry your device not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_Intent_to_First)
        }
    }

    override fun onActivityResult( requestCode:Int,  resultCode:Int,  data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE -> {
                if (resultCode == RESULT_OK &&  data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    text.setText(result?.get(0))
                }
            }
        }
    }

    companion object {
        private const val REQ_CODE = 100
    }
}