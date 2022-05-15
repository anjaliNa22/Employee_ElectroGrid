/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author HP
 *
 */
public class Employee {

	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	//INSERT
	public String insertEmployee(String name, String emptype, String empphone, String empemail,String passwords)
	 {
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `employee`(`ID`, `name`, `address`, `nic`, `position`, `password`)  VALUES  (?, ?, ?,?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, name);
	        preparedStmt.setString(3,emptype );
	        preparedStmt.setString(4, empphone);
	        preparedStmt.setString(5, empemail);
	        preparedStmt.setString(6, passwords);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        output = "Inserted successfully";
	
		}catch (Exception e){
	 
			output = "Error while inserting the item.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	//READ
	public String readEmployee(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>Employee Name</th><th>Employee Address</th>" +
	                "<th>Employee NIC </th>" +
	                "<th>Employee Position </th>" +
	                "<th>Employee Password</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT * FROM employee";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String ID=rs.getString("ID");
	        	String EmpName = rs.getString("name");
	            String EmpType = rs.getString("address");
	            String EmpPhone = rs.getString("nic");
	            String EmpEmail = rs.getString("position");
	            String EmpPassword = rs.getString("password");
	            
	            // Add into the html table
	
	            output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + ID + "'>"
	            		 + EmpName + "</td>";
	            output += "<td>" + EmpType + "</td>";
	            output += "<td>" + EmpPhone + "</td>";
	            output += "<td>" + EmpEmail + "</td>";
	            output += "<td>" + EmpPassword + "</td>";
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
	        			 + "<td><form method='post' action='empReview.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	        			 + "<input name='hidItemIDDelete' type='hidden' value='" + ID + "'> </form></td></tr>";
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the items.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	//UPDATE
	public String updateEmployee(String ID,String name, String emptype, String empphone, String empemail,String passwords){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `employee` SET `name`=?,`address`=?,`nic`=?,`position`=?,`password`=? WHERE `ID`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, name);
	        preparedStmt.setString(2, emptype);
	        preparedStmt.setString(3, empphone);
	        preparedStmt.setString(4, empemail);
	        preparedStmt.setString(5, passwords);
	        preparedStmt.setInt(6, Integer.parseInt(ID));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        output = "Updated successfully";
	 
		}catch (Exception e){
	 
			output = "Error while updating the item.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	//DELETE
	public String deleteEmployee(String ID){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `employee` WHERE `ID`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(ID));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         output = "Deleted successfully";
	
		}catch (Exception e){
			
	 
			output = "Error while deleting the item.";
	        System.err.println(e.getMessage());
	
		}
	
		return output;
	 }

	public String SearchEmployee(String nAME) {
		String output = "";
		 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>Employee Name</th><th>Employee Address</th>" +
	                "<th>Employee NIC</th>" +
	                "<th>Employee Position</th>" +
	                "<th>Employee Password</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT * FROM employee WHERE `name` ='"+nAME+"';";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	   
	       
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String ID=rs.getString("ID");
	        	String EmpName = rs.getString("name");
	            String EmpType = rs.getString("address");
	            String EmpPhone = rs.getString("nic");
	            String EmpEmail = rs.getString("poistion");
	            String EmpPassword = rs.getString("password");
	            
	            // Add into the html table
	
	            output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + ID + "'>"
	            		 + EmpName + "</td>";
	            output += "<td>" + EmpType + "</td>";
	            output += "<td>" + EmpPhone + "</td>";
	            output += "<td>" + EmpEmail + "</td>";
	            output += "<td>" + EmpPassword + "</td>";
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
	        			 + "<td><form method='post' action='empReview.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	        			 + "<input name='hidItemIDDelete' type='hidden' value='" + ID + "'> </form></td></tr>";
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the items.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	} 
}
