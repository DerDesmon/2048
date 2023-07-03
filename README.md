## 2048 Game documentation

## Table of contents
1. Introduction
2. Aim of the game
3. How the game works
4. Game mechanics
5. GUI

## 1. Introduction
This game was created in 2022 for a school project at Otto-von-Taube-Gymnasium. Our task was to program a game ourselves after having achieved basic and advanced Java knowledge. The whole project is written in Java, and Eclipse was used as a developer environment. I, Simon Gunka created this game in cooperation with my classmate Julius Kühlthau.

## 2. Aim of the game
Throughout the game you have to combine tiles that merge if they have the same value. This continues until you reach the value of 2048 (it starts with twos that after a successful merge become a four tile).

## 3. How the game works
1. Two two-tiles will spawn on random empty tiles of the 4x4 grid.
2. By using one of the four arrow keys on your keyboard, all tiles will be pushed into the appropriate side. If a tile is pushed into another tile that has the same value, they will merge together and their values will be added as the new value. Two tiles of different values will not merge together.
3. After each move, a two-tile, or sometimes a four-tile, will spawn at a random location on the board to keep the process ongoing.
4. Step 2. and 3. will be repeated until there are no more empty tiles and no move is possible. If both conditions are true, the game is over.

## 4. Game mechanics
After an arrow key has been pressed, the tile on the edge of the side where the tiles are being pushed will be the first object to start the merge mechanic. This tile will ask its neighbor in the opposite direction of the key pressed for its value. If the value is
- of the same value, it will merge into itself by doubling its value and setting the value of the tile next to it to zero.
- zero, it will give the tile next to it the task of receiving its neighbor's value.
- of a different value, nothing will move.
This process will be done with all tiles of a row or column for every row and column.

In order for the game to know when the game is over a variable is used. At the beginning of a move (if the grid contains no empty files) that is initiated by pressing one of the keys, the variable is set to false. If a tile is moved or merged, the variable will be set to true. This will be done for the other directions without impacting the game board. If the variable is still set to false after trying out all directions, the game is over.

## 5. GUI
Since we did not learn how to design GUIs in our class, I used some GUI templates.
