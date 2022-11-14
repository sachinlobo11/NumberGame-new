import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        FileInputStream serviceAccount = new FileInputStream("numbergame-de078-firebase-adminsdk-whekw-084c44b6c4.json");


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        //FirebaseConfiguration.shared.setLoggerLevel(.min);
        //FirebaseApp.configure();
        System.out.println("setup done");
        FirebaseService.serviceForsend("null","0","null");
        initializeGame();
        ///**************************
    }


        public static void initializeGame() throws ExecutionException, InterruptedException {
            System.out.println("Host/ Player?");
            Scanner sc = new Scanner(System.in);
            String Opinion = sc.nextLine();
            if (FirebaseService.getDocumentdata("name").equals("null")) {
                FirebaseService.serviceForsend(Opinion,"0","null");
                OnNull.forNull(Opinion);

            }
            if (Opinion.equalsIgnoreCase(FirebaseService.getDocumentdata("name").toString())) {
                // Repeat DisAgree line 28
                FirebaseService.serviceForsend("disagree","0","null");
                initializeGame();
            }
            if (!Opinion.equalsIgnoreCase(FirebaseService.getDocumentdata("name").toString())) {
                System.out.println("Opposite of opinion found (Agreed!)");
                FirebaseService.serviceForsend("agree","0","null");// Agree
                OnAgree.agree(Opinion);


            }
        }

    //FirebaseService.serviceForRead();
    }







