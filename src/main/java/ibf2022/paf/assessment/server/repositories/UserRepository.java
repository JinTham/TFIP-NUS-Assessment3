package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_FINDUSERBYUSERNAME = "select * from user where username = ?";
    private static final String SQL_INSERTUSER = "insert into user (user_id, username, name) values (?,?,?)";

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FINDUSERBYUSERNAME,username);
        if (!rs.next()){
            return Optional.empty();
        }
        User user = new User();
        user.setName(rs.getString("name"));
        user.setUserId(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        return Optional.of(user);
    }

    public String insertUser(User user) {
        String userId = UUID.randomUUID().toString().substring(0, 8);
        int inserted = jdbcTemplate.update(SQL_INSERTUSER,userId,user.getUsername(),user.getName());
        if (inserted >0){
            return userId;
        }
        return "User insert failed!";
    }
}
