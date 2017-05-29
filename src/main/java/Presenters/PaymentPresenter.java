package Presenters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PaymentPresenter {
	    private Transaction paymentTransaction;
		private  PaydayTransaction paydayTransaction;
	    private Repository repository;

	
	public PaymentPresenter(Repository repository) {
		this.repository=repository;
 	}

	public void createPayForHour(String year, String month, String day, String hours, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		 paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public void createPayForSalesReceipt(String year, String month, String day, String sales, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(sales),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public void calculateAllPays(String year, String month, String day)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paydayTransaction = new PaydayTransaction(date);
		paydayTransaction.execute(repository);	
	}
	public double getNetpay(String employeeId)
	{
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId)).getNetPay();
	}	

}
