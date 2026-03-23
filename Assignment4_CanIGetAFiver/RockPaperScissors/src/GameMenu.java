import java.util.Scanner;

public class GameMenu {

    final static int GAME_RPS_RAND = 1;
    final static int EXIT = 0;
    final static int GAME_RPS_ML=2;
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
                System.out.println("0. Exit:");
                System.out.println("1. Rock, Paper, Scissors(Human vs. Random Computer):");
                System.out.println("2. Rock Paper Scissors (Human vs. Machine Learning Computer)");

               input = scnr.nextInt();
               if(input==EXIT){
                runGame = false;
               }
               else if (input == GAME_RPS_RAND){
                startGame = new GameRPSHuVsCo(scnr, false);
                startGame.runGame();
               }else if (input==GAME_RPS_ML){
                startGame=new GameRPSHuVsCo(scnr,true);
                startGame.runGame();
               }
            
            } catch (Exception e) {
                System.out.println("Not valid input, try again");
                scnr.nextLine();
            }

        }
        scnr.close();
        System.out.println("Thank you for playing");
    }
    
    /**
     * Start the game directly with a specific mode from command-line args.
     * @param scnr the scanner for input
     * @param useML true for ML, false for random
     */
    public void startWithMode(Scanner scnr, boolean useML) {
        boolean runGame =true;
        while (runGame) {
            Game startGame = new GameRPSHuVsCo(scnr, useML);
            startGame.runGame();
            System.out.println("Play again? (1 = Yes, 0 = No)");
            try {
                int input=scnr.nextInt();
                if (input == 0) {
                    runGame= false;
                }
            } catch (Exception e) {
                runGame =false;
            }
        }
        System.out.println("Thank you for playing");
    }
    
    public static void main(String[] args){
        GameMenu menu =new GameMenu();

        if (args.length>0){
            Scanner scnr=new Scanner(System.in);
            if (args[0].equals("-m")) {
                System.out.println("Starting with Machine Learning computer...");
                menu.startWithMode(scnr, true);
            } else if (args[0].equals("-r")) {
                System.out.println("Starting with Random computer...");
                menu.startWithMode(scnr,false);
            } else {
                System.out.println("Unknown option: " + args[0]);
                System.out.println("Use -r for random or -m for machine learning");
                menu.startMenu();
            }
            scnr.close();
        } else{
            menu.startMenu();
        }
    }
    
}

