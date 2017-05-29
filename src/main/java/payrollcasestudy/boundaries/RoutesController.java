package payrollcasestudy.boundaries;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;

import Presenters.EmployeePresenter;
import Presenters.PaymentPresenter;
import payrollcasestudy.entities.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class RoutesController {
    
	private EmployeePresenter employeePresenter;
	private PaymentPresenter paymentPresenter;
	public RoutesController(EmployeePresenter employeePresenter, PaymentPresenter payPresenter) {
		this.employeePresenter = employeePresenter;
		this.paymentPresenter=payPresenter;
	}
	public void get_all_routes() {
		HashMap<String,Object> view = new HashMap<String, Object>();	
		get("/", (request, response) -> {
		      return new ModelAndView(view, "templates/welcome/index.vtl");
		    }, new VelocityTemplateEngine());
		
		
		get("/NewEmployee", (request, response) -> {
			 		      return new ModelAndView(view, "templates/employee/newEmployee.vtl");
			 		    }, new VelocityTemplateEngine());					
		get("/Employees", (request, response) -> {
			view.put("employees", EmployeePresenter.Devolver_empleados());
		      return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
		    }, new VelocityTemplateEngine());		
		post("/newEmployee", (request, response) -> {		
			 employeePresenter.registrar_empleado(request.queryParams("kind"),request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario"),request.queryParams("comision"));
			 response.redirect("/Employees");
	         return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
		}, new VelocityTemplateEngine());
		
		
		
		get("/CreatePayEmployee/:id", (request, response) -> {			
			Employee employee = employeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			  return new ModelAndView(view, "templates/payment/createPayEmployees.vtl");
			}, new VelocityTemplateEngine());
		
		post("/payHourly", (request, response) -> {			 			
			paymentPresenter.createPayForHour(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), request.queryParams("hours"),request.queryParams("employeeId"));
 			response.redirect("/Employees");
            return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
         }, new VelocityTemplateEngine());
		
		 post("/payComissioned", (request, response) -> {			
			paymentPresenter.createPayForSalesReceipt(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), request.queryParams("sales"),request.queryParams("employeeId"));
			response.redirect("/Employees");
            return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		get("/payEmployee/show/:id", (request, response) -> {	
			Employee employee;
			employee = employeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			double total = paymentPresenter.getNetpay((request.params(":id")));
			view.put("employee", employee);
			view.put("salary", total);
            return new ModelAndView(view, "templates/payment/viewPayEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		
		get("/payAll", (request, response) -> {	
            return new ModelAndView(view, "templates/payment/payEmployees.vtl");
            }, new VelocityTemplateEngine());
		
		post("/payAllTransaction", (request, response) -> {
			paymentPresenter.calculateAllPays(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"));			
			response.redirect("/Employees");
            return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
        }, new VelocityTemplateEngine());	
		
		get("/json/employees", (req, res) -> EmployeePresenter.Devolver_empleados(), GsonApi.json());		
		get("/json/employees/:id", (request, response) -> {
			Employee employee;			
			employee = employeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			return employee;
		}, GsonApi.json());
		
		
	}

}
