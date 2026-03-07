public enum Sign { 
    ROCK(0), 
    PAPER(1),
    SCISSORS(2);
    private final int value;

    private Sign(int value) {
        this.value = value;
        //TODO Auto-generated constructor stub
    }
    public int getValue(){
        return value;
    }

}
