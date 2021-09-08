package by.itransition;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
        if(args.length<=2){
            System.out.println("Use more than two parameters");
            System.out.println("Usage: >java -jar game.jar rock paper scissors");
            System.exit(1);
        }
        if(args.length%2==0){
            System.out.println("Use an odd number of parameters");
            System.out.println("Usage: >java -jar game.jar rock paper scissors");
            System.exit(1);
        }
        if(!Arrays.stream(args).allMatch(new HashSet<>()::add)){
            System.out.println("Use unique parameters");
            System.out.println("Usage: >java -jar game.jar rock paper scissors");
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        UtilHMAC utilHMAC = new UtilHMAC();
        Referee referee = new Referee(args);
        UtilTable table = new UtilTable(args,referee);
        int compMove;
        String answer;
        do{
            compMove = SecureRandom.getInstanceStrong().nextInt(args.length);
            System.out.printf("\nHMAC:\n%s\n",utilHMAC.getHMAC(args[compMove]));
            System.out.println(table.getMenu());
            System.out.print("Enter your move: ");
            answer = scanner.next();
            if(answer.equals("?")){
                System.out.println(table.getTable());
            }
            else if(answer.equals("0")) {
                break;
            }
            else{
                System.out.printf("Your move: %s\n",args[Integer.parseInt(answer)-1]);
                System.out.printf("Computer move: %s\n",args[compMove]);
                switch (referee.play(args[Integer.parseInt(answer)-1],args[compMove])){
                    case "DRAW":
                        System.out.println("draw");
                        break;
                    case "LOSE":
                        System.out.println("You lose");
                        break;
                    case "WIN":
                        System.out.println("You win");
                }
                System.out.printf("HMAC key: %s\n",utilHMAC.getKey());
            }
        }while (true);
    }
}
