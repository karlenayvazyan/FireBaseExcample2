package am.ak.firebase.data.dao.impl;

import am.ak.firebase.data.dao.IUserDao;
import am.ak.firebase.data.model.User;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.Instant;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private IUserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void add() {
        User user = new User();
        user.setName("TestName");
        user.setSurname("TestSurName");
        user.setEmail("TestEmail");
        user.setPassword("TestPassword");
        Instant instant = userDao.add(user);

        assertNotNull(instant);
    }

    @Test
    public void get() {
        // add user
        User user = new User();
        user.setName("TestName");
        user.setSurname("TestSurName");
        user.setEmail("TestEmail");
        user.setPassword("TestPassword");
        userDao.add(user);
        User userResult = userDao.get(user.getUuid());
        assertNotNull(userResult);
    }

    @Test
    public void update() {
        User user = new User();
        user.setName("TestName");
        user.setSurname("TestSurName");
        user.setEmail("TestEmail");
        user.setPassword("TestPassword");
        userDao.add(user);
        user.setName("TestNameUpdated");
        user.setSurname("TestSurNameUpdated");
        user.setEmail("TestEmailUpdated");
        user.setPassword("TestPasswordUpdated");

        Instant update = userDao.update(user);
        assertNotNull(update);
    }

    @Test
    public void remove() {
        User user = new User();
        user.setName("TestName");
        user.setSurname("TestSurName");
        user.setEmail("TestEmail");
        user.setPassword("TestPassword");
        userDao.add(user);
        Instant remove = userDao.remove(user.getUuid());
        assertNotNull(remove);
    }
}