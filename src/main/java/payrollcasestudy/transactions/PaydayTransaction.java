package payrollcasestudy.transactions;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.PayrollDatabase;

import java.util.*;

public class PaydayTransaction implements Transaction{
    private Calendar payDate;
    //private PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private Map<Integer, PayCheck> payChecks = new HashMap<Integer, PayCheck>();

    public PaydayTransaction(Calendar payDate) {
        this.payDate = payDate;
    }

    public void execute(Repository repository) {
        for (Integer employeeId: repository.getAllEmployeeIds()){
            Employee employee = repository.getEmployee(employeeId);
            if (employee.isPayDate(payDate)){
                PayCheck payCheck = new PayCheck(employee.getPayPeriodStartDay(payDate),payDate);
                payChecks.put(employeeId, payCheck);
                employee.payDay(payCheck);
            }
        }
    }

    public PayCheck getPaycheck(int employeeId) {
        return payChecks.get(employeeId);
    }
}
