package studycafe_user_kiosk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SeatReservationPage {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== 좌석 예약 ===");
            System.out.println("1. 1인실 좌석");
            System.out.println("2. 카페형 좌석");
            System.out.println("3. 스터디룸");
            System.out.println("0. 뒤로 가기");
            System.out.print("선택: ");
            choice = sc.nextInt();
 
            switch (choice) {
                case 1:
                    // 1인실 좌석 정보 표시
                    seat_present("A", 5000, 8); // 예를 들어 1인실 좌석이 총 8개 있다고 가정
                    break;
                case 2:
                    // 카페형 좌석 정보 표시
                    seat_present("B", 2000, 16); // 예를 들어 카페형 좌석이 총 16개 있다고 가정
                    break;
                case 3:
                    // 스터디룸 정보 표시
                    // 스터디룸의 경우, 더 세분화된 선택지 제공이 필요할 수 있으므로 별도의 메소드 호출
                    // reserveStudyRoom();
                    seat_present("C", 2000, 16); 
                    break;
                case 0:
                    System.out.println("이전 페이지로 돌아갑니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }
    
    public static void seat_present(String type, int pricePerHour, int totalSeats) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = String.format("SELECT seatnum, reserved FROM seat_info WHERE type = '%s'", type);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    int[][] seatArray = new int[totalSeats][2]; // 해당 타입의 총 좌석 수만큼 배열 생성
                    int index = 0;
                    
                    while (rs.next()) {
                        String seatnum = rs.getString("seatnum");
                        int reserved = rs.getInt("reserved");

                        // 배열에 좌석 정보 저장
                        seatArray[index][0] = Integer.parseInt(seatnum);
                        seatArray[index][1] = reserved;
                        index++;
                    }
            
                    printSeats(seatArray);
                    reserveSeat(type, pricePerHour, totalSeats); // 좌석 예약
                }
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    public static void printSeats(int[][] seats) {
        System.out.println("좌석 현황:");
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
        System.out.println("\n■ 표시된 자리는 이미 예약되어 선택이 불가합니다.");
    }

    public static void reserveSeat(String seatType, int pricePerHour, int totalSeats) {
        Scanner sc = new Scanner(System.in);
        System.out.print("원하는 좌석 번호를 입력하세요 (0 입력 시 뒤로 가기): ");
        int seatNumber = sc.nextInt();
        if (seatNumber == 0) {
            return; // 뒤로 가기
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkSql = "SELECT reserved FROM seat_info WHERE type = ? AND seatnum = ?";
            try (PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
                checkPstmt.setString(1, seatType);
                checkPstmt.setInt(2, seatNumber);
                ResultSet rs = checkPstmt.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("reserved") == 1) {
                        System.out.println("이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.");
                        return;
                    }
                }
            }

            String updateSql = "UPDATE seat_info SET reserved = 1 WHERE type = ? AND seatnum = ?";
            try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                updatePstmt.setString(1, seatType);
                updatePstmt.setInt(2, seatNumber);
                updatePstmt.executeUpdate();
                System.out.println("좌석 예약이 완료되었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        System.out.println("이용요금은 시간당 " + pricePerHour + "입니다.");
        System.out.print("이용할 시간(시간 단위)을 입력해주세요: ");
        int hours = sc.nextInt();
        int totalPrice = hours * pricePerHour;
        System.out.println("총 이용 금액은 " + totalPrice + "원입니다.");
    }

    public static void reserveStudyRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("***** 스터디룸 예약 *****");
        System.out.println("1. 3-4인실");
        System.out.println("2. 5-6인실");
        System.out.println("3. 8-10인실");
        System.out.print("선택: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                reserveSeat("3-4인실 스터디룸", 20000, 3);
                break;
            case 2:
                reserveSeat("5-6인실 스터디룸", 35000, 3);
                break;
            case 3:
                reserveSeat("8-10인실 스터디룸", 50000, 2);
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        }
    }

    public static void main(String[] args) {
        run();
    }
}
