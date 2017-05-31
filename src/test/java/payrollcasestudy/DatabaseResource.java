package payrollcasestudy;

import org.junit.rules.ExternalResource;

import payrollcasestudy.entities.PayrollDatabase;

public class DatabaseResource extends ExternalResource {
    protected PayrollDatabase instance;

    public void before(){
        instance = PayrollDatabase.globalPayrollDatabase;
    }

    public void after(){
        instance.clear();
    }

    public PayrollDatabase getInstance() {
        return instance;
    }
}
