package payrollcasestudy.entities;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentmethods.PaymentMethod;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Employee {
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private int employeeId;
    private String name;
    private String address;
    private UnionAffiliation unionAffiliation;

    public Employee (){
    	
    }
    public Employee(int employeeId, String name, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        unionAffiliation = UnionAffiliation.NO_AFFILIATION;
    }
    public int getEmployeeId(){
    	return employeeId;
    }
    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPayDate(Calendar payDate) {
        return paymentSchedule.isPayDate(payDate);
    }

    public Calendar getPayPeriodStartDay(Calendar payDate) {
        return paymentSchedule.getPayPeriodStartDate(payDate);
    }

    public void payDay(PayCheck payCheck) {
        double grossPay = paymentClassification.calculatePay(payCheck);
    	double deduction  = unionAffiliation.calculateDeduction(payCheck);
        double netPay = grossPay - (deduction);
        payCheck.setGrossPay(grossPay);
        payCheck.setNetPay(netPay);
        payCheck.setDeductions(deduction);
        paymentMethod.pay(payCheck);
    }

	public void setUnionAffiliation(UnionAffiliation unionAffiliation) {
		this.unionAffiliation = unionAffiliation;
	}

	public UnionAffiliation getUnionAffiliation() {
		return unionAffiliation;
	}

	public void safeEmployeeInDB(int id, Employee employee){
		PayrollDatabase.globalPayrollDatabase.addEmployee(id, employee);
	}
	
	public static Employee getEmployeeFromDB(int employeeId){
		return PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
	}
	
	public static List<Employee> getAllEmployees(){
		List<Employee> allEmployees = new ArrayList<>();
		Employee employee;
		Set<Integer> employeeIds=PayrollDatabase.globalPayrollDatabase.getAllEmployeeIds();
		List<Integer> employeeIdsList = new ArrayList<>(employeeIds);
		for(int i = 0; i < employeeIdsList.size();i++ ){
			employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeIdsList.get(i));
			allEmployees.add(employee);
		}
		return allEmployees;
	}
	
}
