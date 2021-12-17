package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.JdbcConfig;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

    private final JdbcConfig jdbcConfig;

    private static final String CREATE_USER_QUERY = "insert into users values (default,?,?,?,?)";
    private static final String UPDATE_USER_QUERY = "update users set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_USER_QUERY = "delete from users where id = ?";
    private static final String EXIST_USER_BY_ID_QUERY = "select count(*) from users where id = ";
    private static final String EXIST_USER_BY_EMAIL_QUERY = "select count(*) from users where email like ?";
    private static final String SELECT_USER_BY_ID_QUERY = "select * from users where id =  ";
    private static final String SELECT_USER_BY_EMAIL_QUERY = "select * from users where email like ?";
    private static final String SELECT_USERS_QUERY = "select * from users";

    public UserDaoImpl(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }
//    private final String CREATE_USER_QUERY = "insert into users values (default,'f','l','e',0)";

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(CREATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(UPDATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(DELETE_USER_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Integer id) {
        int count = 0;
        try (ResultSet resultSet = jdbcConfig.getConnection().createStatement().executeQuery(EXIST_USER_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public boolean existByEmail(String email) {
        int count = 0;
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(EXIST_USER_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("count(*)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public User findById(Integer id) {
        try (ResultSet resultSet = jdbcConfig.getConnection().createStatement().executeQuery(SELECT_USER_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return initUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(SELECT_USER_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return initUserByResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = jdbcConfig.getConnection().createStatement().executeQuery(SELECT_USERS_QUERY)) {
            while (resultSet.next()) {
                users.add(initUserByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataTableResponse<User> userDataTableResponse = new DataTableResponse<>();
        userDataTableResponse.setItems(users);

        return userDataTableResponse;
    }

    private User initUserByResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        int age = resultSet.getInt("age");

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAge(age);

        return user;
    }

    private void initTable() {
        String query = "CREATE SCHEMA IF NOT EXISTS `rest_jdbc` DEFAULT CHARACTER SET utf8;";
        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "CREATE TABLE `rest_jdbc`.`new_table` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `first_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT '',\n" +
                "  `last_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT '',\n" +
                "  `email` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,\n" +
                "  `age` INT NULL DEFAULT 0,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);";

        try (PreparedStatement preparedStatement = jdbcConfig.getConnection().prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
