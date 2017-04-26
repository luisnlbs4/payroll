package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import org.hamcrest.Matcher;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {
	private int numberId;
	private double amount;
	private ServiceCharge serviceCharge;
	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0,0);
	
	public UnionAffiliation(int numberId, double amount){
		this.numberId = numberId;
		this.amount = amount;
	}

	public ServiceCharge getServiceCharge(Calendar date) {
		
		if (serviceCharge.getDate()==date)
			return serviceCharge;
		else
			return null;
	}

	public void addServiceCharge(ServiceCharge serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	
	public int getNumberId()
	{
		return numberId;
	}

	public Double getDues() {
		return amount;
	}

	
	
	
}
