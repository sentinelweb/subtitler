package uk.co.sentinelweb.subtitler.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import uk.co.sentinelweb.subtitler.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.intent_recog).setOnClickListener {
            findNavController().navigate(R.id.action_First_to_Intent)
        }

        view.findViewById<Button>(R.id.service_recog).setOnClickListener {
            findNavController().navigate(R.id.action_First_to_Service)
        }
    }
}