import java.util.List;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class URLDemoService {
    public int amount (List<String> list){//class 쓴 것 아니니까 String


        int countNum = 0;
        for (String e : list) {

            if (!e.equals("")) { //공백이 아닐 때 단어 갯수를 더해준다.
                countNum++;
                //countNum += e.length(); 이것은 단어의 글자수를 다 더함 그래서 13만 나옴..
                System.out.println(e);
            } else {
                // 공백일 때는 노카운트
            }

        }

        return countNum;
    }
}
