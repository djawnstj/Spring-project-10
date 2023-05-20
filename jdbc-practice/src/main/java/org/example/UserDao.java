package org.example;

import java.sql.*;

public class UserDao {

    public void create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "insert into users values(?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }

    public void createV2(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "insert into users values(?, ?, ?, ?)";

        jdbcTemplate.executeUpdate(user, sql, (pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "select userId, password, name, email from users where userId = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }

        return user;
    }

    public User findByUserIdV2(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "select userId, password, name, email from users where userId = ?";

        User user = (User) jdbcTemplate.executeQuery(
                sql,
                (pstmt) -> pstmt.setString(1, userId),
                (rs) -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"))
        );

        return user;
    }
}
