package payrollcasestudy.boundaries;

import java.util.ArrayList;
import java.util.Set;

import payrollcasestudy.entities.Employee;

public class MemoryRepository implements Repository {

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

	@Override
	public ArrayList<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
