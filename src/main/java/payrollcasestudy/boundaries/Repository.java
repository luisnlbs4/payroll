package payrollcasestudy.boundaries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import payrollcasestudy.entities.Employee;

public interface Repository {
	    public Employee getEmployee(int employeeId) ;
	    public void addEmployee(int employeeId, Employee employee);
	    public void clear();
	    public void deleteEmployee(int employeeId);
	    public Employee getUnionMember(int memberId);
	    public void addUnionMember(int memberId, Employee employee);
	    public void deleteUnionMember(int memberId);
	    public Set<Integer> getAllEmployeeIds() ;
	    public ArrayList<Employee> getAllEmployees(); 

}
