import java.io.StringReader; 
import java.util.Scanner; 

public class UnitTest {

    // Sign enum tests
    static void testSignValues(){
        assert Sign.ROCK.getValue()==0: "ROCK should have value 0";
        assert Sign.PAPER.getValue()==1: "PAPER should have value 1";
        assert Sign.SCISSORS.getValue()==2: "SCISSORS should have value 2";
        System.out.println("PASSED: testSignValues");
     }

    // Computer tests
    static void testComputerChoiceNotNull(){
        Computer comp = new Computer(42);
        Sign choice=comp.makeChoice();
        assert choice !=null: "Computer makeChoice should not return null";
        System.out.println("PASSED: testComputerChoiceNotNull");
    }

    static void testComputerChoiceInRange(){
        Computer comp = new Computer (7);
        for(int i = 0; i<50;i++){
            Sign choice = comp.makeChoice();
            int val = choice.getValue();
            assert val>=0 && val<=2: "Computer choice value should be 0, 1, or 2 but got " + val;
        }
        System.out.println("PASSED: testComputerChoiceInRange (50 rounds)");
    }

    // Player class tests
    static void testPlayerName(){
        Computer comp= new Computer();
        assert "Computer".equals(comp.getPlayerName()): "Computer player name should be 'Computer'";
        System.out.println("PASSED: testPlayerName");
    }

    static void testWinTracking(){
        Computer comp = new Computer();
        assert comp.getWins()==0: "Initial wins should be 0";
        comp.addWin();
        assert comp.getWins()==1: "Wins should be 1 after addWin()";
        comp.addWin();
        comp.addWin();
        assert comp.getWins()==3: "Wins should be 3 after three addWin()'s";
        System.out.println("PASSED: testPlayerWinTracking");
    }

    //tests whether human input class works properly
    static void testHumanInput() {
        StringReader fakeInput = new StringReader("1"); 
        Scanner scnr = new Scanner(fakeInput); 
        HumanInput human = new HumanInput(scnr); 
         int result = human.inputThrow(true); 

        assert result == 1 : "Human input should have been 1 if they player entered in 1"; 

        System.out.println("PASSED: testHumanInput"); 
         
    }

    public static void main(String[] args) {
        System.out.println("Running tests: ...");

        testSignValues();
        testComputerChoiceNotNull();
        testComputerChoiceInRange();
        testPlayerName();
        testWinTracking();
        testHumanInput();
        //Test the whole code
        GameMenu test = new GameMenu();
        test.startMenu();

    }
}




