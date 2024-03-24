package studycafe_user_kiosk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MembershipSystem {
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*****회원가입*****");
        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("전화번호: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("*****로그인*****");
        System.out.print("전화번호: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT name FROM members WHERE phoneNumber = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("name") + "님, 환영합니다!");
                SeatReservationPage.run();
            } else {
                System.out.println("로그인 정보가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// public class MembershipSystem {
//     private String[] names = new String[100];
//     private String[] passwords = new String[100];
//     private String[] phoneNumbers = new String[100];
//     private int memberCount = 0;

//     public void signUp() {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("*****회원가입*****");
//         System.out.print("이름: ");
//         names[memberCount] = scanner.nextLine();
//         System.out.print("전화번호: ");
//         phoneNumbers[memberCount] = scanner.nextLine();
//         System.out.print("비밀번호: ");
//         passwords[memberCount] = scanner.nextLine();
//         memberCount++;
//         System.out.println("회원가입이 완료되었습니다.");
//         System.out.println("메인 화면으로 돌아갑니다.");
//     }

//     public void login() {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("*****로그인*****");
//         int loginAttempts = 0;
//         boolean loggedIn = false;

//         while (!loggedIn && loginAttempts < 5) {
//             System.out.print("전화번호: ");
//             String phoneNumber = scanner.nextLine();
//             System.out.print("비밀번호: ");
//             String password = scanner.nextLine();

//             for (int i = 0; i < memberCount; i++) {
//                 if (passwords[i].equals(password) && phoneNumbers[i].equals(phoneNumber)) {
//                     System.out.println(names[i] + "님, 스터디 카페에 오신 걸 환영합니다!");
//                     loggedIn = true;
//                     SeatReservationPage.run();
//                     break;
//                 }
//             }
//             if (!loggedIn) {
//                 loginAttempts++;
//                 if (loginAttempts < 3) {
//                     System.out.println("로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
//                 } else {
//                     System.out.println("로그인 실패 횟수가 3회를 초과하였습니다.");
//                     System.out.println("회원이 아니십니까? 회원가입하시겠습니까? (Y/N)");
//                     String choice = scanner.nextLine();
//                     if (choice.equalsIgnoreCase("Y")) {
//                         signUp();
//                         break;
//                     }
//                 }
//             }
//         }
//     }

//     public static void main (String[]args){
//         MembershipSystem system = new MembershipSystem();
//         system.signUp();
//         system.login();
//         }
//     }
