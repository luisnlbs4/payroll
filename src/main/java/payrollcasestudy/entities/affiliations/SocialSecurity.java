package payrollcasestudy.entities.affiliations;

import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;

public class SocialSecurity implements Afiliations{

	public  final static  UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0, 0);
	Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	private int memberId;
	private double dues;
	
	public SocialSecurity(int memberId){
		this.memberId = memberId;
		this.dues = 12;
	}
	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharges.get(date);

	}
	public Double getDues() {		
		return dues;
	}
	public int getMemberId() {
		return memberId;
	}
	
	public void addServiceCharge(Calendar date, double amount) {
		// TODO Auto-generated method stub
		this.serviceCharges.put(date, new ServiceCharge(date, amount));

	}
	public double calculateDeduction(PayCheck payCheck) {
		// TODO Auto-generated method stub
		return calculateUnionAmount(payCheck) + calculateServiceCharges(payCheck);
	}
	private double calculateUnionAmount(PayCheck payCheck) {
		int fridays = numberOfFridays(payCheck.getPayPeriodStart(), payCheck.getPayPeriodEnd());
		return dues * fridays;
	}

	private double calculateServiceCharges(PayCheck payCheck) {
		double totalServiceCharge = 0;
		for(ServiceCharge serviceCharge : serviceCharges.values()){
			if(isInPayPeriod(serviceCharge.getDate(), payCheck))
				totalServiceCharge += serviceCharge.getAmount();
		}
		return totalServiceCharge;
	}

	private int numberOfFridays(Calendar payPeriodStart, Calendar payPeriodEnd) {
		int fridays = 0;
		while(!payPeriodStart.after(payPeriodEnd)){
			if (payPeriodStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                fridays++;
            payPeriodStart.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fridays;
	}

}
