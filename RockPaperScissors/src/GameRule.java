/**
 * Find winner and keep and print score
 * would like this class to just find the winner/ rules of rock paper scissors 
 * future implementation another class would keep score.
 */
public class GameRule {
    final static int ROCK = 0;
    final static int PAPER =1;
    final static int SCISSORS =2;
    final static int DRAW =4;
    
        int humanWins =0;
        int computerWins=0;
        int draws=0;

    //rock == 0 paper = 1 scissor=2
    public void getRPS(int player1, int player2){
        if(player1 == ROCK && player2== PAPER){
            System.out.println("You chose Rock. The computer chose Paper. Computer wins!");//Subject to change for name
            computerWins+=1;

        }
        else if(player1== ROCK && player2 == SCISSORS){
            System.out.println("You chose Rock. The computer chose Scissors. Human wins!");
            humanWins+=1;
        }
        else if (player1 == PAPER && player2 == SCISSORS) {
            System.out.println("You chose Paper. The computer chose Scissors. Computer wins!");
            computerWins+=1;
        }
        else if (player1 == PAPER && player2 == ROCK) {
            System.out.println("You chose Paper. The computer chose Rock. Human wins!");
            humanWins+=1;
        }
        else if(player1== SCISSORS && player2 == ROCK){
            System.out.println("You chose Paper. The computer chose Rock. Computer wins!");
            computerWins+=1;
        }
        else if(player1== SCISSORS && player2== PAPER){
            System.out.println("You chose Scissors. The computer chose Paper. Human wins!");
            humanWins+=1;
        }
        else{
            switch (player1) {
                case ROCK -> {
                    System.out.println("You chose Rock. The computer chose Rock. Draw!");
                    draws+=1;
                }
                case SCISSORS -> {
                    System.out.println("You chose Scissors. The computer chose Scissors. Draw!");
                    draws+=1;
                }
                default -> {
                    System.out.println("You chose Paper. The computer chose Paper. Draw!");
                    draws+=1;
                }
            }
        }
        
        printResult();

    }

    public int getintRPS(int player1, int player2){
        if(player1==player2){
            System.out.println("Draw!");
            return DRAW;
        }
        else if(player1== 0 && player2== 1){
            System.out.println("paper wins, player2");
             return PAPER;
        }
        else if(player1== 0 && player2 ==2){
            System.out.println("Rocker wins player1 win");
            return ROCK;
            
        }
        else if (player2 == 1 && player1 == 2) {
            System.out.println("Scissors wins");
            return SCISSORS;
        }
        else if(player2== 0 && player1== 1){
            System.out.println("Paper wins, player2");
            return PAPER;
        }
        else if(player2== 0 && player1 ==2){
            System.out.println("Rocker wins player1 win");
            return ROCK;
        }
        else if (player2 == 1 && player1 == 2) {
            System.out.println("Scissors wins");
            return SCISSORS;
        }
        return 0;
    }
    public void printResult(){
        System.out.println("Score: Human:" + humanWins + " Computer:"+ computerWins+ " Draws:"+ draws);
    }

}
