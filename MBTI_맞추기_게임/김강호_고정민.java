import java.util.*;

public class main2 {
    static char[][] mbti = new char[][]{{'E', 'I'}, {'S', 'N'}, {'F', 'T'}, {'P', 'J'}};
    static List<String> possibleMBTIs = new ArrayList<>();
    static Map<String, Boolean> memo = new HashMap<>();
    static String lastCpuGuess;

    public static void main(String[] args) {
        initPossibleMBTIs();
        String cpu = init_cpu();
        //System.out.println(cpu);
        boolean game_flag = false;
        while (true) {
            game_flag = myturn(cpu.toCharArray());
            if (!game_flag) {
                lastCpuGuess = cpu_input();
                cputurn(lastCpuGuess);
                game_flag = cputurn_answer();
            }

            if (game_flag) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1)재시작 | 2)종료");
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    game_flag = false;
                    initPossibleMBTIs();
                    cpu = init_cpu();
                } else if (input.equals("2")) {
                    return;
                }
            }
        }
    }

    public static String init_cpu() {
        char[] cpu = new char[4];
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            cpu[i] = mbti[i][random.nextInt(2)];
        }
        return String.copyValueOf(cpu);
    }
    public static void cputurn(String guess) {
        System.out.print("컴퓨터가 예상한 MBTI : ");
        System.out.println(guess);
    }

    public static String cpu_input() {
        Random random = new Random();
        String cpu = possibleMBTIs.get(random.nextInt(possibleMBTIs.size()));
        while (memo.containsKey(cpu)) {
            cpu = possibleMBTIs.get(random.nextInt(possibleMBTIs.size()));
        }
        memo.put(cpu, true);
        return cpu;
    }
    public static boolean myturn(char[] cpu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("MBTI 예측 : ");
        String input = scanner.nextLine();
        check_mbti(input.toCharArray());
        int o = check_mbti(cpu, input.toCharArray());
        if(print_correct(o)) return true;
        else return false;
    }

    public static int check_mbti(char[] target, char[] input) {
        int o = 0;
        for(int i=0; i<4; i++) {
            if(target[i]==input[i]) o++;
        }
        return o;
    }

    public static boolean print_correct(int o) {
        System.out.println(o+"개는 맞고 "+(4-o)+"개는 틀려요");
        if(o==4) {
            System.out.println("정답입니다! 내가 승리하였습니다. 게임 종료");
            return true;
        }
        return false;
    }

    public static void check_mbti(char[] input) {
        int cnt = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<2; j++) {
                if(input[i] == mbti[i][j]) cnt++;
            }
        }
        if(cnt!=4) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean cputurn_answer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("답변 : ");
        String[] input = scanner.nextLine().split(",");
        int o = Integer.parseInt(input[0].trim());
        int x = Integer.parseInt(input[1].trim());
        filterPossibleMBTIs(lastCpuGuess, o, x);
        if (o == 4) {
            System.out.println("정답입니다! 컴퓨터가 승리했습니다.");
            return true;
        }
        return false;
    }

    public static void initPossibleMBTIs() {
        possibleMBTIs.clear();
        for (char first : mbti[0]) {
            for (char second : mbti[1]) {
                for (char third : mbti[2]) {
                    for (char fourth : mbti[3]) {
                        possibleMBTIs.add("" + first + second + third + fourth);
                    }
                }
            }
        }
    }
    public static void filterPossibleMBTIs(String lastGuess, int correct, int incorrect) {
        List<String> newPossibleMBTIs = new ArrayList<>();
        for (String mbti : possibleMBTIs) {
            int matchCount = 0;
            for (int i = 0; i < 4; i++) {
                if (mbti.charAt(i) == lastGuess.charAt(i)) matchCount++;
            }
            if (matchCount == correct) {
                newPossibleMBTIs.add(mbti);
            }
        }
        possibleMBTIs = newPossibleMBTIs;
    }
}
