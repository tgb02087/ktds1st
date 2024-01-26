import java.util.ArrayList;

public class MBTI {
    private String mbti;
    private ArrayList<String> preList = new ArrayList<>();

    public MBTI() {
        this.mbti = generateMBTI();
    }

    public String generateMBTI() {
        String mbti = "";

        String[] eiArr = {"E", "I"};
        String[] snArr = {"S", "N"};
        String[] tfArr = {"T", "F"};
        String[] jpArr = {"J", "P"};

        int randomEI = (int) (Math.random() * 2);
        int randomSN = (int) (Math.random() * 2);
        int randomTF = (int) (Math.random() * 2);
        int randomJP = (int) (Math.random() * 2);

        mbti = eiArr[randomEI] + snArr[randomSN] + tfArr[randomTF] + jpArr[randomJP];

        return mbti;
    }


    public String getMbti() {
        return mbti;
    }

    public void setPreAnswer(String preAnswer) {
        this.preList.add(preAnswer);
    }

    public ArrayList<String> getPreList() {
        return preList;
    }
}
