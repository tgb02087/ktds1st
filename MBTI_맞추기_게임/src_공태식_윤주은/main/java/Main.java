import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        MBTI MBTI = new MBTI();
        String computerMbti = MBTI.getMbti();
        System.out.println("MBTI : " + computerMbti);

        while (true) {
            System.out.print("MBTI 예측: ");
            String predictMbti = sc.nextLine();

            MBTIService mbtiService = new MBTIService();
            int correctCount = mbtiService.isMatched(computerMbti, predictMbti);

            boolean isWin = mbtiService.printResult(correctCount);
            if (isWin) {
                System.exit(0);
            }

            MBTI MBTI2 = new MBTI();
            String myMbti = MBTI2.getMbti();

            // 컴퓨터가 예상한 나의 MBTI 출력
            System.out.println("컴퓨터가 예상한 MBTI : " + myMbti);

            // 컴퓨터가 예상한 나의 MBTI와 실제 나의 MBTI를 비교하여 일치하는지 확인
            System.out.print("답변: ");
            String answer = sc.nextLine();

            String[] correctCountInput = answer.split(",");
            int correctCount2 = Integer.parseInt(correctCountInput[0]);
            int failCount = Integer.parseInt(correctCountInput[1]);

            MBTI.setPreAnswer(myMbti);





        }


    }
}
