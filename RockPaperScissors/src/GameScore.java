public class GameScore {
    
    int player1Score;
    int player2Score;
    int drawScore;

    public GameScore() {
        player1Score =0;
        player2Score =0;
        drawScore=0;
    }
    
    public void addPlayer1Score(){
        player1Score+=1;
    }
    public void addPlayer2Score(){
        player2Score+=1;
    }
    public void addDrawSCore(){
        drawScore+=1;
    }
    public void printScore(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }


}
