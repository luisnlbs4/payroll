package Presenters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.boundaries.DBconnect;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PaymentPresenter {
	
	private static Transaction paymentTransaction;
	private static PaydayTransaction paydayTransaction;
	private static Repository repository = new DBconnect();

	
	public static void createPayForHour(String year, String month, String day, String hours, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		Transaction paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public static void createPayForSalesReceipt(String year, String month, String day, String sales, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		Transaction paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(sales),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public static void calculateAllPays(String year, String month, String day)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		Transaction paydayTransaction = new PaydayTransaction(date);
		paydayTransaction.execute(repository);	
	}
	public static PayCheck getPayCheckFromPayDayTransaction(String employeeId)
	{
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId));
	}	

}
