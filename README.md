# CPSC 210 Project: Demon Defender Game
#### Sean Tang (Snake z7q8w)

# CONTROLS: 
- WASD to move
- Space to shoot
- P to save
- N to restart game

### *Project Rundown*

So what exactly is this project? This is a **top down shooter game**, with the theme of a *Devilish creatures*
where traditionally, one would **survive for as long as possible**. In this game, you start off in a room/rooms where
you must attempt to **survive constant waves of devilish creatures**, until you die. You're able to defend yourself with **guns**, 
and you're able to **upgrade** them, **purchase new weapons**, and even **get abilities**. The longer you last, the 
higher the score. *Are you able to defend yourself against waves of demons and devlish creatures?*?

### *Who Would Use This?*

Great question. Who would play this game? Here's a list of possible users:
		
- My fellow game dev friends
- Fellow university students (not during class of course)
- Anyone looking for a quick, fun game
- survival game fans

### *Personal Interest*

The reason for picking this project is because of my personal interest in making games. I've always loved
playing video games, and so I've always wanted to try my hand at them. I wanted to create a simple and short game
that someone can enjoy at whichever time you want; I often find myself with 5 minutes before a class, not knowing 
what to do. Maybe this game can help fill those times, and would also be something cool to show people in the 
future!



## User Stories

**As a User, I want to be able to:**
- start game and restart game over and over
- walk around the map
- view game information such as health, rounds, money, ammo
- shoot weapon (pew pew, multiple bullets added to game)
- view spawned enemies (multiple enemies added to game)
- eliminate enemies and get high score (in rounds)
- lose when health reaches 0

- Save game state: round number, enemies, ammo, health, bullet list, zombie list 
- Reload game state upon opening the game
- Choose to start new game upon opening the game


## Phase 4: Task 2

Test and design a class in your model package that is robust.  You must have at least one method that throws a checked exception.
You must have one test for the case where the exception is expected and another where the exception is not expected.

In the GameData class, in the update() method, there is a GameOverException thrown; this is designed to throw an exception whenever
the game is over (when the player loses all it's health)
In the GameRun class, in the gameRunLoop() method, it try catches the update() method of GameData; in the case of game over, it
will not run through all the GameData, Game GUI, and Score GUI update methods. Instead, a message prompting you to restart the
game will print in the console.

For testing, in the GameDataTest class, there are two test methods: testUpdate() and testUpdateGameOver(). 
testUpdate() runs update without expecting GameOver exception.
testUpdateGameOver runs update() when game is over, throwing a GameOver exception.


## Phase 4: Task 3

The UML diagram of my overall project design is fairly straightforward. In my opinion, I've kept most data fairly organized
in an easily readible/understandable manner. If I had more time and a chance to refactor my work, there are some changes I 
would definitely make. The input system, while mostly remaining in GUI, gets passed along many classes to determine a 
correct input. Perhaps refactoring to it's own class would make it more neat. Another possible change is the GameData class.
The class itself handles many responsibilities, such as making sounds, updating game data, saving game, hit detections, etc.
A nice serperation for a unique class for each responsibility can add cohesion, making it easier to understand and debug
in the future.