package com.wipro.rmgyantra.scripts;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.sql.SQLException;

import com.wipro.rmgyantra.genericlib.BaseClass;
import com.wipro.rmgyantra.genericlib.IEndPoints;
import com.wipro.rmgyantra.genericlib.JavaUtility;
import com.wipro.rmgyantra.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddSingleProjectWithOnGoingstatus extends BaseClass{
	@Test
	public void goingStatus() throws SQLException {
		String projName="WWWApi"+JavaUtility.getRandomNum();
		Project pObj=new Project("Bharani",projName,10,"Ongoing");
		
		Response resp = given()
		.contentType(ContentType.JSON)
		.body(pObj)
		.post("http://localhost:8084/addProject");
		
		resp.then().log().all()
		.contentType(ContentType.JSON)
		.assertThat().statusCode(201);
		
		//Capture Project Name and status
		String apiProjectName = resp.jsonPath().get("ProjectName");
		String apiStatus=resp.jsonPath().get("status");
		
		String projectNameresult = dblib.executeQueryAndGetData("select * from project",4,apiProjectName);
		String projectStatusresult=dblib.executeQueryAndGetData("select * from project",5,apiStatus);
		
		
		
		
	}

}
