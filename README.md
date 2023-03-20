# Mobile_CW1
![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange.svg) 

## 📸 Screenshots

<img src="https://github.com/pasinduwimalarathne2000/Mobile_CW1/blob/main/SS/Screenshot%202023-03-19%20223552.png" height="600" width="300" hspace="40"><img src="https://github.com/pasinduwimalarathne2000/Mobile_CW1/blob/main/SS/Screenshot%202023-03-19%20223656.png" height="600" width="300" hspace="40">

<img src="https://github.com/pasinduwimalarathne2000/Mobile_CW1/blob/main/SS/Screenshot%202023-03-19%20223754.png" height="600" width="300" hspace="40"><img src="https://github.com/pasinduwimalarathne2000/Mobile_CW1/blob/main/SS/Screenshot%202023-03-19%20223827.png" height="600" width="300" hspace="40">

## Demo
The code is an implementation of a dice game, where the player rolls five dice and tries to score points. The user can choose to keep some of the dice after the first roll and roll the remaining ones again, up to three times. After three rolls, the scores are updated, and the winner is announced. The code also includes an option for the user to set the target score to win.

The game consists of a single activity named MainActivity3 that extends the AppCompatActivity class. The class contains several private variables to store the dice images, dice arrays, image views, scores, rolls, and target score. The activity has a layout file named activity_main3.xml that defines the UI of the game.

The onCreate method initializes the UI elements of the game, sets the onClickListeners for the roll and score buttons, and shows a dialog to set the target score. The roll button is used to roll the dice, and the score button is used to update the scores and check if the game is over. The updateScore method updates the scores of the user and computer after each turn and resets the roll sums and number of rolls left. The showSetTargetScoreDialog method displays an alert dialog to allow the user to set a new target score. The updateComputerRolls method updates the computer's rolls and shows the winner if the game is over.

The game logic is implemented in the rollDiceMA1 method, which uses a custom DiceMA class to simulate rolling a dice. The method randomly generates new values for the dice that are not selected by the user and updates the dice image views.

##Ref
#android:configChanges="orientation|screenSize|screenLayout|keyboardHidden"
