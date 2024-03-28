package studycafe_user_kiosk;

import java.util.Scanner;

public class PaymentSystem {
	private int totalPrice;
	
	public PaymentSystem(int totalPrice) {
        this.totalPrice = totalPrice;
    }
	
    public void pay() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("결제 금액을 투입해주세요: ");
            int payment = sc.nextInt();

            if (payment == totalPrice) {
                System.out.println("결제가 완료되었습니다.\n");
                break;
            } else {
                System.out.println("결제에 실패하였습니다. 다시 투입해주세요.\n");
            }
        }

        try {
        	System.out.println("잠시 후 메인 화면으로 돌아갑니다.");
            Thread.sleep(3000); // 3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("메인 화면으로 돌아갑니다.");
        Main.menuView();
    }
}
