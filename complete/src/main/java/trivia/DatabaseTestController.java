package trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class DatabaseTestController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/test")
    public int test() {

        int count = 0;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(" ")) {

            if(rs.next()) {
                count = rs.getInt("C");
                System.out.println("Count = " + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
