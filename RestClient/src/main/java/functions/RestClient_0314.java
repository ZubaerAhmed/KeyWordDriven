package functions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestClient_0314 {
	
	public CloseableHttpResponse get(String url,HashMap<String,String>headers) throws ClientProtocolException, IOException{
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		for(Entry<String,String> entry : headers.entrySet()){
			request.addHeader(entry.getKey(), entry.getValue());
		} 
		
		CloseableHttpResponse response = client.execute(request);
		return response;
	}
	public CloseableHttpResponse post(String url,HashMap<String,String>headers) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		
		for(Entry<String,String> entry : headers.entrySet()){
			request.addHeader(entry.getKey(), entry.getValue());
		}
//		request.setHeader((Header) header());  
		
		request.setEntity(new StringEntity(payLoad()));
		CloseableHttpResponse response = client.execute(request);
		return response;
	} 
	
	
	
	public HashMap<String, String> header() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-type", "application/json");
		map.put("username", "zubaer");
		map.put("password", "Test1234");
		map.put("Auth Tocken", "abcd1234");
		return map;
	}
	
	public String payLoad() throws JsonProcessingException{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("name", "Zubaer");
		map.put("job", "developer");
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(map);
		return content;
	}

	public JSONObject body() throws ClientProtocolException, IOException{
		RestClient_0314 rest = new RestClient_0314();
		String url = "https://reqres.in/api/users";
		CloseableHttpResponse response =rest.get(url, rest.header());
		String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		Gson pretty = new GsonBuilder().setPrettyPrinting().create();
		String prettyOutput = pretty.toJson(responseJson);
		//System.out.println(prettyOutput); 
		return responseJson; 
	}
}
