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
import androidx.lifecycle.MutableLiveData
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
                hideButtons()

            } else if (hangmanViewModel._numHint.value == 3) {
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
        hangmanViewModel._correctLetters.observe(viewLifecycleOwner) { correctLetters ->
            for (letter in correctLetters) {
                if (letter == 'A') {
                    binding.txtBox1.text = letter.toString()
                } else if (letter == 'G') {
                    binding.txtBox2.text = letter.toString()
                } else if (letter == 'R') {
                    binding.txtBox3.text = letter.toString()
                }  else if (letter == 'E') {
                    binding.txtBox4.text = letter.toString()
                    binding.txtBox5.text = letter.toString()
                }

            }
        }

        hangmanViewModel._buttonsClicked.observe(viewLifecycleOwner) { buttonsClicked ->
            Log.d("HangmanGame", "These are all the button clicked: $buttonsClicked")
            for (buttonState in buttonsClicked) {
                val buttonId = buttonState.id
                val buttonView = when (buttonId) {
                    R.id.a_button -> binding.aButton
                    R.id.b_button -> binding.bButton
                    R.id.c_button -> binding.cButton
                    R.id.d_button -> binding.dButton
                    R.id.e_button -> binding.eButton
                    R.id.f_button -> binding.fButton
                    R.id.g_button -> binding.gButton
                    R.id.h_button -> binding.hButton
                    R.id.i_button -> binding.iButton
                    R.id.j_button -> binding.jButton
                    R.id.k_button -> binding.kButton
                    R.id.l_button -> binding.lButton
                    R.id.m_button -> binding.mButton
                    R.id.n_button -> binding.nButton
                    R.id.o_button -> binding.oButton
                    R.id.p_button -> binding.pButton
                    R.id.q_button -> binding.qButton
                    R.id.r_button -> binding.rButton
                    R.id.s_button -> binding.sButton
                    R.id.t_button -> binding.tButton
                    R.id.u_button -> binding.uButton
                    R.id.v_button -> binding.vButton
                    R.id.w_button -> binding.wButton
                    R.id.x_button -> binding.xButton
                    R.id.y_button -> binding.yButton
                    R.id.z_button -> binding.zButton
                    // Add bindings for other buttons here
                    else -> null
                }
                buttonView?.visibility = View.INVISIBLE
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
                val currentList = hangmanViewModel._correctLetters.value?.toMutableList() ?: mutableListOf()
                currentList.add(0, 'A')
                hangmanViewModel._correctLetters.value = currentList
                btn.visibility = View.INVISIBLE
            }
            'E' -> {
                binding.txtBox4.text = "E"
                binding.txtBox5.text = "E"
                hangmanViewModel.addClickedButton(btn)
                val currentList = hangmanViewModel._correctLetters.value?.toMutableList() ?: mutableListOf()
                currentList.add(3, 'E')
                currentList.add(4, 'E')
                hangmanViewModel._correctLetters.value = currentList
                btn.visibility = View.INVISIBLE
            }
            'G' -> {
                binding.txtBox2.text = "G"
                hangmanViewModel.addClickedButton(btn)
                val currentList = hangmanViewModel._correctLetters.value?.toMutableList() ?: mutableListOf()
                currentList.add(1, 'G')
                hangmanViewModel._correctLetters.value = currentList
                btn.visibility = View.INVISIBLE
            }
            'R' -> {
                binding.txtBox3.text = "R"
                hangmanViewModel.addClickedButton(btn)
                val currentList = hangmanViewModel._correctLetters.value?.toMutableList() ?: mutableListOf()
                currentList.add(2, 'R')
                hangmanViewModel._correctLetters.value = currentList
                btn.visibility = View.INVISIBLE
            }
        }
    }


    private fun resetGame() {
        resetTextBox()
        resetButtons()
        hangmanViewModel._buttonsClicked.value = emptyList()
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
        val buttonsClicked = hangmanViewModel._buttonsClicked.value
        if (buttonsClicked != null) {
            for (buttonState in buttonsClicked) {
                val buttonId = buttonState.id
                val buttonView = when (buttonId) {
                    R.id.a_button -> binding.aButton
                    R.id.b_button -> binding.bButton
                    R.id.c_button -> binding.cButton
                    R.id.d_button -> binding.dButton
                    R.id.e_button -> binding.eButton
                    R.id.f_button -> binding.fButton
                    R.id.g_button -> binding.gButton
                    R.id.h_button -> binding.hButton
                    R.id.i_button -> binding.iButton
                    R.id.j_button -> binding.jButton
                    R.id.k_button -> binding.kButton
                    R.id.l_button -> binding.lButton
                    R.id.m_button -> binding.mButton
                    R.id.n_button -> binding.nButton
                    R.id.o_button -> binding.oButton
                    R.id.p_button -> binding.pButton
                    R.id.q_button -> binding.qButton
                    R.id.r_button -> binding.rButton
                    R.id.s_button -> binding.sButton
                    R.id.t_button -> binding.tButton
                    R.id.u_button -> binding.uButton
                    R.id.v_button -> binding.vButton
                    R.id.w_button -> binding.wButton
                    R.id.x_button -> binding.xButton
                    R.id.y_button -> binding.yButton
                    R.id.z_button -> binding.zButton
                    // Add bindings for other buttons here
                    else -> null
                }
                buttonView?.visibility = View.VISIBLE
            }
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