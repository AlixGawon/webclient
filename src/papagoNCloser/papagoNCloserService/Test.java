package papagoNCloser.papagoNCloserService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) throws Exception {

        String clientId = "UAn9Yzxr9pvMwDgwbuC4";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "wVle4V6Y5w";//애플리케이션 클라이언트 시크릿값";
        try {

            BufferedReader fbr = new BufferedReader(new FileReader("src\\closers.txt")); //변수 겹치니까 fbr이라고 함
            String line = "";//하나 미리 만들어 두기!

            while ((line = fbr.readLine()) != null) {// 읽다가 공백이 나올 경우를 설정. 여기 밑에 파파고 서비스가 들어가야 함!그래야 계속 돌면서 번역 됨.

                if (line.trim().equals("")){//trim : 앞 뒤를 잘라줌.
                    continue;
                }

                String text = URLEncoder.encode(line, "UTF-8"); //번역할 것이 line 안에 들어있음!
                String apiURL = "https://openapi.naver.com/v1/language/translate";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                String postParams = "source=en&target=ko&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }

                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                //결과 JSON을 파싱하여 콘솔에 출력한다.

                String jsonData = response.toString();

                JSONObject obj = new JSONObject(jsonData);

//                    JSONObject msgObj = obj.getJSONObject("message");//메세지라는 키의 값에 해당되는 것이 object
//                    JSONObject resObj = msgObj.getJSONObject("result");
//                    String result = resObj.getString("translatedText");


                String result =  obj.getJSONObject("message").getJSONObject("result").getString("translatedText");//chaining으로 들어가는 것이 일반적임. 계속 파고들어가서 찾아내는 것
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
