package naverApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class NaverApiService {
    public static String searchAndReturnJson(String keyword) {
        String clientId = "UAn9Yzxr9pvMwDgwbuC4";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "wVle4V6Y5w";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);//서버 검증
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);//여기까지 잘 수행되면 요청 잘 된 것이라고 생각하면 됨.
            int responseCode = con.getResponseCode();//url객체 만든 순간 이미 요청 한 것이라고 생각하면 됨.
            BufferedReader br;
            if(responseCode==200) { // 정상 호출 200번대일 때 정상이라고 생각하는 것.
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));// response가져오는 것.
                // 실제로 자바 메모리로 가져오는 것.
                // InputStreamReader쓰는 이유 : 바이트 스트림을 문자 스트림으로, 문자 스트림에서 바이트 스트림으로 변환하는 스트림.
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();//StringBuffer 쓰는 이유 : 동기화를 위해서
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();//StringBuffer를 String으로 바꾸는 함수 toString이다.

//            //JSON ARRAY 사용해서 추출하기. key를 통해서 값을 가져온다.
//
//            JSONObject obj = new JSONObject(response.toString()); //객체화 시키기
//            JSONArray objarr= (JSONArray)obj.get("items"); //item에 있는 배열을 갖고오기
//
//            for(int i = 0; i < objarr.length(); i++){ //옛날 for문 방식.
//
//                String title = objarr.getJSONObject(i).getString("title");//getJSONObject(i)는 items에 여러개의 항목이 있으니까 씀.
//                // getString은 받아오는 값이 String이기 때문.
//                String link = objarr.getJSONObject(i).getString("link");
//                String description = objarr.getJSONObject(i).getString("description");
//                String bloggername = objarr.getJSONObject(i).getString("bloggername");
//                String bloggerlink = objarr.getJSONObject(i).getString("bloggerlink");
//                String postdate = objarr.getJSONObject(i).getString("postdate");
//                // System.out.print(title + link + description + bloggername + bloggerlink + postdate);
//
//            }
//
//            String result = objarr.getJSONObject(2).getString("link");
//
//            return result;

        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
}
