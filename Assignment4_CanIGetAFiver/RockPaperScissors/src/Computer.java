import java.util.Random;

public class Computer extends Player {


    
    private int result;
    private Random gen;

    /**
     * Create a random generator for Computer
     */
    public Computer(){
        Random rand = new Random();
        gen = rand;
        super.setPlayerName("Computer");
    }
    /**
     * Set a specific seed for the random generator for computer
     * @param seed a specific seed number 
     */
    public Computer(int seed){
        super.setPlayerName("Computer");
        gen = new Random(seed);
    }
    public Computer(int seed, int bound){
        super.setPlayerName("Computer");
        gen = new Random(seed);
    }
    /**
     * Computer make a choice for rock, paper , or scissors. Return its chosen sign
     * @return computer's sign choice 
     */
    @Override
    public Sign makeChoice(){
        calculateResults();
        intToEnum(result);
        return super.getCurSign();
    }
    
    
    /**
     * Generate a number from 0 to 2
     */
    public void calculateResults(){
        result = gen.nextInt(3);
    }
    
    public int getResult(){
        return result;
    }
    public void print(){
        System.out.println("Computer chosen:");
        System.out.println(result);
    }

    public static void main(String[] args) {
        Computer one = new Computer();
        Computer two = new Computer(10);
        one.calculateResults();
        two.calculateResults();
        one.print();
        two.print();

    }


}
