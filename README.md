# Game Rules
1. In each round both players are given a choice to choose between to cooperate/defect<br>
2. Based on this rule:<br>
![image](https://github.com/apookash55/PrisonersDilemma/assets/44578360/f6f2f62b-da44-4350-8153-38ef34f74e4f)
<br>Each player is awarded points<br>
3. This will go on for finite number of rounds and your goal is to get max points possible at the end<br>
# Task
1. Your task is to implement your logic of decision making as in when to cooperate/defect<br>
2. A template player has been added in `dev/apookash55/gametheory/players` where you can rename it and add your logic<br>
3. Other players are added for testing which your implemented player will compete against<br>
# Results
1. When the application runs it will compete all the players against each other and generate results
2. Sample results with the existing players with 50 rounds has been added for reference
3. result.csv - Has the final scores of each player
4. games.csv - Has the score of each game for each player 1 vs player 2
5. games/gameno_player_1_VS_player_2.csv - Has detailed info of the game which contains all the rounds' choices
# How to Add Player
1. Simply create a branch from `master`, add your player class by using the template or creating your own and create a pull request
2. Your player class will extend the abstract `Player` class which has all the functionalities except the `makeDecision()` which you will implement
3. Make sure the game has been tested befor creating a pr by checking the results
