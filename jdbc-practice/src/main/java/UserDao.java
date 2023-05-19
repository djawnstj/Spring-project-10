import java.sql.*;

public class UserDao {

    private Connection getConnection() {
        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
        String id = "sa";
        String password = "";

        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, id, password);
        } catch (Exception e) {
            return null;
        }
    }

    public void create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ptsmt = null;

        try {
            con = getConnection();
            String sql = "insert into users values(?, ?, ?, ?)";
            ptsmt = con.prepareStatement(sql);
            ptsmt.setString(1, user.getUserId());
            ptsmt.setString(2, user.getPassword());
            ptsmt.setString(3, user.getName());
            ptsmt.setString(4, user.getEmail());

            ptsmt.executeUpdate();
        } finally {
            if (ptsmt != null) ptsmt.close();
            if (con != null) con.close();
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ptsmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = getConnection();
            String sql = "select userId, password, name, email from users where userId = ?";

            ptsmt = con.prepareStatement(sql);
            ptsmt.setString(1, userId);

            rs = ptsmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ptsmt != null) ptsmt.close();
            if (con != null) con.close();
        }

        return user;
    }
}
