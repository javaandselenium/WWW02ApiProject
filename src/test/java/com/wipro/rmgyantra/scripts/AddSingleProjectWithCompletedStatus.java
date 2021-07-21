package com.wipro.rmgyantra.scripts;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.testng.annotations.Test;

import com.wipro.rmgyantra.genericlib.BaseClass;
import com.wipro.rmgyantra.genericlib.JavaUtility;
import com.wipro.rmgyantra.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddSingleProjectWithCompletedStatus extends BaseClass {
@Test
public void completedStatus() throws SQLException {
String projectName="ApiTest"+JavaUtility.getRandomNum();	
Project pobj=new Project("Kavya", projectName,5,"Completed");	
	
	Response resp = given()
	.contentType(ContentType.JSON)
	.body(pobj)
	.post("http://localhost:8084/addProject");
	resp.then().log().all()
	.assertThat().statusCode(201);
	
	//Capture Project Name and status
		String apiProjectName = resp.jsonPath().get("ProjectName");
		String apiStatus=resp.jsonPath().get("status");
		
	String projectNameresult = dblib.executeQueryAndGetData("select * from project",4,apiProjectName);
	String projectStatusresult=dblib.executeQueryAndGetData("select * from project",5,apiStatus);
		
		
	
	
}
}
