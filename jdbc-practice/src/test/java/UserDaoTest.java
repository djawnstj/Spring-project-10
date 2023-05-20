import org.example.ConnectionManager;
import org.example.User;
import org.example.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class UserDaoTest {

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.createV2(new User("wizard", "password", "name", "email"));

        User user = userDao.findByUserIdV2("wizard");

        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
