import java.util.Scanner;

public class GameMenu {

    final static int GAME1 = 1;
    final static int EXIT = 0;

    public void startMenu(){
        Scanner scnr = new Scanner(System.in);
        int input;
        boolean runGame = true;
        Game startGame = new Game();
        while(runGame){
            try {
                System.out.println("Select a game: Enter Number");
                System.out.println("0.Exit:");
                System.out.println("1.Rock, Paper, Scissors:");

               input = scnr.nextInt();
               if(input==EXIT){
                runGame = false;
               }
               else if (input == GAME1){
                startGame.runGame();
               }
            
            } catch (Exception e) {
                System.out.println("Not valid input, try again");
            }

        }
        System.out.println("Thank you for playing");
    }
    
}
