//package studycafe_user_kiosk;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class DatabaseConnection {
//    public static Connection getConnection() {
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//            System.out.println();
//            return DriverManager.getConnection(
//                    // "jdbc:mariadb://localhost:3306/test", "root", "Kkyy7134");
//                    "jdbc:mariadb://localhost:3333/studycafe_kiosk", "root", "guro1234");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("DB 접속 실패");
//            return null;
//        }
//    }
//}
//
//
//
//
////package studycafe_user_kiosk;
////
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.PreparedStatement;
////import java.sql.SQLException;
////
////public class sql_save_first {
////  public static void main(String[] args) {
////      // 데이터베이스 연결 정보
////      // String url = "jdbc:mariadb://localhost:3306/test";
////      // String username = "root";
////      // String password = "Kkyy7134";
////      String url = "jdbc:mariadb://localhost:3333/studycafe_kiosk";
////      String username = "root";
////      String password = "guro1234";
////
////      // SQL 쿼리
////      String sql = "INSERT INTO seat_info (type, seatnum, reserved) VALUES (?, ?, ?)";
////
////      // 연결 및 쿼리 실행
////      try (Connection conn = DriverManager.getConnection(url, username, password);
////           PreparedStatement pstmt = conn.prepareStatement(sql)) {
////
////          // type A, B, C에 해당하는 좌석 정보 추가
////          for (char type = 'A'; type <= 'C'; type++) {
////              int seat_num = 0;
////              if (type=='A'){
////                  seat_num = 8;}
////              else if (type=='B'){
////                  seat_num = 16;
////              }
////              else {
////                  seat_num = 4;
////              }
////              for (int i = 1; i <= seat_num; i++) {
////                  pstmt.setString(1, String.valueOf(type)); // type
////                  pstmt.setString(2, String.valueOf(i)); // seatnum
////                  pstmt.setInt(3, 0); // reserved
////                  pstmt.executeUpdate(); // 쿼리 실행
////              }
////          }
////          System.out.println("좌석 정보가 성공적으로 추가되었습니다.");
////
////      } catch (SQLException e) {
////          System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
////          e.printStackTrace();
////      }
////  }
////}
