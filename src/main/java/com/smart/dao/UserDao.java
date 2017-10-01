package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName,String passward){
        String sqlStr = "SELECT COUNT(*) FROM t_user WHERE user_name = ? AND password = ? ";
        return jdbcTemplate.queryForObject(sqlStr,new Object[] {userName,passward},Integer.class);
    }

    public User findUserByUserName(String userName){
        String sqlStr = "SELECT * FROM t_user WHERE user_name = ? ";
        return jdbcTemplate.queryForObject(sqlStr,new Object[] {userName},User.class);
    }

    public void updateLoginInfo (User user){
        String sqlStr = "UPDATE t_user SET last_visit = ?,last_ip = ?,credits = ? WHERE user_id = ? ";
        jdbcTemplate.update(sqlStr,new Object[] {user.getLastVisit(),user.getLastIp(),user.getCredits()});
    }
}
