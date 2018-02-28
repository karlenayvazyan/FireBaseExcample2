package am.ak.firebase.data.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileStore;

public final class FireBaseDBFactory {

    private volatile static Firestore databaseReference = null;

    private FireBaseDBFactory() {
    }

    public static Firestore getInstance() {
        if (databaseReference == null) {
            synchronized (FireBaseDBFactory.class) {
                if (databaseReference == null) {
                    databaseReference = init();
                }
            }
        }
        return databaseReference;
    }

    private static Firestore init() {
        try (InputStream serviceAccount = new FileInputStream(new File("serviceAccountKey.json"))) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://javafirebaseexcample.firebaseio.com/")
                    .build();

            FirebaseApp testApp = FirebaseApp.initializeApp(options, "TestApp");

            return FirestoreClient.getFirestore(testApp);
        } catch (Exception e) {
            throw new RuntimeException("Can't create databaseReference");
        }
    }
}
