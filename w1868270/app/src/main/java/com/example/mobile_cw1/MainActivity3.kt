package com.example.mobile_cw1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity3 : AppCompatActivity() {
    // Private variables to store dice images, dice arrays, image views, scores, rolls, and target score
    private val diceImages = arrayOf(
        R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
        R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
    )
    private val diceMAArray = Array(5) { DiceMA(6) }
    private val diceComputerArray = Array(5) { DiceMA(6) }
    private lateinit var diceImageViews: Array<ImageView>
    private lateinit var compImg: Array<ImageView>
    private lateinit var checkBoxes: Array<CheckBox>
    private var userScore = 0
    private var computerScore = 0
    private var rollsLeft = 3
    private var userRollSum = 0
    private var compRollSum = 0
    private var targetScore: Int = 101

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        // Initialize image views for user and computer dices
        diceImageViews = arrayOf(
            findViewById(R.id.UserImgDice1),
            findViewById(R.id.UserImgDice2),
            findViewById(R.id.UserImgDice3),
            findViewById(R.id.UserImgDice4),
            findViewById(R.id.UserImgDice5)
        )
        compImg = arrayOf(
            findViewById(R.id.CompImgDice1),
            findViewById(R.id.CompImgDice2),
            findViewById(R.id.CompImgDice3),
            findViewById(R.id.CompImgDice4),
            findViewById(R.id.CompImgDice5)
        )
        // Initialize check boxes for user dices
        checkBoxes = arrayOf(
            findViewById(R.id.chck1),
            findViewById(R.id.chck2),
            findViewById(R.id.chck3),
            findViewById(R.id.chck4),
            findViewById(R.id.chck5)
        )
        // Show a dialog to set the target score
        showSetTargetScoreDialog()
        // Initialize the roll button and set an onClickListener to it
        val rollBtn: Button = findViewById(R.id.thBtn)
        rollBtn.setOnClickListener {
            // Check if there are rolls left
            if (rollsLeft > 0) {
                rollsLeft--
                rollDiceMA1()

                // If there are no rolls left, update scores and check if the game is over
                if (rollsLeft == 0) {
                    updateScore()
                }
                // If the target score is reached, show an alert dialog to the user
                if (userScore >= targetScore || computerScore >= targetScore) {
                    updateComputerRolls()
                }
            }
        }
        // Initialize the score button and set an onClickListener to it
        val score: Button = findViewById(R.id.scBtn)
        score.setOnClickListener {
            updateComputerRolls()
            val textscore1: TextView = findViewById(R.id.UsSc)
            textscore1.text = userScore.toString()
            val textscore2: TextView = findViewById(R.id.compSc)
            textscore2.text = computerScore.toString()

        }
    }
    // This function updates the scores of the user and computer after each turn and resets the roll sums and number of rolls left.
    private fun updateScore() {
        userScore += userRollSum
        val textscore1: TextView = findViewById(R.id.SumusSc)
        textscore1.text = userScore.toString()

        computerScore += compRollSum
        val textscore2: TextView = findViewById(R.id.SumcompSc)
        textscore2.text = computerScore.toString()

        userRollSum = 0
        compRollSum = 0
        rollsLeft = 3
        if (rollsLeft >= 2) {
            AlertDialog.Builder(this)
                .setTitle("Stop")
                .setMessage("You have clicked the button 3 times only")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }


    // This function displays an alert dialog to allow the user to set a new target score
    private fun showSetTargetScoreDialog() {
        // Create a new EditText view to allow the user to input a new target score
        val inputView = EditText(this)
        inputView.inputType = InputType.TYPE_CLASS_NUMBER
        inputView.setText(targetScore.toString())

        // Create a new AlertDialog to display the EditText view
        AlertDialog.Builder(this)
            .setTitle("Set Target Score")
            .setMessage("Enter the target score:")
            .setView(inputView)
            // Set the positive button to update the target score if the user enters a valid input
            .setPositiveButton("OK") { _, _ ->
                val newTargetScore = inputView.text.toString().toIntOrNull()
                if (newTargetScore != null && newTargetScore > 0) {
                    targetScore = newTargetScore
                } else {
                    // Invalid input, use default target score
                }
            }
            // Set the negative button to cancel the dialog
            .setNegativeButton("Cancel", null)
            .show()
    }



    private fun updateComputerRolls() {
        if (userScore >= targetScore || computerScore >= targetScore) {
            val winner = if (userScore > computerScore) {
                // user wins
                AlertDialog.Builder(this)
                    .setView(R.layout.winner)
                    .setTitle("Winner")
                    .setMessage("\n\nUser score: $userScore\nComputer score: $computerScore")
                    .setPositiveButton("Exit") { _, _ ->
                        // exit the game
                        finish()
                    }
                    .setNegativeButton("Restart") { _, _ ->
                        // reset scores and dice arrays
                        userScore = 0
                        computerScore = 0

                        for (i in 0 until diceMAArray.size) {
                            diceMAArray[i] = DiceMA(6)
                            diceComputerArray[i] = DiceMA(6)
                        }
                        // update score text views
                        val textscore1: TextView = findViewById(R.id.SumusSc)
                        textscore1.text = userScore.toString()
                        val textscore2: TextView = findViewById(R.id.SumcompSc)
                        textscore2.text = computerScore.toString()
                        // update dice image views
                        for (i in 0 until diceImageViews.size) {
                            diceImageViews[i].setImageResource(diceImages[0])
                            compImg[i].setImageResource(diceImages[0])
                        }
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            } else {
                // computer wins
                AlertDialog.Builder(this)
                    .setView(R.layout.winner2)
                    .setTitle("Game Over")
                    .setMessage("\n\nUser score: $userScore\nComputer score: $computerScore")
                    .setPositiveButton("Exit") { _, _ ->
                        // exit the game
                        finish()
                    }
                    .setNegativeButton("Restart") { _, _ ->
                        // reset scores and dice arrays
                        userScore = 0
                        computerScore = 0

                        for (i in 0 until diceMAArray.size) {
                            diceMAArray[i] = DiceMA(6)
                            diceComputerArray[i] = DiceMA(6)
                        }
                        // update score text views
                        val textscore1: TextView = findViewById(R.id.SumusSc)
                        textscore1.text = userScore.toString()
                        val textscore2: TextView = findViewById(R.id.SumcompSc)
                        textscore2.text = computerScore.toString()
                        // update dice image views
                        for (i in 0 until diceImageViews.size) {
                            diceImageViews[i].setImageResource(diceImages[0])
                            compImg[i].setImageResource(diceImages[0])
                        }
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        } else {
            if (userScore < targetScore && computerScore < targetScore) {
                AlertDialog.Builder(this)
                    .setTitle("Score")
                    //.setMessage("Your score is less than $targetScore. Try again!")
                    .setMessage("Your target score: $targetScore\n\nUser score: $userScore\nComputer score: $computerScore")

                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .setCancelable(false)
                    .show()
            } else {
                rollDiceMA1()
            }
        }
        userScore += userRollSum
        val textscore1: TextView = findViewById(R.id.SumusSc)
        textscore1.text = userScore.toString()

        computerScore += compRollSum
        val textscore2: TextView = findViewById(R.id.SumcompSc)
        textscore2.text = computerScore.toString()

        userRollSum = 0
        compRollSum = 0
    }

    private fun rollDiceMA1() {

        val diceMA = DiceMA(6) // Create an instance of DiceMA with 6 sides
        var total = 0// Initialize the variable to store the total of user's rolls


        // Loop through all the diceImageViews to roll the dice
        for (i in diceImageViews.indices) {
            // Check if the corresponding checkBox is not checked
            if (!checkBoxes[i].isChecked) {
                val userRoll = diceMA.rollMA() // Roll the die using the rollMA() function
                total += userRoll// Add the roll to the total
                // Set the corresponding diceImageView to display the image of the rolled number
                val drawableId = when (userRoll) {
                    1 -> R.drawable.dice1
                    2 -> R.drawable.dice2
                    3 -> R.drawable.dice3
                    4 -> R.drawable.dice4
                    5 -> R.drawable.dice5
                    else -> R.drawable.dice6
                }
                diceImageViews[i].setImageResource(drawableId)
            }
        }
        userScore += total// Add the total of user's rolls to the userScore variable


        var totalComp = 0// Initialize the variable to store the total of computer's rolls
        // Loop through all the dice in the diceComputerArray to roll the dice for the computer
        for (i in 0 until diceMAArray.size) {
            val computerRoll = diceComputerArray[i].rollMA()// Roll the die using the rollMA() function
            // Add the roll to the total
            totalComp += computerRoll
            // Set the corresponding imageView in the compImg array to display the image of the rolled number
            compImg[i].setImageResource(diceImages[computerRoll - 1])
        }
        // Add the total of computer's rolls to the computerScore variable
        computerScore += totalComp


    }


    class DiceMA(val numSidesMA: Int) {
        fun rollMA(): Int {
            return (1..numSidesMA).random()
        }
    }
}

