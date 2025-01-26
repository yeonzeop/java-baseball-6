package baseball;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class Application {
    static final int size=3;
    static int menu; //재시작or종료 선택 변수

    public static void main(String[] args){
        System.out.println("숫자 야구 게임을 시작합니다.");
        menu=1;
        try {
            while (menu != 2) {
                baseball2();
                if (menu != 2) {
                    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                    menu = Integer.parseInt(Console.readLine());
                    if (menu != 1 && menu != 2) throw new IllegalArgumentException("");
                }
            }
        }catch (IllegalArgumentException e){
            System.out.println("종료합니다.");
            throw e;
        }
    }
    private static void baseball2(){
        List<Character> key=new ArrayList<>();

        while(key.size()<size){
            int num=Randoms.pickNumberInRange(1,9);
            char c=(char)(num+'0');
            if(!key.contains(c))
                key.add(c);
        }

        while(true) {
            try {
                System.out.print("숫자를 입력해주세요 : ");
                String str = Console.readLine();
                if (str.length() != size) throw new IllegalArgumentException("");
                char[] chars=str.toCharArray(); //흐음
                List<Character> answer=new ArrayList<>();
                for(int i=0;i<size;i++){
                    if(answer.contains(chars[i])||chars[i]<'1'||chars[i]>'9')
                        throw new IllegalArgumentException("");
                    answer.add(chars[i]);
                }
                if(checkForGame2(key, answer)) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    break;
                }
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }

    }
    private static void baseball() {
        List<Integer> key=new ArrayList<>();

        //정답 번호 생성
        while(key.size()<size){
            int num=Randoms.pickNumberInRange(1,9);
            if(!key.contains(num))
                key.add(num);
        }

        while(true) {
            try {
                System.out.print("숫자를 입력해주세요 : ");
                String str = Console.readLine();
                if (str.length() != size) throw new IllegalArgumentException("");
                String[] strs = str.split("");
                List<Integer> answer = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Integer num = Integer.parseInt(strs[i]);
                    if (answer.contains(num) || num < 1 || num > 9) //질문
                        throw new IllegalArgumentException("");
                    answer.add(num);
                }
                if(checkForGame(key, answer)) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    break;
                }
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }

    }
    private static boolean checkForGame2(List<Character> key,List<Character>answer){
        int strike=0;
        int ball=0;

        for(int i=0;i<size;i++){
            Character num=answer.get(i);
            for(int j=0;j<size;j++){
                Character keyNum=key.get(j);
                if(keyNum.equals(num)){
                    if(j==i)
                        strike++;
                    else ball++;
                    break;
                }
            }
        }
        String str="";
        if(strike==0&&ball==0){
            System.out.println("낫싱");
            return false;
        } else if (ball>0||strike>0) {
            if(ball>0){
                str+=ball+"볼 ";
            }
            if(strike>0){
                str+=strike+"스트라이크";
            }
            System.out.println(str);
        }
        if(strike==3) return true;
        else return false;
    }
    private static boolean checkForGame(List<Integer> key, List<Integer> answer) {
        int strike=0;
        int ball=0;

        for(int i=0;i<size;i++){
            Integer num= answer.get(i);
            for(int j=0;j<size;j++){
                Integer keyNum=key.get(j);
                if(keyNum.equals(num)){
                    if(j==i)
                        strike++;
                    else ball++;
                    break;
                }
            }
        }
        String str="";
        if(strike==0&&ball==0){
            System.out.println("낫싱");
            return false;
        } else if (ball>0||strike>0) {
            if(ball>0){
                str+=ball+"볼 ";
            }
            if(strike>0){
                str+=strike+"스트라이크";
            }
            System.out.println(str);
        }
        if(strike==3) return true;
        else return false;
    }
}
