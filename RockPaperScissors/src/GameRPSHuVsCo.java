public class GameRPSHuVsCo extends Game{

    private Player human;
    private Player computer;
    private final static int ROUNDS = 20;
    
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
    }
}
