package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;

public interface Afiliations {
	
	public ServiceCharge getServiceCharge(Calendar date);
	public Double getDues();
	public int getMemberId();	
	public void addServiceCharge(Calendar date, double amount);
	public double calculateDeduction(PayCheck payCheck);
	
}
