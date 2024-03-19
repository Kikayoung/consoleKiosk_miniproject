package studycafe_user_kiosk;

import java.util.Scanner;

public class Menu {
    private Scanner sc;
    public Menu(){
        sc = new Scanner(System.in);
    }
    public void MainMenu(){
        System.out.println("1. 신규 가입");
        System.out.println("2. 로그인");
        System.out.println("3. 좌석 상태 실시간 업데이트");
//        System.out.println("4. 스터디 카페 소개");
//        System.out.println("5. 문의 게시판");
//        System.out.println("6. 환불 서비스");
        System.out.print("메뉴 선택: ");

        int choice = sc.nextInt();
        switch (choice){
            case 1:
                SignUp();
                break;
            case 2:
                LogIn();
                break;
            case 3 :
                UpdateSeat();
                break;
//            case 4:
//                ImformationCafe();
//                break;
//            case 5:
//                NocticeBoard();
//                break;
//            case 6 :
//                RefundService();
//                break;
            default:
                System.out.println("잘못된 메뉴 선택");

        }

    }

}
