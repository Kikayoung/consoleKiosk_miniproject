package studycafe_user_kiosk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MembershipSystem {
    public void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*****회원가입*****");
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("전화번호: ");
        String phoneNumber = sc.nextLine();
        System.out.print("비밀번호: ");
        String password = sc.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO members(name, phoneNumber, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void login() {
        Scanner sc = new Scanner(System.in);
        
        int loginAttempts = 0;
        boolean loggedIn = false;
        while (true) {
            System.out.println("*****로그인*****");
            System.out.print("전화번호: ");
            String phoneNumber = sc.nextLine();
            System.out.print("비밀번호: ");
            String password = sc.nextLine();
            

            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT name FROM members WHERE phoneNumber = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, phoneNumber);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println(rs.getString("name") + "님, 환영합니다!");
                    loggedIn = true;
                    SeatReservationPage.run();
                } else {
                    System.out.println("로그인 정보가 일치하지 않습니다.");
                    loginAttempts++;
                    if (loginAttempts >= 3) {
                    	System.out.println("로그인 실패 횟수가 3회를 초과하였습니다.");
                        System.out.println("회원이 아니십니까? 회원가입하시겠습니까? (Y/N)");
                        String choice = sc.nextLine();
                        if (choice.equalsIgnoreCase("Y")) {
                            signUp();
                        }else if (choice.equalsIgnoreCase("N")) {
                            System.out.println("이어서 로그인을 계속 시도합니다.");
                            loginAttempts = 0;
                            loggedIn = false;
                            continue;
                        }else {
                            System.out.println("잘못된 입력입니다. 메인화면으로 돌아갑니다.\n");
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
