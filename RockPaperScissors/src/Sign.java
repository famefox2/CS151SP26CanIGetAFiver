
//enum that represents the three possible choices: rock, paper, and scissors.
public enum Sign { 
    ROCK(0), 
    PAPER(1),
    SCISSORS(2);
    private final int value;

    private Sign(int value) {
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }

}
