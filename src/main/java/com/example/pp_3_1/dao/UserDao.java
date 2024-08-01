package com.example.pp_3_1.dao;

import com.example.pp_3_1.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void delete(User user);
    void update(User user);
    User getUser(long id);
    void deleteById(Long id);
    List<User> listUsers();
    void clearTable();
}
