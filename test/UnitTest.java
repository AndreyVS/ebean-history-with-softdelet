
import models.Settings;
import models.User;
import org.junit.Test;
import play.test.WithApplication;

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

}
