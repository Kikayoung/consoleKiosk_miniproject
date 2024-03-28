
package studycafe_user_kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class StudyRoomReservePage {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n***** 스터디룸 예약 *****");
            System.out.println("1. 3-4인실 (20000원/시간)");
            System.out.println("2. 5-6인실 (35000원/시간)");
            System.out.println("3. 8-10인실 (50000원/시간)");
            System.out.println("0. 뒤로 가기");
            System.out.print("선택: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    reserveRoom("C_1", 20000);
                    break;
                case 2:
                    reserveRoom("C_2", 35000);
                    break;
                case 3:
                    reserveRoom("C_3", 50000);
                    break;
                case 0:
                    System.out.println("이전 페이지로 돌아갑니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    public static void reserveRoom(String roomType, int pricePerHour) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n***** " + getRoomTypeName(roomType) + " *****");

        int remainingRooms = getRemainingRooms(roomType);

        if (remainingRooms <=0) {
            System.out.println("남은 방이 없습니다. ");
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String updateSql = "UPDATE study_rooms SET remainingRooms = remainingRooms - 1 WHERE roomType = ?";
            try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                updatePstmt.setString(1, roomType);
                updatePstmt.executeUpdate();
                System.out.println("비어 있는 스터디룸 " + (remainingRooms - 1) + "개");

                System.out.print("몇 시간 이용하시겠습니까? ");
                int hours = sc.nextInt();

                System.out.println("이용요금은 시간당 " + pricePerHour + "원입니다.");
                System.out.println("총 이용 금액은 " + (pricePerHour * hours) + "원입니다.");
                PaymentSystem ps = new PaymentSystem(pricePerHour * hours);
                ps.pay();
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    public static int getRemainingRooms(String roomType) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT remainingRooms FROM study_rooms WHERE roomType = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, roomType);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("remainingRooms");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return 0;
    }


    public static String getRoomTypeName(String roomType) {
        switch (roomType) {
            case "C_1":
                return "3-4인실 스터디룸";
            case "C_2":
                return "5-6인실 스터디룸";
            case "C_3":
                return "8-10인실 스터디룸";
            default:
                return "Unknown";
        }
    }
}