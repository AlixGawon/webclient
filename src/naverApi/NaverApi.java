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

    // 네이버 검색 API 예제 - blog 검색


    public class NaverApi {


        //네이버 검색 Api. 이 밑에 JSON 이용해야 함.
        public static void main(String[] args) {

            String keyword = "커리";
            String json = NaverApiService.searchAndReturnJson(keyword);
            System.out.println(json);
            JSONObject obj = new JSONObject(json);

//            JSONArray list = (JSONArray)obj.get("items");
//            JSONObject theThird = (JSONObject)list.get(2);
//            theThird.getString("bloggerlink");

           JSONArray list = obj.getJSONArray("items");
           String result = list.getJSONObject(2).getString("bloggerlink");

            System.out.println(result);

            JSONArray list2 = obj.getJSONArray("items");

            //JSONArray는 FOR-EACH 문을 지원하지 않는다.

            for (int i = 0; i < list2.length(); i++) { //list2에 있는 배열의 length만큼 돌리는 것
                System.out.println(
                        list2.getJSONObject(i).getString("bloggerlink")
                );
            }



        }
    }

