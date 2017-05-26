package payrollcasestudy.boundaries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;

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
	
	@Override
	 	public  List<Employee> getAllEmployees()
	     {
		        List<Employee> employees = new ArrayList<>();	 	
		        try{
	 			String 	query = "SELECT * FROM employee";
			    result = statement.executeQuery(query);
	 			while(result.next()){
	 				Employee employee = new Employee(Integer.parseInt(result.getString("id")),result.getString("name"),result.getString("address"));
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
		Employee employee = new Employee();
        try{
			String 	query = "SELECT * FROM employee WHERE employee id ="+employeeId;
	        result = statement.executeQuery(query);
			employee = new Employee(Integer.parseInt(result.getString("id")),result.getString("name"),result.getString("adsress"));
			return employee;
		}catch (Exception ex){
			System.out.println("Error:"+ex);
			return employee;
		}
	}
	@Override
	public void addEmployee(int employeeId, Employee employee) {
		// TODO Auto-generated method stub
		int result=0;
		try{
			if(employee.getClasificationPayment() == "Hora")			{
				result = createEmployeeHourlyPaymentClassification(employeeId,employee);
			}else if(employee.getClasificationPayment() == "Comision"){
				result = createEmployeeCommissionedPaymentClassification(employeeId,employee);
			}else if(employee.getClasificationPayment() == "Salario"){
				result = createEmployeeSalariedClassification(employeeId,employee);
			}			
			System.out.println("Creo un nuevo empleado");
		}catch (Exception e){
			System.out.println("Me mataste");
			System.err.println(e);
		}
	}
	
	
	public int createEmployeeCommissionedPaymentClassification(int employeeId, Employee employee)
    {
		int result=0;
		CommissionedPaymentClassification commissionPayment =  (CommissionedPaymentClassification) employee.getPaymentClassification();
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arquidb","root","");
			String insertEmployee_query = "INSERT INTO employee (id,name,address,type) "
 					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', 'commissioned')";
			String query_classification = "INSERT INTO commissioned (id, commission, salary)"
					+ "VALUES ('"+employeeId+"', '"+commissionPayment.getCommissionRate()+"', '"+commissionPayment.getMonthlySalary()+"')";
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(insertEmployee_query);
			result = statement.executeUpdate(query_classification);
		}catch (Exception e){
			System.err.println(e);
		}
		return result;
    }
	int createEmployeeHourlyPaymentClassification(int employeeId, Employee employee) {
		// TODO Auto-generated method stub
		 		int resultaddEmployee=0;
		 		HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification(); 
		 		try{
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arquidb","root","");
		 			String insertEmployee_query = "INSERT INTO employee (id,name,address,type) "
		 					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', 'hourly')";
		 			String insertClassification_query = "INSERT INTO hourly (id,hourlyRate) "
		 					+ "VALUES ('"+employee.getEmployeeId()+"', '"+hourlyClassification.getHourlyRate()+"')";
		 			Statement statement = connection.createStatement();
		 			resultaddEmployee = statement.executeUpdate(insertEmployee_query);
		 			resultaddEmployee = statement.executeUpdate(insertClassification_query);
					System.out.println("Empleado por hora creado satisfactoriamente.");
		 		}catch (Exception error){
		 			System.err.println(error);
		 		}
		 		return resultaddEmployee;
	}
	
	public int createEmployeeSalariedClassification(int employeeId, Employee employee)
    {
		int result=0;
		SalariedClassification salariedPayment =  (SalariedClassification) employee.getPaymentClassification();
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arquidb","root","");
			String insertEmployee_query = "INSERT INTO employee (id,name,address,type) "
 					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', 'salaried')";
			String query_classification = "INSERT INTO salaried (id, salary) "
					+ "VALUES ('"+employeeId+"', '"+salariedPayment.getSalary()+"')";
			
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(insertEmployee_query);
			result = statement.executeUpdate(query_classification);
		}catch (Exception e){
			System.err.println(e);
		}
		return result;
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
