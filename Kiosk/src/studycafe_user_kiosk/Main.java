package studycafe_user_kiosk;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void menuView(){

        Scanner sc = new Scanner(System.in);
        int choice = -1;
        MembershipSystem m = new MembershipSystem();

        while (choice != 0) {
            try {
                System.out.println("\n=== 메뉴 ===");
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.println("3. 카페 소개");
                System.out.println("0. 종료");
                System.out.print("선택: ");

                choice = sc.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        m.login();
                        break;
                    case 2:
                        m.signUp();
                        break;
                    case 3:
                        introduceCafe();
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.next();
            }
        }
    }

    
    public static void introduceCafe() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
    
        while (choice != 0) {
            System.out.println("***** 카페 소개 *****");
            System.out.println("우리 카페는 스터디카페입니다. \n");
            System.out.println("<** 가격 안내 **>\n");
            System.out.println("1. 1인실 독서실");
            System.out.println("    - 시간당 5,000원\n");
            System.out.println("2. 카페형 좌석");
            System.out.println("    - 시간당 2,000원\n");
            System.out.println("3. 스터디룸");
            System.out.println("    - 시간당 10,000원\n");
            System.out.println("");
            System.out.println("0. 뒤로 가기.");
    
            try {
                System.out.print("선택: ");
                choice = sc.nextInt();
    
                if (choice == 0) {
                    System.out.println("메뉴로 돌아갑니다.");
                    return; 
                } else {
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.next();
            }
        }
    }
    

    public static void main(String[] args) {
        menuView();
    }
}