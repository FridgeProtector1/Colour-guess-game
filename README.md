# Guess Colour Game

A Java-based two-player game where a human competes against an AI opponent (HAL-9000) in a colour-based guessing match that mixes chance, strategy, and adaptive behavior.

## Game Rules
- Both players choose a colour and attempt to guess the opponent’s choice each round.
- Points are awarded for correct guesses, with bonus points every third round if the guess matches a hidden Power Colour.
- The player with the highest score at the end of all rounds wins.

## AI Difficulty Levels
- **Easy:** Random choices and guesses.
- **Medium:** Starts random, then switches to basic strategy.
- **Hard:** Adapts based on win/loss history.

## Design Patterns Used
- **Factory Pattern:** Creates AI instances based on difficulty level using a static factory method.
- **Strategy Pattern:** AI logic is modular and switchable using a `setStrategy` method.

## Technologies Used
- Java
- Object-oriented design
- Factory and Strategy design patterns

## Purpose
Developed as a university project to demonstrate AI behavior modeling, game logic, and design pattern implementation.
