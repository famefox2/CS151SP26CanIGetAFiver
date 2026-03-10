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

    public static void main(String[] args) {


        //Test the whole code
        GameMenu test = new GameMenu();
        test.startMenu();

    }
}

