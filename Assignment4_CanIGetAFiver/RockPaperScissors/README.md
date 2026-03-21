# Rock Paper Scissors — Human vs. Computer

## Description

A command-line Rock-Paper-Scissors game written in Java 

---

## How to Run

### Prerequisites
- **Java Development Kit (JDK)** installed (version 8 or later)
- Terminal or command prompt access

### Compilation

Navigate to the `src` directory and compile all Java files:

```bash
cd RockPaperScissors/src
javac *.java
```

### Running the Game

From the `src` directory, run the main menu:

```bash
java GameMenu
```

### Gameplay Instructions

1. **Main Menu** — You will see:
   ```
   Select a game: Enter a Number
   0.Exit:
   1.Rock, Paper, Scissors(Human vs. Computer):
   ```

2. **Start the Game** — Enter `1` to begin Rock-Paper-Scissors

3. **Each Round** — You'll be prompted:
   ```
   Enter A Number: 0.Rock, 1.Paper, or 2.Scissors
   ```
   - Enter `0` for **Rock**
   - Enter `1` for **Paper**
   - Enter `2` for **Scissors**

4. **Results** — After each round, you'll see the winner of that round and the running score

5. **Game Over** — After 20 rounds, the final score is displayed and an overall winner is announced

6. **Exit** — Return to the main menu and enter `0` to exit the program

### Example Session

```
Select a game: Enter a Number
0.Exit:
1.Rock, Paper, Scissors(Human vs. Computer):
1
Round 1
Enter A Number: 0.Rock, 1.Paper, or 2.Scissors
0
[Round result displayed]

Round 2
Enter A Number: 0.Rock, 1.Paper, or 2.Scissors
1
[Round result displayed]

... [18 more rounds] ...

===== GAME OVER =====
[Final score tally]
[Overall winner announcement]
```

---

## Tips

- **Invalid input?** The game will ask you to try again. Enter 0, 1, or 2 only.
- **All 20 rounds required** — The game must complete all 20 rounds before declaring a winner
- **Ties are possible** — If you and the computer tie at 20 rounds (same wins), it's a draw

---

**Enjoy the game! :)**
