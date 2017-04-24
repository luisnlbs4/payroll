package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {

	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(-1,0);
	Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	private int memberID;
	private double amount;
	

	public UnionAffiliation(int memberId, double amount) {
		this.memberID = memberId;
		this.amount = amount;
	}

	public ServiceCharge getServiceCharge(Calendar date) {
		// TODO Auto-generated method stub
		return serviceCharges.get(date);
	}
	
	public void addServiceCharge(Calendar date, double amount) {
		this.serviceCharges.put(date, new ServiceCharge(date, amount));
	}

	public Double getDues() {
		return amount;
	}

}
