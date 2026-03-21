
import java.util.HashMap;
import java.util.Set;

public class ComputerML extends Computer{

    private RoundSequence temp = new RoundSequence(null, null);//Access to round results

    private HashMap<String, SequenceHashMap> storedSequence;// Round Sequence stored 
    

    private final int N;// Size of saved sequence

    private String key; //Last round Choices N-1 length
    private String currentSequence; //last round choices N length

    public ComputerML(){
        super();
        N=5; //Default N size
    }

    public ComputerML(int seed){
        super(seed);
        N=5; //Default N size
    }

    public ComputerML(RoundSequence seq, int N){
        super();
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
    }
    public ComputerML(int seed, RoundSequence seq, int N){
        super(seed);
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
    }


     @Override
    public Sign makeChoice(){
        storeNSequence();
        if(temp.isEmpty()){
            return super.makeChoice();
        }
        else{
            super.setCurSign(makePrediction());
            return super.getCurSign();
        }
    }



    /**
     * Record last round choices add them to hashmap and add frequency count
     */
    public void storeNSequence(){
        if(temp.isLengthGreater(N)){
            setKey();
            setCurrentSequence();
            if(storedSequence.containsKey(key)){
                SequenceHashMap theMap = storedSequence.get(key);
                if(theMap.hasKey(currentSequence)){
                    theMap.addFreq(currentSequence);
                }
                else{
                    theMap.addHashMapSequence(currentSequence);
                }
            }
            else{
                storedSequence.put(key, new SequenceHashMap(currentSequence));
            }
        }
    }
     /**
     * Record last round choices add them to hashmap and add frequency count
     */
    public void storeNSequence(String sequence, HashMap<String, SequenceHashMap> newMap){
        String mapKey = sequence.substring(0, sequence.length()-1);
        if(newMap.containsKey(mapKey)){
            SequenceHashMap theMap = storedSequence.get(mapKey);
            if(theMap.hasKey(sequence)){
                theMap.addFreq(sequence);
            }                
            else{
                   theMap.addHashMapSequence(sequence);
            }
        }
        else{                
            newMap.put(mapKey, new SequenceHashMap(sequence));
        }
        
    }
    /**
     * Update last round choice throw out old choices size N
     */
    public void setCurrentSequence(){

        currentSequence = temp.getNSizeSequence(N);
    }
    /**
     * Key is sequence of current round size N-1
     */
    public void setKey(){
        String currSequence;
        currSequence = temp.getRoundSequence();
        key = currSequence.substring(startIndex(), lastIndex());
    }
    /**
     * Find the start of the sequence
     * @return
     */
    public int startIndex(){
        String currSequence;
        currSequence = temp.getRoundSequence();
        int startIndex;
        if(currSequence.isEmpty()){
             startIndex=0;
        }
        else{
            startIndex = currSequence.length()-N-1;
        }
        return startIndex;
    }
    /**
     * end of the sequence
     * @return
     */
    public int lastIndex(){
        String currSequence;
        currSequence = temp.getRoundSequence();
        int lastIndex;
        if(currSequence.isEmpty()||currSequence.length()<N){
            return currSequence.length();
        }
        else{
            lastIndex = currSequence.length()-2;
            return lastIndex;
        }
    }
   
    /**
     * The algo
     * @return
     */
    public Sign makePrediction(){
        if(temp.isLengthEqualN(N) && key!=null){
            if(storedSequence.containsKey(key)){
                Sign predictionSign = getMostFreqSeq(key);
                return predictionSign.loseTo();
            }
            else{
                return super.makeChoice();
            }
        }
        return super.makeChoice();
    }
    /**
     * Get the most frequent pattern
     * @param lastChoices the sequence of the last rounds String size N-1
     * @return
     */
    public Sign getMostFreqSeq(String lastChoices){
        String mostSeq;
        SequenceHashMap list = storedSequence.get(lastChoices);
        mostSeq = list.getMostFreqSequence();
        if(mostSeq == null){
            return super.makeChoice();
        }
        else{
            String signToPredict = mostSeq.substring(mostSeq.length()-1);
            
        return Sign.getSignFromString(signToPredict);
    }

    }

    /**
     * UNFINSHED, makes a another hashmap with a different size N, currently just takes the existing saved
     * round results and make a new hashmap. Does not add new individuals rounds to map nor predict the next
     * round
     * @param n
     * @return
     */
    public HashMap<String, SequenceHashMap> storeSequenceWithDifferentN(int n){
        HashMap<String, SequenceHashMap> newMap = new HashMap<>();
        String totalSequence = temp.getRoundSequence();
        String sequenceSizeN;
        for(int i = 0; i < totalSequence.length()-2-n; i = i+2){
            sequenceSizeN = totalSequence.substring(i, i+n-1);
            storeNSequence(sequenceSizeN, newMap);
        }
        return newMap;
    }
    //Testing delete later
    public void printKeyS(){
        System.out.println("Key "+ key+ " Seq "+currentSequence);
    }
    /**
     * Checker
     */
    public void printAll(){
        System.out.println("Total sequence   " + temp.getRoundSequence());
        Set<String> allPatternkey = storedSequence.keySet();
        System.out.println("For Size N="+N);
        for(String str: allPatternkey){
            System.out.print("Key: "+ str+ " Sequences> ");
            storedSequence.get(str).printSequenceFrequency();
        }
    }

    //Testing delete later
    public static void main(String[] args) {
        
        //Player human = new HumanPlayer(null)
        RoundSequence savedRounds = new RoundSequence();
        Player test = new StaticPlayer("RPSRPSRP");
        Player computerML = new ComputerML(0, savedRounds, 5);
        savedRounds.updatePlayers(test, computerML);
        int count =0;
        
        while(count<40){
            test.makeChoice();
            computerML.makeChoice();
            savedRounds.updateRoundSequence();
            count+=1;
        }
        savedRounds.print();
        ComputerML testC = (ComputerML)computerML;
        testC.printAll();



        
    }


}
