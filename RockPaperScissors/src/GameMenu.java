import java.util.Scanner;

public class GameMenu {

    final static int GAME_RPS = 1;
    final static int EXIT = 0;
    /**
     * Opens the main menu for the game
     * Allow player to choose a game to play
     */
    public void startMenu(){
        Scanner scnr = new Scanner(System.in);
        int input;
        boolean runGame = true;
        Game startGame;
        while(runGame){
            try {
                System.out.println("Select a game: Enter a Number");
                System.out.println("0.Exit:");
                System.out.println("1.Rock, Paper, Scissors(Human vs. Computer):");

               input = scnr.nextInt();
               if(input==EXIT){
                runGame = false;
               }
               else if (input == GAME_RPS){
                startGame = new GameRPSHuVsCo(scnr);
                startGame.runGame();
               }
            
            } catch (Exception e) {
                System.out.println("Not valid input, try again");
            }

        }
        scnr.close();
        System.out.println("Thank you for playing");
    }
    
    public static void main(String[] args){
        GameMenu menu = new GameMenu();
        menu.startMenu();
    }
    
}

