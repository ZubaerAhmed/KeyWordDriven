package sprint_001;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssured_92_Test {
	
	ExtentReports reports = new ExtentReports(System.clearProperty("user.dir")+"\\report.html", true);
	Logger logger = LogManager.getLogger(RestAssured_92_Test.class);

	@Test(priority = 1, description = "TC_001,GET")
	public void get() {

		reports.addSystemInfo("Name","Zubaer");
		ExtentTest test = reports.startTest("RestAssured_92_Test");
		
		Response response =
		given().contentType(ContentType.JSON)
		.when().get("https://reqres.in/api/users")
		.andReturn();
		//response.prettyPrint();
		Reporter.log("pretty printing ok");
		logger.info("pretty printing ok");
		test.log(LogStatus.PASS, "pretty printing ok");
		
		int statuscode = response
				.then().assertThat()
				.statusCode(200)
				.extract().statusCode();
		logger.info("Status code is-->"+statuscode);
		test.log(LogStatus.PASS, "Status code is-->"+statuscode); 
		Reporter.log("Status code is-->"+statuscode);  
		
		String string = response.path("data[0].last_name");
		logger.info("name--> "+ string);   
		 
		reports.endTest(test);
		reports.flush();
	}
	
	@Test
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
		HashMap<String,String> headers = new HashMap();
		headers.put("Content-Type", "application/json");
		headers.put("username", "test1");
		headers.put("password", "Test1234");
		headers.put("Auth Tocken", "1234");
		return headers;
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
