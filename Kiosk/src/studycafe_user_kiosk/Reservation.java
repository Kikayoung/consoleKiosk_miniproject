import java.util.Scanner;

public class Reservation {
    private Scanner sc;
    private boolean[] seats;

    public Reservation(int numberOfSeats) {
        sc = new Scanner(System.in);
        seats = new boolean[numberOfSeats];
    }

    public void   () {
        System.out.println("좌석 예약을 진행합니다.");

        // 예약 정보 입력 받기
        System.out.print("예약할 좌석 번호를 입력하세요: ");
        int seatNumber = sc.nextInt();

        // 예약 확인 및 처리
        if (checkSeatAvailability(seatNumber)) {
            reserveSeat(seatNumber);
            System.out.println("좌석 예약이 완료되었습니다.");
        } else {
            System.out.println("이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.");
        }
    }

    private boolean checkSeatAvailability(int seatNumber) {
        // 좌석 예약 가능 여부를 확인하는 로직 구현
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("유효하지 않은 좌석 번호입니다.");
            return false;
        }

        if (seats[seatNumber - 1]) {
            return false;
        } else {
            return true;
        }
    }

    private void reserveSeat(int seatNumber) {
        // 좌석 예약을 처리하는 로직 구현
        seats[seatNumber - 1] = true;
    }
}
