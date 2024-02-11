package com.example.hangman

import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HangmanViewModel : ViewModel() {
    private val _guessedLetters = MutableLiveData<List<Char>>()
    val guessedLetters: LiveData<List<Char>> = _guessedLetters
    val _numGuess = MutableLiveData<Int>()
    val _numHint = MutableLiveData<Int>()

    val _correctLetters = MutableLiveData<List<Char>>()


    fun incrementGuess() {
        _numGuess.value = (_numGuess.value ?: 0) + 1
    }
    val _buttonsClicked = MutableLiveData<List<Button>>()

    fun incrementHint() {
        _numHint.value = (_numHint.value ?: 0) + 1
    }

    fun addGuessedLetter(letter: Char) {
        val currentList = _guessedLetters.value.orEmpty().toMutableList()
        currentList.add(letter)
        _guessedLetters.value = currentList
    }

    fun addClickedButton(button: Button) {
        val currentList = _buttonsClicked.value.orEmpty().toMutableList()
        currentList.add(button)
        _buttonsClicked.value = currentList
    }




}
