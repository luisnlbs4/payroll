package payrollcasestudy.entities.affiliations;

import java.util.Calendar;


import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {

	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0,0);
	private ServiceCharge servicecharge = new ServiceCharge(null, 0.0);
	private int memberID;
	private double amount;
	

	public UnionAffiliation(int memberId, double amount) {
		this.memberID = memberId;
		this.amount = amount;
	}

	public ServiceCharge getServiceCharge(Calendar date) {
		// TODO Auto-generated method stub
		return servicecharge;
	}

	public Double getDues() {
		return amount;
	}

}
