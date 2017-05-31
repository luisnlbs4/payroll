package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayrollDatabase;
import payrollcasestudy.transactions.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {

    PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private int employeeId;

    public ChangeEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    public void execute(Repository repository) {
        Employee employee = repository.getEmployee(employeeId);
        changeEmployee(employee,repository);
    }


	public void changeEmployee(Employee employee, Repository repository) {
		
	}
}
