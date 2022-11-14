import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class OnNull {
    public static void forNull(String Opinion) throws InterruptedException, ExecutionException {
    System.out.println("got null.........");
   // TimeUnit.SECONDS.sleep(15);
    if (FirebaseService.getDocumentdata("name").equals("agree")) {
        OnAgree.agree(Opinion);
    } else if (FirebaseService.getDocumentdata("name").equals("disagree")) {
        System.out.println("disagreed");
        // goto line 28
        Main.initializeGame();

    } else if (FirebaseService.getDocumentdata("name").equals(Opinion)) {
        System.out.println("Other player is sleeping! (Offline)");
    } else {
        System.out.println("Unexpected! " + FirebaseService.getDocumentdata("name"));
    }
}
}
