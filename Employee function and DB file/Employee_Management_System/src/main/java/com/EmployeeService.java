/**
 * 
 */
package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Employee;

/**
 * @author HP
 *
 */
@Path("/Employee")
public class EmployeeService{
  
	Employee Obj = new Employee();

	@GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
	public String readEmployee(){
 
		return Obj.readEmployee();
 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("name") String EmpName,@FormParam("address") String EmpType,@FormParam("nic") String EmpPhone,@FormParam("position") String EmpEmail,@FormParam("password") String EmpPassword) {
		
		String output = Obj.insertEmployee(EmpName, EmpType, EmpPhone, EmpEmail, EmpPassword);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String EmployeeData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(EmployeeData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = Object.get("ID").getAsString();
	 String EmpName = Object.get("name").getAsString();
	 String EmpType = Object.get("address").getAsString();
	 String EmpPhone = Object.get("nic").getAsString();
	 String EmpEmail = Object.get("position").getAsString();
	 String EmpPassword = Object.get("password").getAsString();
	 String output = Obj.updateEmployee(ID, EmpName, EmpType, EmpPhone, EmpEmail, EmpPassword);
	
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String EmployeeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(EmployeeData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = Obj.deleteEmployee(ID);
	return output;
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String SearchEmployee(String EmployeeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(EmployeeData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String NAME = doc.select("NAME").text();
	 String output = Obj.SearchEmployee(NAME);
	return output;
	}



}

