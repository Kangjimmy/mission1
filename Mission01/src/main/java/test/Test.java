package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.*;

import dto.RowDTO;

public class Test {

	private String run(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
	      .url(url)
	      .build();

	  try (Response response = client.newCall(request).execute()) {
	    return response.body().string();
	  }
	}
	
   
	public static void main(String[] args) throws IOException, ParseException {
		
		Test test = new Test();
		long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
		String url = "http://openapi.seoul.go.kr:8088/71546d4870736b6137384a4b46656d/json/TbPublicWifiInfo/1/5";
		
		Gson gson = new Gson();
		String st = test.run(url);
		//System.out.println(st);
		
		JsonElement je = JsonParser.parseString(st);
		//System.out.println(je);
		String strCount = je.getAsJsonObject().get("TbPublicWifiInfo")
				.getAsJsonObject().get("list_total_count").getAsString();
		
		int count = Integer.valueOf(strCount);
		ArrayList<RowDTO> list = new ArrayList<>();
		int startIdx = 1;
		int endIdx = 0;
		
		while (count > -1) {
			if (count - 1000 > -1) {
				count -= 1000;
				startIdx = endIdx+1;
				endIdx += 1000;
			} else {
				startIdx = endIdx+1;
				endIdx += count;
				count = -1;
			}
			
			//System.out.println("뽑는중 : " + startIdx + " ~ " + endIdx);
			st = test.run(url+ "/" + startIdx + "/" + endIdx);
			je = JsonParser.parseString(st);
			JsonArray ar = (JsonArray) je.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("row");
			
			
			for (JsonElement e : ar) {
				RowDTO dto = gson.fromJson(e, RowDTO.class);
				list.add(dto);
				System.out.println(dto.toString());
				
				Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dto.getWORK_DTTM());
				System.out.println("date:" + date);
				String dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);
			}
			count = -1;
		}
		
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime);
		System.out.println(secDiffTime);
	}
}
