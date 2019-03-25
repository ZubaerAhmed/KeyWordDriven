package sprint_001;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class RestAssured_0315_Test {

	ExtentReports reports = new ExtentReports(System.clearProperty("user.dir")+"\\report.html", true);
	Logger logger = LogManager.getLogger(RestAssured_92_Test.class);
	
	@Test
	public void get(){
		
		given()
		.contentType(ContentType.JSON)
		.authentication().basic(baseURI, "")
		.when().get("https://reqres.in/api/users")
		.andReturn().statusCode();
		//.prettyPrint();
//---accept---XML-------------
//		http://www.google.com/ig/api?weather=Seattle+WA	
		String body = given()
		.accept(ContentType.JSON)
		//.param("city", "Dhaka") 
		.when().get("http://restapi.demoqa.com/utilities/weather/city/dhaka")
		.thenReturn().asString();
		System.out.println(body);
/*		
		given()
		.pathParams("owner", "eugenp", "repo", "tutorials")
	     .when().get();
		
		int perPage = 20;
		    given()
		    .queryParam("q", "john").queryParam("per_page",perPage)
		    .when().get("/search/users")
		    .then()
		    .body("items.size()", is(perPage));  

		//Adding cookies
		given().cookie("session_id", "1234")
		.when().get("https://reqres.in/api/users")
	      .then().statusCode(200);
*/		    
		Response response = given()
		.headers(headers())
		.when().get("https://reqres.in/api/users");
		//response.prettyPrint();
		
		//Adding header
		given().header("User-Agent", "MyAppName")
		.when().get("https://reqres.in/api/users")
	      .then().statusCode(200);
				
		response.statusCode();
		
		Cookies cookies = given()
				.when().get("https://reqres.in/api/users")
				.getDetailedCookies();

		for(Cookie cookie : cookies){
			System.out.println(cookie.getDomain()); 
			System.out.println(cookie.getName());
		}

		Map<String, String> myCookies = given()
				.when().get("https://reqres.in/api/users")
				.cookies();
		for(String cookie : myCookies.keySet()){
			System.out.println(cookie); 
		}

		
		Headers headers = response.getHeaders();
		for(Header head : headers){
			//System.out.println(head); 
		}
	}
	
//	@Test
	public void post(){
		reports.addSystemInfo("Name","Zubaer");
		ExtentTest test = reports.startTest("RestAssured_92_Test");
		
		Response response = given()
		.headers(headers())
		.body(payload())
		.when().post("https://reqres.in/api/users")
		.andReturn();
		logger.info("status code--> "+response.statusCode());
		response.prettyPrint();
		 
		Response response2 = given()
		.headers(headers())
		.body(update())
		.when().put("https://reqres.in/api/users/2")
		.andReturn();
		response2.prettyPrint();
		logger.info("status code--> "+response2.statusCode());
		test.log(LogStatus.FAIL,"message");
		
		String name = response2.path("name");
		logger.info("name for PUT--> "+name); 
		reports.endTest(test);
		reports.flush();
		
	}
	public HashMap<String,String> headers(){
		HashMap<String,String> map = new HashMap();
		map.put("Content-Type", "application/json");
		map.put("username", "zubaer");
		map.put("password", "Test123");
		map.put("Auth Tocken", "12345");
		return map;
	}
	public HashMap<String,String> payload(){
		HashMap<String,String> payload = new HashMap();
		payload.put("name", "Novaira");
		payload.put("job", "java");
		return payload;
	}
	public HashMap<String,String> update(){
		HashMap<String,String> update = new HashMap();
		update.put("name", "Farzana");
		return update;
	}
}
