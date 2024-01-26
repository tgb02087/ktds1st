package org.example;

import java.util.Scanner;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static final String[] EXTRAVERTED = {"E", "I"};
    private static final String[] SENSING = {"S", "N"};
    private static final String[] THINKING = {"T", "F"};
    private static final String[] JUDGING = {"J", "P"};

    private static String computerMBTI;
    
    public static void main(String[] args) {
        computerMBTI = "";
    
        while (true) {
            System.out.println("새 게임 시작! ");
            computerMBTI = createMBTI();
            int idx = 0;
            while (true) {
                System.out.println(idx+"턴~~");
                if(GuessComputerMBTI()){
                    if (exit()) return;
                    break;
                }
                if(GuessUserMBTI()){
                    if (exit()) return;
                    break;
                }
            }
        }


    }
    
    private static boolean exit() {
        System.out.println("---------------------");
        System.out.println("다시하려면 1, 종료하시려면 아무 키나 눌러주세요.");
        int cmd = sc.nextInt();
        sc.nextLine();
        if(cmd==1) return false;
        return true;
    }
    
    private static boolean GuessUserMBTI() {
        String expected = createMBTI();
        System.out.println("컴퓨터가 예상한 MBTI : " + expected);
        System.out.print("답변: ");
        String temp = sc.nextLine();
        if(!temp.contains(",")) throw new IllegalArgumentException();
    
        StringTokenizer st = new StringTokenizer(temp, ",");
    
        int yes = Integer.parseInt(st.nextToken());
        int no = Integer.parseInt(st.nextToken());
        if(yes==4){
            System.out.println("정답입니다.! 컴퓨터가 승리하였습니다. 게임 종료");
            return true;
        }
        return false;
    }
    
    private static boolean GuessComputerMBTI() {
        System.out.print("MBTI 예측: ");
        String input = sc.nextLine();
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (input.charAt(i) == computerMBTI.charAt(i)) cnt++;
        }
        if (cnt == 4) {
            System.out.println("정답입니다.! 내가 승리하였습니다. 게임 종료");
            return true;
        }
        System.out.println(cnt + "개는 맞고 " + (4 - cnt) + "개는 틀려요");
        return false;
    }
    
    public static String createMBTI() {
        Random random = new Random();
        String s1 = getRandomElement(EXTRAVERTED, random);
        String s2 = getRandomElement(SENSING, random);
        String s3 = getRandomElement(THINKING, random);
        String s4 = getRandomElement(JUDGING, random);
        return s1 + s2 + s3 + s4;
    }
    
    public static String getRandomElement(String[] arrayList, Random random) {
        int idx = random.nextInt(2);
        return arrayList[idx];
    }

}

