package util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dto.RowDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCall {
	
	private static String run(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
	      .url(url)
	      .build();

	  try (Response response = client.newCall(request).execute()) {
	    return response.body().string();
	  }
	}
	
	public static ArrayList<RowDTO> apiCall() throws IOException {
		
		String url = "http://openapi.seoul.go.kr:8088/71546d4870736b6137384a4b46656d/json/TbPublicWifiInfo";
		
		Gson gson = new Gson();
		String st = run(url + "/1/5");
		
		JsonElement je = JsonParser.parseString(st);
		
		String strCount = je.getAsJsonObject().get("TbPublicWifiInfo")
				.getAsJsonObject().get("list_total_count").getAsString();
		
		int count = Integer.valueOf(strCount);
		ArrayList<RowDTO> dtoList = new ArrayList<>();
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
			
			st = run(url+ "/" + startIdx + "/" + endIdx);
			je = JsonParser.parseString(st);
			JsonArray ar = (JsonArray) je.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("row");
			
			
			for (JsonElement e : ar) {
				RowDTO dto = gson.fromJson(e, RowDTO.class);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
}
