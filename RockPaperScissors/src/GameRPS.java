public class GameRPS extends Game{

    private Player player1;
    private Player player2;
    
    

    @Override
    public void runGame(){
        int count =1;
        GameRule rules = new GameRule();
        Computer com =  new Computer();
        Input userInput = new Input();
        GameResult score = new GameResult();
        int player1;
        int player2;
        
        while(count<=20){
            System.out.println("Round "+count);
            player1= userInput.inputThhrow();
            com.calculateResults();
            player2 = com.getResult();
            rules.getRPS(player1, player2);
            count+=1;
                }                
    }
}
