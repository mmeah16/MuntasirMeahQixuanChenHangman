package com.example.hangman

import android.os.Bundle


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hangman.databinding.FragmentHangmanRenderBinding
import com.example.hangman.databinding.FragmentHintBinding

class HintFragment : Fragment() {
    private val hangmanViewModel: HangmanViewModel by activityViewModels()
    private var _binding : FragmentHintBinding?= null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var hint : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentHintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            hintButton.setOnClickListener { getHint() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getHint() {
        binding.apply {
            if (hint == 0) {
                hintTextview?.text = "HINT: Verb"
            }
        }
    }


}