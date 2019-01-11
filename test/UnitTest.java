
import models.Settings;
import models.User;
import org.junit.Test;
import play.test.WithApplication;
import io.ebean.Ebean;
import io.ebean.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UnitTest extends WithApplication {

    @Test
    public void getUser() {

        User user = User.find
                .query()
                .setId(1L)
                .findOne();

        assertNotEquals(null, user);

    }

    @Test // In this case Query threw SQLException
    public void getDeletedUser() {

        User user = User.find
                .query()
                .setId(1L)
                .setIncludeSoftDeletes()
                .findOne();

        assertNotEquals(null, user);

    }

    @Test
    public void getDeletedSettings() {

        Settings settings = Settings.find
                .query()
                .setId(1L)
                .setIncludeSoftDeletes()
                .findOne();

        assertNotEquals(null, settings);

    }

    @Test
    public void ebeanMultipleNativeSql() {

        Query<User> query1 = Ebean.findNative(User.class, "select * from users where id in (:ids)");

        query1.setParameter("ids", new ArrayList<Long>() {{
            add(1L);
        }});

        List<User> l1 = query1.findList();

        Query<User> query2 = Ebean.findNative(User.class, "select * from users where id in (:ids)");

        query2.setParameter("ids", new ArrayList<Long>() {{
            add(1L);
            add(2L);
        }});

        List<User> l2 = query2.findList();

        assertEquals(3, l1.size() + l2.size());

    }

    @Test
    public void ebeanNativeSql1() {

        Query<User> query = Ebean.findNative(User.class, "select * from users where id in (:ids)");

        query.setParameter("ids", new ArrayList<Long>() {{
            add(1L);
        }});

        List<User> l = query.findList();

        assertEquals(1, l.size());

    }

    @Test
    public void ebeanNativeSql2() {

        Query<User> query = Ebean.findNative(User.class, "select * from users where id in (:ids)");

        query.setParameter("ids", new ArrayList<Long>() {{
            add(1L);
            add(2L);
        }});

        List<User> l = query.findList();

        assertEquals(2, l.size());

    }

}
