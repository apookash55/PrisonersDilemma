# Game Rules
1. In each round both players are given a choice to either `COOPERATE` or `DEFECT`<br>
2. Based on this rule:<br>
If Player 1 = `COOPERATE` and Player 2 = `COOPERATE`, both players get 2 pts<br>
If Player 1 = `COOPERATE` and Player 2 = `DEFECT`, player 1 gets 0 pts and player 2 gets 3 pts<br>
If Player 1 = `DEFECT` and Player 2 = `COOPERATE`, player 1 gets 3 pts and player 2 gets 0 pts<br>
If Player 1 = `DEFECT` and Player 2 = `DEFECT`, both players get 1 pt<br><br>
![image](https://github.com/apookash55/PrisonersDilemma/assets/44578360/f6f2f62b-da44-4350-8153-38ef34f74e4f)
<br>
3. This will go on for finite number of rounds and your goal is to get max points possible till the end<br>

# Task
1. Your task is to implement your logic of decision making as in when to `COOPERATE` or `DEFECT`<br>
2. A template player has been added in `dev/apookash55/gametheory/players` where you can rename it and add your logic<br>
3. Other players are added for testing which your implemented player will compete against<br>
4. Your player class will extend the abstract `Player` class which has all the functionalities except the `makeDecision()` which you will implement<br>

# Player Definition
1. The Player has two attributes<br>
a. `roundsHistory` : Has all the last rounds' descisions made by your player and the other player<br>
b. `currentRound`: Integer stating what is the current round number starting from 0<br>
3. `Decision` : Enum class having two values i.e. `Decision.COOPERATE` and `Decision.DEFECT`
2. `getRoundsHistory()` : Returns the full rounds' data as List of Array of `Decision` with first and second index as your player's choice and other player's choice respectively
3. `getLastRoundHistory()` : Returns the last round data as an Array of `Decision` with first and second index as your player's choice and other player's choice respectively
4. `getCurrentRound()` : Returns the currentRound starting from 0
5. `makeDecision()` : Abstract method that you will implement which returns `Decision.COOPERATE` or `Decision.DEFECT` (If `null` it will consider it as `Decision.DEFECT`)

# Results
1. When the application runs it will compete all the players against each other and generate results
2. Sample results with the existing players with 50 rounds has been added for reference in `src/main/resources/sampleresults`
3. result.csv - Has the final scores of each player
4. games.csv - Has the score of each game for each player 1 vs player 2
5. games/gameno_player_1_VS_player_2.csv - Has detailed info of the game which contains all the rounds' choices

# How to Add Player
1. Create a branch from `players`, add your player class by using the template or creating your own and create a pull request to the branch `players`
2. Make sure the game has been tested before creating pull request by checking the results
3. Run the `MainGame` class that will compete all the players and generate results
