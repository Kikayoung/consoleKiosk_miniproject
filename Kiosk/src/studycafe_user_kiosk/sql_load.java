package studycafe_user_kiosk;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class sql_load {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT seatnum, reserved FROM seat_info";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 결과 집합 가져오기
                try (ResultSet rs = pstmt.executeQuery()) {
                    // 결과 처리를 위한 배열 선언
                    int[][] seatArray = new int[240][2];
                    int index = 0;

                    // 결과 반복 처리
                    while (rs.next()) {
                        String seatnum = rs.getString("seatnum");
                        int reserved = rs.getInt("reserved");

                        // 배열에 좌석 정보 저장
                        seatArray[index][0] = Integer.parseInt(seatnum);
                        seatArray[index][1] = reserved;
                        index++;
                    }
                    printSeats(seatArray);
                }
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
                
    }

    public static void printSeats(int[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            int seatNum = seats[i][0];
            int reserved = seats[i][1];
            if (reserved == 0) {
                System.out.print(String.valueOf(seatNum) + "   \t");
            } else {
                System.out.print("■   \t");
            }
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}

