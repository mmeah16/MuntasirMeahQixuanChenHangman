package com.example.hangman

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HangmanViewModel : ViewModel() {
    private val _guessedLetters = MutableLiveData<List<Char>>()
    val guessedLetters: LiveData<List<Char>> = _guessedLetters
    val _numGuess = MutableLiveData<Int>()
    fun incrementGuess() {
        _numGuess.value = (_numGuess.value ?: 0) + 1
    }
    val buttonsClicked = mutableListOf<Button>()


    fun addGuessedLetter(letter: Char) {
        val currentList = _guessedLetters.value.orEmpty().toMutableList()
        currentList.add(letter)
        _guessedLetters.value = currentList
    }

    fun addClickedButton(button: Button) {
        val buttonsClicked = mutableListOf<Button>()
        buttonsClicked.add(button)
    }

}
