package com.example.hangman

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hangman.databinding.FragmentLetterButtonsBinding

class LetterButtonsFragment : Fragment() {
    private val hangmanViewModel: HangmanViewModel by activityViewModels()
    private var _binding : FragmentLetterButtonsBinding?= null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    var numGuesses : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentLetterButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hangmanViewModel._numHint.observe(viewLifecycleOwner) { numHint ->
            if (hangmanViewModel._numHint.value == 2) {
                if (hangmanViewModel._numGuess.value == 4) {
                    Toast.makeText(context, "Hint not available!", Toast.LENGTH_SHORT).show()
                }
                hideButtons()

            } else if (hangmanViewModel._numHint.value == 3) {
                if (hangmanViewModel._numGuess.value == 4) {
                    Toast.makeText(context, "Hint not available!", Toast.LENGTH_SHORT).show()
                }
                showVowels()

            }
        }
        hangmanViewModel._numGuess.observe(viewLifecycleOwner) { numGuess ->
            if (numGuess == 4) {
                binding.apply {
                    resetTextBox()
                }
            }
        }
        binding.apply{
            aButton.setOnClickListener { handleCorrectButtonClick('A', aButton) }
            eButton.setOnClickListener { handleCorrectButtonClick('E', eButton) }
            gButton.setOnClickListener { handleCorrectButtonClick('G', gButton) }
            rButton.setOnClickListener { handleCorrectButtonClick('R', rButton) }
            bButton.setOnClickListener { handleButtonClick('B', bButton) }
            cButton.setOnClickListener { handleButtonClick('C', cButton) }
            dButton.setOnClickListener { handleButtonClick('D', dButton) }
            fButton.setOnClickListener { handleButtonClick('F', fButton) }
            hButton.setOnClickListener { handleButtonClick('H', hButton) }
            iButton.setOnClickListener { handleButtonClick('I', iButton) }
            jButton.setOnClickListener { handleButtonClick('J', jButton) }
            kButton.setOnClickListener { handleButtonClick('K', kButton) }
            lButton.setOnClickListener { handleButtonClick('L', lButton) }
            mButton.setOnClickListener { handleButtonClick('M', mButton) }
            nButton.setOnClickListener { handleButtonClick('N', nButton) }
            oButton.setOnClickListener { handleButtonClick('O', oButton) }
            pButton.setOnClickListener { handleButtonClick('P', pButton) }
            qButton.setOnClickListener { handleButtonClick('Q', qButton) }
            sButton.setOnClickListener { handleButtonClick('S', sButton) }
            tButton.setOnClickListener { handleButtonClick('T', tButton) }
            uButton.setOnClickListener { handleButtonClick('U', uButton) }
            vButton.setOnClickListener { handleButtonClick('V', vButton) }
            wButton.setOnClickListener { handleButtonClick('W', wButton) }
            xButton.setOnClickListener { handleButtonClick('X', xButton) }
            yButton.setOnClickListener { handleButtonClick('Y', yButton) }
            zButton.setOnClickListener { handleButtonClick('Z', zButton) }
            zButton.setOnClickListener { handleButtonClick('Z', zButton) }
            newGameButton.setOnClickListener { resetGame() }
        }
    }

    private fun handleCorrectButtonClick(letter: Char, btn: Button) {
        hangmanViewModel.addGuessedLetter(letter)
        when (letter) {
            'A' -> {
                binding.txtBox1.text = "A"
                hangmanViewModel.addClickedButton(btn)
                btn.visibility = View.INVISIBLE
            }
            'E' -> {
                binding.txtBox4.text = "E"
                binding.txtBox5.text = "E"
                hangmanViewModel.addClickedButton(btn)
                btn.visibility = View.INVISIBLE
            }
            'G' -> {
                binding.txtBox2.text = "G"
                hangmanViewModel.addClickedButton(btn)
                btn.visibility = View.INVISIBLE
            }
            'R' -> {
                binding.txtBox3.text = "R"
                hangmanViewModel.addClickedButton(btn)
                btn.visibility = View.INVISIBLE
            }
        }
    }

    private fun resetGame() {
        resetTextBox()
        resetButtons()
        hangmanViewModel._numGuess.value = 0
        hangmanViewModel._numHint.value = 0
    }

    private fun hideButtons() {
        val nonWordLetters = listOf(
            binding.bButton, binding.cButton, binding.dButton, binding.fButton,
            binding.hButton, binding.iButton, binding.jButton,
            binding.kButton, binding.lButton, binding.mButton, binding.nButton,
            binding.oButton, binding.pButton, binding.qButton, binding.sButton,
            binding.tButton, binding.uButton, binding.vButton, binding.wButton,
            binding.xButton, binding.yButton, binding.zButton
        )

        val notClicked = mutableListOf<Button>()

        for (button in nonWordLetters) {
            if (button !in hangmanViewModel._buttonsClicked.value.orEmpty()) {
                notClicked.add(button)
            }
        }
        notClicked.shuffle()
        val halfSize = notClicked.size / 2
        val currentButtonsClicked = hangmanViewModel._buttonsClicked.value.orEmpty().toMutableList()

        for (i in 0 until halfSize) {
            notClicked[i].visibility = View.INVISIBLE
            currentButtonsClicked.add(notClicked[i])
        }

        hangmanViewModel._buttonsClicked.value = currentButtonsClicked
        hangmanViewModel.incrementGuess()
    }

    private fun resetButtons() {
        hangmanViewModel._numGuess.value = 0
        hangmanViewModel._numHint.value = 0
        for (btn in hangmanViewModel._buttonsClicked.value?.toMutableList()!!) {
            btn.visibility = View.VISIBLE
        }
    }
    private fun resetTextBox() {
        binding.apply {
            txtBox1.text = getString(R.string.string_initial)
            txtBox2.text = getString(R.string.string_initial)
            txtBox3.text = getString(R.string.string_initial)
            txtBox4.text = getString(R.string.string_initial)
            txtBox5.text = getString(R.string.string_initial)
        }
    }
    private fun handleButtonClick(letter: Char, btn: Button) {
        hangmanViewModel.addGuessedLetter(letter)
        hangmanViewModel.incrementGuess()
        hangmanViewModel.addClickedButton(btn)
        numGuesses += 1
        Log.d("HangmanGame", "Number of Guesses from LetterButtonsFragment: $numGuesses")
        btn.visibility = View.INVISIBLE
        if (numGuesses >= 4) {
            resetTextBox()
            numGuesses = 0
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showVowels() {
        binding.apply {
            txtBox1.text = "A"
            txtBox4.text = "E"
            txtBox5.text = "E"
        }
        hangmanViewModel.incrementGuess()
    }
}