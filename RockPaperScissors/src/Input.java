import java.util.Scanner;

public class Input {

    public int inputThhrow(){
        int throwSign;
        throwSign =-1;
        Scanner scnr = new Scanner(System.in);
        
        
        boolean valid=false;
        while(!valid){
            try {
                System.out.println("Enter A Number: 0.Rock, 1.Paper, or 2.Scissors");
                    
                    throwSign = scnr.nextInt(2);

                    } catch (Exception e) {
                        valid = false;
                        System.out.println(throwSign+"is not a valid input");
                        System.out.println("Enter correct amount again");
                        throwSign = scnr.nextInt();
                    }
                    if (0<=throwSign && throwSign<=2) {
                        valid = true;
                    }
       
        }
        return throwSign;
    }

}
