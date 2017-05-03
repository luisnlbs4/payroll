package Presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {
	
	public static String registrar_empleado_Asalariado(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	        return "Empleado creado satisfactoriamente!";
	}

	public static ArrayList<Employee> getEmployees() {
		ArrayList<Employee> Employees = new ArrayList<>();
		Employee employee;
		Set<Integer> employeeIds=PayrollDatabase.globalPayrollDatabase.getAllEmployeeIds();
		List<Integer> employeeIdsList = new ArrayList<>(employeeIds);
		for(int i = 0; i < employeeIdsList.size();i++ ){
			employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeIdsList.get(i));
			Employees.add(employee);
		}
		return Employees;
	}


}
