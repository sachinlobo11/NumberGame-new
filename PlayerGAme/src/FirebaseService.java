import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseService {
    public static void serviceForsend(String Opinion, String number, String guess) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("name", Opinion);
        data.put("number",number);
        data.put("guess",guess);

        ApiFuture<WriteResult> result = db.collection("users").document("Number").set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());


    }
    public static void serviceForsend(String Opinion, int number) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("name", Opinion);
        data.put("number",number);

        ApiFuture<WriteResult> result = db.collection("users").document("Number").set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());


    }
    public static void serviceForsend(String Opinion) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("name", Opinion);

        ApiFuture<WriteResult> result = db.collection("users").document("Number").set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());


    }

    public static void serviceForRead() throws ExecutionException, InterruptedException {
        // asynchronously retrieve all users
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
// ...
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("name"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("lastname"));
            System.out.println("Born: " + document.getLong("points"));
        }
    }

    public static String getDocumentdata(String query) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document("Number");
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            System.out.println(document.getString("name"));


            return document.getString(query);





        } else {
            System.out.println("No such document!");
        }
        return "exit";

    }
}
