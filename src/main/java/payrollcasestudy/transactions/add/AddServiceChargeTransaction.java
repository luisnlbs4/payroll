package payrollcasestudy.transactions.add;

import java.util.Calendar;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class AddServiceChargeTransaction implements Transaction {
	
	  PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;

	public AddServiceChargeTransaction(int memberId, Calendar date, double amount) {
		Employee unionMember = database.getUnionMember(memberId);
        UnionAffiliation unionAffiliation = unionMember.getUnionAffiliation();
        unionAffiliation.addServiceCharge(date, amount);
	}

	public void execute() {
		// TODO Auto-generated method stub
		
	}


}
