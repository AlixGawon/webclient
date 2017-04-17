import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class URLDemo {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        try {
            URL url = new URL ("http://www.naver.com");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();//네이버 오픈 api는 주소랑 header값 다 필요.

//            System.out.println(contentType);
//
//            String encoding = conn.getContentEncoding();//암호화이다 생각하기.
//            System.out.println(encoding);//하지만 애초에 네이버 측에서 정보 안 줌. 그래서 null
//
//            System.out.println(url.getProtocol());//쓰고 있는 프로토콜이 무엇인지 알아보는 메서드.
//
//            int contentLength = conn.getContentLength();//response하는 콘텐츠가 몇 개인지 알아보는 메서드.
//            System.out.println(contentLength);//네이버는 contentlength를 -1로 줌. 의도적임.


            InputStream is = url.openStream();//stream은 8 bit BufferedReader은 16 bit
            InputStreamReader isr = new InputStreamReader(is);//8 비트 짜리를 16비트 짜리로 변환
            BufferedReader br = new BufferedReader(isr);//BufferedReder로 파일 잘 읽으려고 준비.

            String line = "";




            while ((line = br.readLine()) != null) { //공백 처리.
                //System.out.println(line);
                String[] splitted = line.split(" ");


                for (String e : splitted) {
                    list.add(e);
                }

                //list.add(splitted[0]);


            }

//

        } catch (IOException e) {//MalformedURLException는
            // 도메인(naver.com)이 아니라 프로토콜(http)이 잘못되었을 때 에러가 남.

            e.printStackTrace();
        }


        URLDemoService service = new URLDemoService();
        int countNum = service.amount(list); //이 포멧 외우기!
        System.out.println(countNum);

    }
}
