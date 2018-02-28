package am.ak.firebase.data.dao;

import am.ak.firebase.data.model.User;
import org.threeten.bp.Instant;

public interface IUserDao {

    Instant add(User user);

    User get(String uuid);

    Instant update(User user);

    Instant remove(String uuid);
}
