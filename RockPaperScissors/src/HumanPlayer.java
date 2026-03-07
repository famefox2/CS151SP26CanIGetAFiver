public class HumanPlayer extends Player {
    
    private final HumanInput humanInput;

    public HumanPlayer(){
        super.setPlayerName("Human");
        humanInput = new HumanInput();
    }

    @Override
    public void getintRPS(){
        int input;
        input = humanInput.inputThrow();
        intToEnum(input);
    }
    @Override
    public Sign getCurSign(){
        getintRPS();
        return super.getCurSign();
    }
    @Override
    public Sign makeChoice(){
        getintRPS();
        return super.getCurSign();
    }





    
}
