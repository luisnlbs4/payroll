package payrollcasestudy.transactions.add;

import java.util.Calendar;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class AddServiceChargeTransaction implements Transaction {
	private int memberId;
	private Calendar date;
	private double amount;

	public AddServiceChargeTransaction(int memberId, Calendar date, double d) {
		this.memberId = memberId;
		this.date = date;
		this.amount = d;
	}

	public void execute(Repository repository) {
		// TODO Auto-generated method stub
		Employee unionMember = PayrollDatabase.globalPayrollDatabase.getUnionMember(memberId);
		if(unionMember!=null){
			UnionAffiliation unionAffiliation = unionMember.getUnionAffiliation();
			unionAffiliation.addServiceCharge(date, amount);
		}
	}

}
