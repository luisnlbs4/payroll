package payrollcasestudy.transactions.change;

import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeMemberTransaction implements Transaction {
	private int employeeId;
	private int memberId;
	private double dues;
	PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
	
	public ChangeMemberTransaction(int employeeId, int memberId, double dues) {
		this.employeeId=employeeId;
		this.memberId=memberId;
		this.dues=dues;
	}

	public void execute() {
		Employee employee = database.getEmployee(employeeId);
		UnionAffiliation unionAffiliation = new UnionAffiliation(memberId,dues);
	    employee.setUnionAffiliation(unionAffiliation);	
	    PayrollDatabase.globalPayrollDatabase.addUnionMember(memberId, employee);
	}

}
