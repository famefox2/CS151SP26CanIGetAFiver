
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class ComputerML extends Computer{

    private RoundSequence temp = new RoundSequence(null, null);//Access to round results

    private HashMap<String, SequenceHashMap> storedSequence;// Round Sequence stored 
    

    private final int N;// Size of saved sequence

    private String key; //Last round Choices N-1 length
    private String currentSequence; //last round choices N length

    private static final String DATA_FILE="ml_data.txt";

    public ComputerML(){
        super();
        N=5; //Default N size
        storedSequence = new HashMap<>();
    }

    public ComputerML(int seed){
        super(seed);
        N=5; //Default N size
        storedSequence = new HashMap<>();
        loadData();
    }

    public ComputerML(RoundSequence seq, int N){
        super();
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
        loadData();
    }
    public ComputerML(int seed, RoundSequence seq, int N){
        super(seed);
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
        loadData();
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

    @Override
    public Sign chooseSign(){
        return makeChoice();
    }

    @Override
    public String getAlgorithmName(){
        return "Machine Learning";
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
        int start=currSequence.length()-N;
        int end = currSequence.length()-1;
        if(start<0){
            start=0;
        }
        key = currSequence.substring(start,end);
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
     * Makes a another hashmap with a different size N
     * @param n new sequence size
     * @return new hashmap of sequences
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

    public void setRoundSequence(RoundSequence seq){
        this.temp=seq;
    }

    public RoundSequence getRoundSequence(){
        return temp;
    }

    /**
     * Saves stored frequency data to a file so it persists between games
     */
    public void saveData() {
        try {
            PrintWriter writer =new PrintWriter(DATA_FILE);
            Set<String> keys=storedSequence.keySet();
            for(String k : keys){
                SequenceHashMap freqMap = storedSequence.get(k);
                HashMap<String, Integer> innerMap= freqMap.getaSeqHashMap();
                Set<String> seqKeys =innerMap.keySet();
                StringBuilder line=new StringBuilder();
                line.append(k).append(":");
                boolean first=true;
                for (String seqKey : seqKeys){
                    if(!first){
                        line.append(",");
                    }
                    line.append(seqKey).append("=").append(innerMap.get(seqKey));
                    first =false;
                }
                writer.println(line.toString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Could not save ML data: "+e.getMessage());
        }
    }

    public void loadData() {
        try {
            File file =new File(DATA_FILE);
            if (!file.exists()){
                return;
            }
            Scanner fileScanner=new Scanner(file);
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()){
                    continue;
                }
                // Format: KEY:SEQ1=FREQ1,SEQ2=FREQ2,...
                int colonIndex = line.indexOf(":");
                if (colonIndex < 0){
                    continue;
                }
                String fileKey=line.substring(0, colonIndex);
                String pairs=line.substring(colonIndex + 1);
                String[] entries = pairs.split(",");
                SequenceHashMap freqMap =null;
                for (String entry:entries) {
                    String[] parts=entry.split("=");
                    if (parts.length==2){
                        String seqKey=parts[0];
                        int freq = Integer.parseInt(parts[1]);
                        if (freqMap== null){
                            freqMap =new SequenceHashMap(seqKey);
                            for (int i= 1;i<freq;i++) {
                                freqMap.addFreq(seqKey);
                            }
                        }else{
                            freqMap.addHashMapSequence(seqKey);
                            for (int i= 1;i<freq;i++){
                                freqMap.addFreq(seqKey);
                            }
                        }
                    }
                }
                if (freqMap != null){
                    storedSequence.put(fileKey,freqMap);
                }
            }
            fileScanner.close();
        } catch (Exception e){
            System.out.println("Could not load ML data: "+ e.getMessage());
        }
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
