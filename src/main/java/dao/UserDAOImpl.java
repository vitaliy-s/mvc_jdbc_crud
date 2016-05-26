package dao;

import model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vitaliy on 04.05.16.
 */

public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> allUser() {
        String sql = "SELECT * FROM test";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {

            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("uname"));
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("mail"));
                return user;
            }
        });
        return userList;
    }

    public void delete(int userId) {
        String sql = "DELETE FROM test WHERE id=?";
        jdbcTemplate.update(sql, userId);
    }

    public User get(int userId) {
        String sql = "SELECT * FROM test WHERE id=" + userId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("uname"));
                    user.setPassword(resultSet.getString("password"));
                    user.setMail(resultSet.getString("mail"));
                    return user;
                }
                return null;
            }
        });
    }

    public void saveOrUpdate(User user) {

        if (user.getId() > 0){
            String sql = "UPDATE test SET uname=?, password=?, mail=? WHERE id=?";
            jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getMail(), user.getId());
        }else {
            String sql = "INSERT INTO test (uname, password, mail) VALUES (?,?,?)";
            jdbcTemplate.update(sql, user.getName(), user.getPassword(),user.getMail());
        }
    }

    @Override
    public List<User> serchUserName(String str) {
        String sql = "SELECT * FROM test WHERE test.uname LIKE ?";

        List<User> userList = jdbcTemplate.query(sql, new Object[]{str + "%"},new RowMapper<User>(){

            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("uname"));
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("mail"));
                return user;
            }
        });
        return userList;
    }

    //rab variant_1
   /*
   private JdbcTemplate jdbcTemplate;
   public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    //test comment
}
