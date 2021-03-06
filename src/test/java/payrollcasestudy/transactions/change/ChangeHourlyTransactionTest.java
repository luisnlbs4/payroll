package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

public class ChangeHourlyTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();
	private static final Repository repository = new MemoryRepository();

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int employeeId = 3;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Lance", "Home", 2500, 3.2);
        addEmployeeTransaction.execute(repository);

        ChangeHourlyTransaction changeHourlyTransaction = new ChangeHourlyTransaction(employeeId, 27.52);
        changeHourlyTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getPaymentClassification(), is(instanceOf(HourlyPaymentClassification.class)));
        HourlyPaymentClassification paymentClassification =
                (HourlyPaymentClassification) employee.getPaymentClassification();
        assertThat(paymentClassification.getHourlyRate(), is(closeTo(27.52, FLOAT_ACCURACY)));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklyPaymentSchedule.class)));
    }
}