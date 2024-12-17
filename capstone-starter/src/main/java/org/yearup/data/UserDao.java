package org.yearup.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import org.yearup.models.User;
import org.yearup.models.authentication.Authority;

import javax.sql.DataSource;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Set;

public abstract class UserDao {
    private final DataSource dataSource;
    private int id;
    private String username;
    private String password;
    private boolean activated;
    private Set<Authority> authorities;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource; }

    public abstract List<User> getAll();

    public abstract User getUserById(int id);

    public User getByUserName(String username) {
        return null; }

    public User create(User user) {
        return user; }

    public abstract int getIdByUsername(String username);

    public boolean exists(String username) {
        return false; }
}