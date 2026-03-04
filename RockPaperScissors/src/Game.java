public class Game {

    public Game(){
    }
    
    public void runGame(){
        int count = 1;
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
    public static void main(String[] args) {
        Game testing = new Game();
        testing.runGame();
    }

        


    

}
