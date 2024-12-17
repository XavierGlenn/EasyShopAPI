package org.yearup.data;

import org.yearup.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public abstract class UserDao {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword; }



    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword); }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close(); }
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, activated, authorities) VALUES (?, ?, ?, ?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isActivated());
            statement.setString(4, String.join(",", user.getAuthorities().stream()
                    .map(authority -> authority.getName()).toArray(String[]::new)));

            boolean rowInserted = statement.executeUpdate() > 0;
            return rowInserted;
        } finally {
            disconnect(); }
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean activated = resultSet.getBoolean("activated");
                String authorities = resultSet.getString("authorities");

                User user = new User(id, username, password, authorities);
                listUser.add(user); }

            return listUser;
        } finally {
            disconnect(); }
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users where id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());

            boolean rowDeleted = statement.executeUpdate() > 0;
            return rowDeleted;
        } finally {
            disconnect(); }
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, activated = ?, authorities = ? WHERE id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isActivated());
            statement.setString(4, String.join(",", user.getAuthorities().stream()
                    .map(authority -> authority.getName()).toArray(String[]::new)));
            statement.setInt(5, user.getId());

            boolean rowUpdated = statement.executeUpdate() > 0;
            return rowUpdated;
        } finally {
            disconnect(); }
    }

    public abstract List<User> getAll();

    public abstract User getUserById(int id);

    public User getByUserName(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    boolean activated = resultSet.getBoolean("activated");
                    String authorities = resultSet.getString("authorities");
                    user = new User(id, username, password, authorities); }
            }
            return user;
        } finally {
            disconnect(); }
    }

    public abstract int getIdByUsername(String username);

    public boolean exists(String username) {
    }

    public User create(User user) {
    }
    public UserDao(DataSource dataSource) {
    }
}