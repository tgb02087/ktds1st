import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class MBTIService {

    // 에측한 MBTI가 상대방의 MBTI와 일치하는지 확인하는 메소드
    public int isMatched(String computerMbti, String predictMbti) {

        int correctCount = 0;

        for (int i = 0; i < predictMbti.length(); i++) {
            if (predictMbti.charAt(i) == computerMbti.charAt(i)) {
                correctCount++;
            }
        }

        return correctCount;
    }

    // 프린트 메소드
    public boolean printResult(int correctCount) {
        if (correctCount == 4) {
            System.out.println("정답입니다! 내가 승리하였습니다. 게임 종료.");
            return true;
        }
        if (correctCount == 0) {
            System.out.println("전부 틀려요");
            return false;
        }
        System.out.println(correctCount + "개는 맞고 " + (4 - correctCount) + "개는 틀려요.");
        return false;
    }

    // 컴퓨터가 나의 MBTI를 맞춘 갯수를 보고 다음 MBTI를 예측하는 메소드
    public String predictNextMbti(ArrayList<String> preAnswerList, int correctCount, String myMbti) {
        String nextMbti = "";

        if (correctCount == 4) {
            System.out.println("정답입니다! 컴퓨터가 승리하였습니다. 게임 종료.");
            System.exit(0);
        }
        if (correctCount == 0) {
            System.out.println("전부 틀려요");
            System.exit(0);
        }

        if (correctCount == 3) {
            int randomIndex = (int) (Math.random() * 4);
            nextMbti = myMbti.substring(0, randomIndex) + myMbti.substring(randomIndex + 1);
        } else if (correctCount == 2) {
            int randomIndex1 = (int) (Math.random() * 4);
            int randomIndex2 = (int) (Math.random() * 4);
            while (randomIndex1 == randomIndex2) {
                randomIndex2 = (int) (Math.random() * 4);
            }
            nextMbti = myMbti.substring(0, randomIndex1) + myMbti.substring(randomIndex1 + 1, randomIndex2) + myMbti.substring(randomIndex2 + 1);
        } else if (correctCount == 1) {
            int randomIndex1 = (int) (Math.random() * 4);
            int randomIndex2 = (int) (Math.random() * 4);
            int randomIndex3 = (int) (Math.random() * 4);
            while (randomIndex1 == randomIndex2) {
                randomIndex2 = (int) (Math.random() * 4);
            }
            while (randomIndex1 == randomIndex3 || randomIndex2 == randomIndex3) {
                randomIndex3 = (int) (Math.random() * 4);
            }
            nextMbti = myMbti.substring(0, randomIndex1) + myMbti.substring(randomIndex1 + 1, randomIndex2) + myMbti.substring(randomIndex2 + 1, randomIndex3) + myMbti.substring(randomIndex3 + 1);
        }


        for (int i = 0; i < preAnswerList.size(); i++) {
            if (preAnswerList.get(i).equals(nextMbti)) {
                nextMbti = predictNextMbti(preAnswerList, correctCount, myMbti);
            }
        }

        System.out.println("다음 MBTI : " + nextMbti);




        return nextMbti;
    }




}
