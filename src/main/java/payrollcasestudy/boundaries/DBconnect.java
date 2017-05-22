package payrollcasestudy.boundaries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import payrollcasestudy.entities.Employee;

public class DBconnect implements Repository{
	
	private Connection connection;
	private Statement statement;
	private ResultSet result;
	
	public DBconnect(){
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arquidb","root","");
			statement = connection.createStatement();
			
			
		}
		catch(Exception exception){
		   System.out.println("Error:"+exception);
		}
	}
	/*public void getemployees(){
      try{
		    String 	query = "select * from employee";
		    result = statement.executeQuery(query);
		    System.out.println("Records from database");
		    while(result.next()){
		    	String name= result.getString("name");
		    	String id = result.getString("id");
		    	String adress = result.getString("adress");
		    	System.out.println("Name: "+name+"adress"+adress+"id"+id);
		    }
		    
		}
		catch(Exception ex){
		   System.out.println("Error:"+ex);
		}
	}*/
	@Override
	 	public  List<Employee> getAllEmployees()
	     {
		        List<Employee> employees = new ArrayList<>();	 	
		        try{
	 			String 	query = "SELECT * FROM employee";
			    result = statement.executeQuery(query);
	 			while(result.next()){
	 				Employee employee = new Employee(Integer.parseInt(result.getString("id")),result.getString("name"),result.getString("adress"));
	 				employees.add(employee);
	 			}
	 			return employees;
	 		}catch (Exception ex){
	 			System.out.println("Error:"+ex);
	 			return employees;
	 		}
	     }

	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addEmployee(int employeeId, Employee employee) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Employee getUnionMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addUnionMember(int memberId, Employee employee) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteUnionMember(int memberId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Set<Integer> getAllEmployeeIds() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
