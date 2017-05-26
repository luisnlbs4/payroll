package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChangeAddressTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();
	private static final Repository repository = new MemoryRepository();

    @Test
    public void testChangeNameTransaction() throws Exception {
        int employeeId = 2;
        AddHourlyEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        ChangeAddressTransaction changeAddressTransaction =
                new ChangeAddressTransaction(employeeId, "Mars");
        changeAddressTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getAddress(), is("Mars"));
    }

}
