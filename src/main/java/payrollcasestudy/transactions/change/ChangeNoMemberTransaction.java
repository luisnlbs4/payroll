package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {
	
	
	public ChangeNoMemberTransaction(int employeeId)
	{
		super(employeeId);
	}

	@Override
	public void changeEmployee(Employee employee, Repository repository) {
		int memberId = employee.getUnionAffiliation().getMemberId();
		employee.setUnionAffiliation(UnionAffiliation.NO_AFFILIATION);
		repository.deleteUnionMember(memberId);
	}

	

}
