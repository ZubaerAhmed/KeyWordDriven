package restclientTest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import functions.RestClient_0314;

public class RestClient_0314_Test {

	RestClient_0314 rest = new RestClient_0314();
	String url = "https://reqres.in/api/users";
	
	@Test
	public void getBody() throws ClientProtocolException, IOException{
		CloseableHttpResponse response =rest.get(url, rest.header());
		int statuscode = response.getStatusLine().getStatusCode();
		System.out.println("status code GET--> "+statuscode);		
	}
	@Test
	public void validateName() throws ClientProtocolException, IOException{
		JSONObject response = rest.body();
		System.out.println("total--> "+response.get("total"));
		JSONArray data = response.getJSONArray("data");
		JSONObject dataobject = data.getJSONObject(0);
		String lastname = dataobject.getString("last_name");
		System.out.println("lastname--> "+lastname); 
	}
	@Test
	public void validatePost() throws ClientProtocolException, IOException{
		CloseableHttpResponse response = rest.post(url, rest.header());
		System.out.println("POST--> "+response.getStatusLine().getStatusCode());
		
		String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("POST name--> "+responseJson.get("name"));
	}
}
