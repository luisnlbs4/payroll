import payrollcasestudy.boundaries.DBconnect;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.RoutesController;
import Presenters.EmployeePresenter;
import Presenters.PaymentPresenter;

public class Main {

	public static void main(String[] args) {
		Repository repository = new DBconnect();
		EmployeePresenter employeeService = new EmployeePresenter(repository);
		PaymentPresenter paymentService = new PaymentPresenter(repository);
		RoutesController routes = new RoutesController(employeeService, paymentService);
        routes.get_all_routes();		
	}
}
