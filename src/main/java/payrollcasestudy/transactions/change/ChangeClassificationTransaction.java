package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction{

    public ChangeClassificationTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    public void changeEmployee(Employee employee, Repository repository) {
        PaymentClassification classification = getNewPaymentClassification();
        employee.setPaymentClassification(classification);
        PaymentSchedule schedule = getNewPaymentSchedule();
        employee.setPaymentSchedule(schedule);
    }

    abstract PaymentClassification getNewPaymentClassification();
    abstract PaymentSchedule getNewPaymentSchedule();
}