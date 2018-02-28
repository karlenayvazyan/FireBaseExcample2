package am.ak.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FireBaseFirstExample {

    public void configure() {

        try (InputStream serviceAccount = new FileInputStream(new File("serviceAccountKey.json"))) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://javafirebaseexcample.firebaseio.com/")
                    .build();

            FirebaseApp testApp = FirebaseApp.initializeApp(options, "TestApp");

            DatabaseReference reference = FirebaseDatabase.getInstance(testApp).getReference();

            DatabaseReference usersRef = reference.child("users");
            Map<String, User> users = new HashMap<>();
            users.put("alanisawesome", new User("June 23, 1912", "Alan Turing"));
            users.put("gracehop", new User("December 9, 1906", "Grace Hopper"));

            usersRef.setValueAsync(users).addListener(() -> System.out.println("done"), Runnable::run);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class User {

        public String date_of_birth;
        public String full_name;
        public String nickname;

        public User(String date_of_birth, String full_name) {
            this.date_of_birth = date_of_birth;
            this.full_name = full_name;
        }

        public String getDate_of_birth() {
            return date_of_birth;
        }

        public void setDate_of_birth(String date_of_birth) {
            this.date_of_birth = date_of_birth;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
