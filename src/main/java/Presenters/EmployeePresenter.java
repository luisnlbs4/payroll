package Presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {
	
	public static String registrar_empleado(String tipo, String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision){
		if(tipo == "horas"){
			return registrar_empleado_por_hora(nombre_empleado, direccion_empleado, ci_employee, amount);
		}
		if(comision != ""){
			return registrar_empleado_Asalariado_con_Comision(nombre_empleado, direccion_empleado, ci_employee, amount,comision);
		}
		return registrar_empleado_Asalariado(nombre_empleado, direccion_empleado, ci_employee, amount);
	}
	
	public static String registrar_empleado_Asalariado(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	        return "Empleado Asalariado creado satisfactoriamente!";
	}
	
	public static String registrar_empleado_Asalariado_con_Comision(String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		double comisionn= Double.parseDouble(comision);
		 Transaction addEmployeeTransaction =
	                new AddCommissionedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt,comisionn);
	        addEmployeeTransaction.execute();
	        return "Empleado con comision creado satisfactoriamente!";
	}
	
	public static String registrar_empleado_por_hora(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddHourlyEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	        return "Empleado por hora creado satisfactoriamente!";
	}


	public static ArrayList<Employee> Devolver_empleados() {
		ArrayList<Employee> Employees = new ArrayList<>();
		return Employees=PayrollDatabase.globalPayrollDatabase.getAllEmployees();
	}


}
