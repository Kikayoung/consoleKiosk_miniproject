package studycafe_user_kiosk;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println();
            return DriverManager.getConnection(
                    // "jdbc:mariadb://localhost:3306/test", "root", "Kkyy7134");
                    "jdbc:mariadb://localhost:3333/studycafe_kiosk", "root", "guro1234");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB 접속 실패");
            return null;
        }
    }
}
