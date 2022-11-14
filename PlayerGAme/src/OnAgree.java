import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
public class OnAgree {
    public static void agree(String Opinion) throws ExecutionException, InterruptedException {
            Scanner sc = new Scanner(System.in);
        if (Opinion.equalsIgnoreCase("host")) {
            System.out.println("Congrats you are host! Enter secret number: ");
            String number = sc.next();
            FirebaseService.serviceForsend("agree", number,"null");
            System.out.println("wait for 15 seconds!");
            TimeUnit.SECONDS.sleep(15);
            if (FirebaseService.getDocumentdata("guess").equals("guessed")) {
                System.out.println(" They guessed number!");
            } else if (FirebaseService.getDocumentdata("guess").equals("nguessed")) {
                System.out.println("You won!");
                Main.initializeGame();
            }
            else{

                System.out.println("Still not guessed Do you want to continue? (guess)= "+FirebaseService.getDocumentdata("guess"));

            }
        }
        else {
            System.out.println("Fetching secret number>>>> ");
            String secretNumber =  FirebaseService.getDocumentdata("number");
            String gn = sc.next();
            for(int i=0;i<3;i++) if (gn.equalsIgnoreCase(secretNumber)) {
                System.out.println("number guessed");
                FirebaseService.serviceForsend("agree",gn,"guessed");
            }else {
                System.out.println("Not guessed");
                FirebaseService.serviceForsend("agree", secretNumber, "nguessed");


            } }
    }
}
