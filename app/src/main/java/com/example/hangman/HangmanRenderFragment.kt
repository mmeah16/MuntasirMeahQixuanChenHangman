package com.example.hangman
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.hangman.databinding.FragmentHangmanRenderBinding
import com.example.hangman.databinding.FragmentLetterButtonsBinding
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels

class HangmanRenderFragment : Fragment() {
    private val hangmanViewModel: HangmanViewModel by activityViewModels()
    private var _binding : FragmentHangmanRenderBinding?= null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentHangmanRenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hangmanViewModel._numGuess.observe(viewLifecycleOwner) { numGuess ->
            if (numGuess in imageIds.indices) {
                binding.imageView1.setImageResource(imageIds[numGuess])
                Log.d("HangmanGame", "Number of Guesses: $numGuess")
            }
            if (numGuess == 4) {
                Toast.makeText(context, "You lose!", Toast.LENGTH_SHORT).show()
                hangmanViewModel._numGuess.value = 0
                Log.d("HangmanGame", "Number of Guesses: $numGuess")
                for (btn in hangmanViewModel._buttonsClicked.value?.toMutableList()!!) {
                    btn.visibility = View.VISIBLE
                }
            }
        }
        hangmanViewModel._numHint.observe(viewLifecycleOwner) { numHint ->

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     private val imageIds = arrayOf(
        R.drawable.hangman_1,
        R.drawable.hangman_2,
        R.drawable.hangman_3,
        R.drawable.hangman_4,
        R.drawable.hangman_5
    )
}