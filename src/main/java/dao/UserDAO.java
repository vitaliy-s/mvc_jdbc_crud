package dao;

import model.User;

import java.util.List;

/**
 * Created by vitaliy on 04.05.16.
 */
public interface UserDAO {

    public List<User> allUser();

    void delete(int userId);
    User get(int userId);
    void saveOrUpdate(User user);

}
