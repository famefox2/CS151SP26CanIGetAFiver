import java.util.Scanner;
/**
 * Rock, paper, scissors for a human vs a computer
 */
public class GameRPSHuVsCo extends Game{

    private Player human;
    private Player computer;
    private final static int ROUNDS = 20;
    private Scanner scnr;
    private boolean useML;

    public GameRPSHuVsCo(Scanner scnr, boolean useML){
        this.scnr=scnr;
        this.useML=useML;
    }
    
    /**
     * Make the RPS game for 20 rounds. Creates the human and computer players, the game rules for the game
     */
    @Override
    public void runGame(){
        int count = 1;
        human = new HumanPlayer(scnr);
        RoundSequence roundSeq=null;
        
        if(useML){
            roundSeq =new RoundSequence();
            ComputerML mlComp=new ComputerML();
            mlComp.setRoundSequence(roundSeq);
            computer=mlComp;
            roundSeq.updatePlayers(human, computer);
            System.out.println("Playing against Machine Learning Computer");
        } else {
            computer = new Computer();
            System.out.println("Playing against Random Computer");
        }
        System.out.println("----------------------------");

        GameRule rules = new GameRule(human, computer);
        
        while(count<=ROUNDS){
            System.out.println("Round " + count);
            
            rules.getRPS();

            if(useML && roundSeq!=null){
                roundSeq.updateRoundSequence();
            }
            
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

        // Saves ML data at end of game
        if(useML && computer instanceof ComputerML){
            ComputerML mlComp =(ComputerML)computer;
            mlComp.saveData();
            System.out.println("ML data saved for next game.");
        }
    }
}
