package am.ak.firebase.data.dao.impl;

import am.ak.firebase.data.dao.IUserDao;
import am.ak.firebase.data.model.User;
import am.ak.firebase.data.util.FireBaseDBFactory;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.threeten.bp.Instant;

import java.util.UUID;

public class UserDaoImpl implements IUserDao {

    private Firestore firestore;

    public UserDaoImpl() {
        this.firestore = FireBaseDBFactory.getInstance();
    }

    @Override
    public Instant add(User user) {
        try {
            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);
            ApiFuture<WriteResult> set = firestore.collection("users").document(uuid)
                    .set(user);
            return set.get().getUpdateTime();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User get(String uuid) {
        ApiFuture<DocumentSnapshot> users = firestore.collection("users").document(uuid).get();
        try {
            DocumentSnapshot documentSnapshot = users.get();
            User user = new User();
            user.setUuid(documentSnapshot.getId());
            user.setEmail(documentSnapshot.getString("email"));
            user.setPassword(documentSnapshot.getString("password"));
            user.setName(documentSnapshot.getString("name"));
            user.setSurname(documentSnapshot.getString("surname"));
            return user;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Instant update(User user) {
        try {
            ApiFuture<WriteResult> set = firestore.collection("users").document(user.getUuid())
                    .set(user);
            return set.get().getUpdateTime();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Instant remove(String uuid) {
        try {
            ApiFuture<WriteResult> users = firestore.collection("users").document(uuid).delete();
            return users.get().getUpdateTime();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
