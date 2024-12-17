package org.yearup.data;

import org.springframework.stereotype.Repository;
import org.yearup.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final List<User> users = new ArrayList<>(); // Example in-memory store

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getUserById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null); }

    @Override
    public User getByUserName(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null); }

    @Override
    public int getIdByUsername(String username) {
        User user = getByUserName(username);
        return user != null ? user.getId() : -1; }

    @Override
    public User create(User user) {
        users.add(user);
        return user; }

    @Override
    public boolean exists(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username)); }
}
