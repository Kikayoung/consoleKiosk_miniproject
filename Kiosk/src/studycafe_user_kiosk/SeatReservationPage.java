package studycafe_user_kiosk;

import java.util.Scanner;

public class SeatReservationPage {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== 좌석 예약 ===");
            System.out.println("1. 1인실 좌석");
            System.out.println("2. 카페형 좌석");
            System.out.println("3. 스터디룸 예약");
            System.out.println("0. 뒤로 가기");
            System.out.print("선택: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    SoloSeats();
                    break;
                case 2:
                    CafeSeat();
                    break;
                case 3:
                    StudyRoom();
                    break;
                case 0:
                    System.out.println("이전 페이지로 돌아갑니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    public static void SoloSeats() {
        System.out.println("*****1인실 좌석 예약현황*****");
        System.out.println("독서실 같이 조용한 1인 공간의 좌석입니다. ");
    }

    public static void CafeSeat() {
        System.out.println("*****카페형 좌석 예약현황*****");
    }
    public static void StudyRoom() {
        System.out.println("*****스터디룸 예약*****");
    }
}
