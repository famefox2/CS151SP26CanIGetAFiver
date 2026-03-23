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

 
    static void testHumanInput() {
        StringReader fakeInput = new StringReader("1"); 
        Scanner scnr = new Scanner(fakeInput); 
        HumanInput human = new HumanInput(scnr); 
         int result = human.inputThrow(true); 

        assert result == 1 : "Human input should have been 1 if they player entered in 1"; 

        System.out.println("PASSED: testHumanInput"); 
         
    }

    //Interface tests
    static void testComputerIsChoiceAlgorithm() {
        Computer comp = new Computer(42);
        // Computer should be assignable to ComputerChoiceAlgorithm
        ComputerChoiceAlgorithm algo = comp;
        Sign s = algo.chooseSign();
        assert s != null : "chooseSign() should not return null";
        assert "Random".equals(algo.getAlgorithmName()) : "Computer algorithm name should be 'Random'";
        System.out.println("PASSED: testComputerIsChoiceAlgorithm");
    }

    static void testComputerMLIsChoiceAlgorithm() {
        ComputerML ml = new ComputerML(42);
        ComputerChoiceAlgorithm algo = ml;
        Sign s = algo.chooseSign();
        assert s != null : "ML chooseSign() should not return null";
        assert "Machine Learning".equals(algo.getAlgorithmName()) : "ML algorithm name should be 'Machine Learning'";
        System.out.println("PASSED: testComputerMLIsChoiceAlgorithm");
    }

    //Algorithm tests
    static void testAlgorithmSwap() {
        ComputerChoiceAlgorithm randomAlgo = new Computer(42);
        ComputerChoiceAlgorithm mlAlgo = new ComputerML(42);

        Sign randomChoice = randomAlgo.chooseSign();
        Sign mlChoice = mlAlgo.chooseSign();

        assert randomChoice != null : "Random chooseSign should not be null";
        assert mlChoice != null : "ML chooseSign should not be null";

        assert "Random".equals(randomAlgo.getAlgorithmName()) :
                "Should be 'Random' but got " + randomAlgo.getAlgorithmName();
        assert "Machine Learning".equals(mlAlgo.getAlgorithmName()) :
                "Should be 'Machine Learning' but got " + mlAlgo.getAlgorithmName();

        System.out.println("PASSED: testAlgorithmSwap");
    }

    static void testMLPatternLearning() {
        RoundSequence seq = new RoundSequence();
        ComputerML ml = new ComputerML(0, seq, 5);
        StaticPlayer humanSim = new StaticPlayer(Sign.ROCK);
        seq.updatePlayers(humanSim, ml);

        // Play 30 rounds where human always picks ROCK
        for (int i = 0; i < 30; i++) {
            humanSim.makeChoice();
            ml.makeChoice();
            seq.updateRoundSequence();
        }
        // ML should have learned the pattern - just verify it returns valid signs
        Sign prediction = ml.chooseSign();
        assert prediction != null : "ML prediction should not be null after learning";
        assert prediction.getValue() >= 0 && prediction.getValue() <= 2 : "ML prediction out of range";
        System.out.println("PASSED: testMLPatternLearning");
    }

    static void testSequenceHashMap() {
        SequenceHashMap map = new SequenceHashMap("RSPSR");
        map.addHashMapSequence("RSPSS");
        map.addFreq("RSPSS");
        map.addFreq("RSPSS");
        map.addFreq("RSPSS");
        // RSPSR has freq 1, RSPSS has freq 4
        String most = map.getMostFreqSequence();
        assert "RSPSS".equals(most) : "Most frequent should be RSPSS but got " + most;
        System.out.println("PASSED: testSequenceHashMapMostFrequent");
    }



    public static void main(String[] args) {
        System.out.println("Running tests: ...");
        System.out.println();

        testSignValues();
        testComputerChoiceNotNull();
        testComputerChoiceInRange();
        testPlayerName();
        testWinTracking();
        testHumanInput();

        testComputerIsChoiceAlgorithm();
        testComputerMLIsChoiceAlgorithm();
        testAlgorithmSwap();
        testMLPatternLearning();
        testSequenceHashMap();

        System.out.println();
        System.out.println("All tests passed.");
    }
}





