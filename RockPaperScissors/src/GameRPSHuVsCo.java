/**
 * Rock, paper, scissors for a human vs a computer
 */
public class GameRPSHuVsCo extends Game{

    private Player human;
    private Player computer;
    private final static int ROUNDS = 20;
    
    /**
     * Make the RPS game for 20 rounds. Creates the human and computer players, the game rules for the game
     */
    @Override
    public void runGame(){
        int count = 1;
        human = new HumanPlayer();
        computer = new Computer();
        GameRule rules = new GameRule(human, computer);
        
        while(count<=ROUNDS){
            System.out.println("Round " + count);
            
            rules.getRPS();
            
            count+=1;
                } 
        
        System.out.println("\n---GAME OVER---");
        rules.printResult();
        if(rules.getHumanWins()>rules.getComputerWins()){
            System.out.println( human.getPlayerName() + " wins the game!");
        }else if(rules.getComputerWins()>rules.getHumanWins()){
            System.out.println("Computer wins the game!");
        }else{
            System.out.println("It's a draw!");
        }
        System.out.println("---------------\n");
    }
}
