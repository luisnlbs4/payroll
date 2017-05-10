package Presenters;

import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PaymentPresenter {
	
	private static Transaction paymentTransaction;
	private static PaydayTransaction paydayTransaction;

	
	public static void createPayPerHour(String year, String month, String day, String hours, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute();
	}
	
	public static void calculateAllPays(String year, String month, String day)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
	    paydayTransaction = new PaydayTransaction(date);
		paydayTransaction.execute();		
	}
	
	public static PayCheck getPayCheckFromPayDayTransaction(String employeeId)
	{
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId));
	}
	

}