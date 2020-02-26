package com.android.example.exer3_sibor_b4l

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var mCount: Int = 0
    private lateinit var mViewCount: TextView
    private lateinit var submitBtn: Button
    private lateinit var retryButton: Button


    private var board = arrayOf( // checker if the light is on or off (initially off)
        arrayOf(
            0, 0, 0, 0, 0),
        arrayOf(
            0, 0, 0, 0, 0),
        arrayOf(
            0, 0, 0, 0, 0),
        arrayOf(
            0, 0, 0, 0, 0),
        arrayOf(
            0, 0, 0, 0, 0))

    private var boardVariable = arrayOf( // array of textviews
        arrayOf(
            R.id.box_one_text, R.id.box_two_text, R.id.box_three_text,R.id.box_four_text,R.id.box_five_text),
        arrayOf(
            R.id.box_six_text, R.id.box_seven_text, R.id.box_eight_text,R.id.box_nine_text,R.id.box_ten_text),
        arrayOf(
            R.id.box_eleven_text, R.id.box_twelve_text, R.id.box_thirteen_text,R.id.box_fourteen_text,R.id.box_fifteen_text),
        arrayOf(
            R.id.box_sixteen_text, R.id.box_seventeen_text, R.id.box_eighteen_text,R.id.box_nineteen_text,R.id.box_twenty_text),
        arrayOf(
            R.id.box_twentyone_text, R.id.box_twentytwo_text, R.id.box_twentythree_text,R.id.box_twentyfour_text,R.id.box_twentyfive_text))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameTextView = findViewById<TextView>(R.id.name_text)
        submitBtn = findViewById(R.id.submit_button)

        submitBtn.setOnClickListener { // shows name on top and the viewboard
            updateNickname(it)
            viewBoard()
        }

        nameTextView.setOnClickListener{// when the name on top is clicked, the user will be able to edit it
            changeNickname()
        }

        setListeners() // flipping lights happen
        mViewCount = findViewById(R.id.count)
        mViewCount.visibility = View.GONE // wala pa sya sa start

        retryButton = findViewById(R.id.retry_button)
        retryButton.setOnClickListener {// reset all
            retry()
        }

        retryButton.visibility = View.GONE // wala pa sa start
    }

    private fun updateNickname(view: View) { // adds nickname
        val editText = findViewById<EditText>(R.id.name_edit)
        val nameTextView = findViewById<TextView>(R.id.name_text)

        nameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nameTextView.visibility = View.VISIBLE // pagkaadd malalagay sa top yung name
        mViewCount.visibility = View.VISIBLE // displays counter
        retryButton.visibility = View.VISIBLE // displays retry button


    }

    private fun changeNickname () { // function that changes nickname
        val editText = findViewById<EditText>(R.id.name_edit)
        val nameTextView = findViewById<TextView>(R.id.name_text)

        nameTextView.visibility = View.GONE
        editText.visibility = View.VISIBLE
        submitBtn.visibility = View.VISIBLE

    }

    private fun viewBoard() { // shows 5x5 board
        val boxOneText = findViewById<TextView>(R.id.box_one_text)
        val boxTwoText = findViewById<TextView>(R.id.box_two_text)
        val boxThreeText = findViewById<TextView>(R.id.box_three_text)
        val boxFourText = findViewById<TextView>(R.id.box_four_text)
        val boxFiveText = findViewById<TextView>(R.id.box_five_text)
        val boxSixText = findViewById<TextView>(R.id.box_six_text)
        val boxSevenText = findViewById<TextView>(R.id.box_seven_text)
        val boxEightText = findViewById<TextView>(R.id.box_eight_text)
        val boxNineText = findViewById<TextView>(R.id.box_nine_text)
        val boxTenText = findViewById<TextView>(R.id.box_ten_text)
        val boxElevenText = findViewById<TextView>(R.id.box_eleven_text)
        val boxTwelveText = findViewById<TextView>(R.id.box_twelve_text)
        val boxThirteenText = findViewById<TextView>(R.id.box_thirteen_text)
        val boxFourteenText = findViewById<TextView>(R.id.box_fourteen_text)
        val boxFifteenText = findViewById<TextView>(R.id.box_fifteen_text)
        val boxSixteenText = findViewById<TextView>(R.id.box_sixteen_text)
        val boxSeventeenText = findViewById<TextView>(R.id.box_seventeen_text)
        val boxEighteenText = findViewById<TextView>(R.id.box_eighteen_text)
        val boxNineteenText = findViewById<TextView>(R.id.box_nineteen_text)
        val boxTwentyText = findViewById<TextView>(R.id.box_twenty_text)
        val boxTwentyOneText = findViewById<TextView>(R.id.box_twentyone_text)
        val boxTwentyTwoText = findViewById<TextView>(R.id.box_twentytwo_text)
        val boxTwentyThreeText = findViewById<TextView>(R.id.box_twentythree_text)
        val boxTwentyFourText = findViewById<TextView>(R.id.box_twentyfour_text)
        val boxTwentyFiveText = findViewById<TextView>(R.id.box_twentyfive_text)

        val arr = arrayOf(boxOneText,boxTwoText,boxThreeText,boxFourText,boxFiveText,
            boxSixText,boxSevenText,boxEightText,boxNineText,boxTenText,
            boxElevenText,boxTwelveText,boxThirteenText,boxFourteenText,boxFifteenText,
            boxSixteenText,boxSeventeenText,boxEighteenText,boxNineteenText,boxTwentyText,
            boxTwentyOneText,boxTwentyTwoText,boxTwentyThreeText,boxTwentyFourText,boxTwentyFiveText)

        for (i in arr){
            i.visibility = View.VISIBLE
        }
    }


    private fun setListeners(){
        for (i: Int in (0..4)){
            for (j: Int in (0..4)) {
                findViewById<TextView>(boardVariable[i][j]).setOnClickListener{closeLight(it, i, j)}
            }
        }
    }

    private fun closeLight(view:View, row:Int, col:Int) {
        countUp(view)
        val thisBox: TextView = findViewById(boardVariable[row][col])

        if (board[row][col] == 0){ // if off
            thisBox.setBackgroundColor(Color.BLACK)
            board[row][col] = 1
        }else{ // if on
            thisBox.setBackgroundColor(Color.YELLOW)
            board[row][col] = 0
        }

        if(row != 0){ // kapag hindi sa pinakataas na row
            val newBox: TextView = findViewById(boardVariable[row-1][col])

            if (board[row-1][col] == 0){
                newBox.setBackgroundColor(Color.BLACK)
                board[row-1][col] = 1
            }else{
                newBox.setBackgroundColor(Color.YELLOW)
                board[row-1][col] = 0
            }
        }

        if(col != 0){ // kapag hindi sa pinakaleft na column
            val newBox: TextView = findViewById(boardVariable[row][col-1])

            if (board[row][col-1] == 0){
                newBox.setBackgroundColor(Color.BLACK)
                board[row][col-1] = 1
            }else{
                newBox.setBackgroundColor(Color.YELLOW)
                board[row][col-1] = 0
            }
        }

        if(row < 4){ // kapag hindi sa pinakababang row
            val newBox: TextView = findViewById(boardVariable[row+1][col])

            if (board[row+1][col] == 0){
                newBox.setBackgroundColor(Color.BLACK)
                board[row+1][col] = 1
            }else{
                newBox.setBackgroundColor(Color.YELLOW)
                board[row+1][col] = 0
            }
        }

        if(col < 4) { // kapag hindi sa pinakaright na column
            val newBox: TextView = findViewById(boardVariable[row][col+1])

            if (board[row][col+1] == 0){
                newBox.setBackgroundColor(Color.BLACK)
                board[row][col+1] = 1
            }else{
                newBox.setBackgroundColor(Color.YELLOW)
                board[row][col+1] = 0
            }
        }


    }

    private fun countUp(view: View) { // function na nagccount per click
        mCount++
        if(mViewCount !== null)
            mViewCount.text = mCount.toString()
    }

    private fun retry(){ // function na nagrereset ng board and tap count
        for (i: Int in (0..4)){
            for (j: Int in (0..4)) {
                // Lahat ng black gagawing yellow
                // If array na board ay 1 gagawing yellow yung color tas 0 yung value
                if(board[i][j] == 1){
                    val newBox: TextView = findViewById(boardVariable[i][j])
                    newBox.setBackgroundColor(Color.YELLOW)
                    board[i][j] = 0
                }

            }
        }
        // Yung tap ay reset sa 0
        mCount = 0
        if(mViewCount !== null)
            mViewCount.text = mCount.toString()

    }
}
