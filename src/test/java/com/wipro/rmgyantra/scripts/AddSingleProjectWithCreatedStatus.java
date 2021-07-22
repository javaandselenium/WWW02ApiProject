package com.wipro.rmgyantra.scripts;
import static io.restassured.RestAssured.*;

import java.sql.SQLException;

import org.testng.annotations.Test;

import com.wipro.rmgyantra.genericlib.BaseClass;
import com.wipro.rmgyantra.genericlib.IEndPoints;
import com.wipro.rmgyantra.genericlib.JavaUtility;
import com.wipro.rmgyantra.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddSingleProjectWithCreatedStatus extends BaseClass{
	@Test
	public void createdStauts() throws SQLException {
	
		String projectName="Api"+JavaUtility.getRandomNum();
		Project pObj=new Project("John", projectName,5,"Created");
		
	Response resp = given()
	.contentType(ContentType.JSON)
	.body(pObj)
	.post(IEndPoints.addSingleProjectWithCreatedStatus);
	resp.then().log().all()
	.assertThat().statusCode(201)
	.contentType(ContentType.JSON);
	
	//Capture Project Name and status
	String apiProjectName = resp.jsonPath().get("ProjectName");
	String apiStatus=resp.jsonPath().get("status");
	
String projectNameresult = dblib.executeQueryAndGetData("select * from project",4,apiProjectName);
String projectStatusresult=dblib.executeQueryAndGetData("select * from project",5,apiStatus);
	
	
	
	

}
}