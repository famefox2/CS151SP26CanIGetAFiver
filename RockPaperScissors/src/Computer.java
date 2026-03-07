import java.util.Random;

public class Computer extends Player {


    String name = "Computer";
    int difficulty;
    int result;
    Random gen;

    public Computer(){
        Random rand = new Random();
        gen = rand;
        difficulty = rand.nextInt(3);
    }
    @Override
    public Sign makeChoice(){
        calculateResults();
        intToEnum(result);
        return super.getCurSign();
    }
    
    public Computer(int seed){
        gen = new Random(seed);
    }

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
