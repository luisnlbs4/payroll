import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import Presenters.EmployeePresenter;
import Presenters.PaymentPresenter;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
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
			 EmployeePresenter.registrar_empleado(request.queryParams("kind"),request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario"),request.queryParams("comision"));
			 response.redirect("/Employees");
	         return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
		}, new VelocityTemplateEngine());
		
		
		
		get("/EmployeeAddHours/:id", (request, response) -> {			
			Employee employee = EmployeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			  return new ModelAndView(view, "templates/payment/employeeAddhours.vtl");
			}, new VelocityTemplateEngine());
		
		post("/payHourly", (request, response) -> {			 			
 			PaymentPresenter.createPayPerHour(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), request.queryParams("hours"),request.queryParams("employeeId"));
 			response.redirect("/Employees");
            return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
         }, new VelocityTemplateEngine());
		
		get("/payEmployee/show/:id", (request, response) -> {			
			Employee employee = EmployeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			view.put("salary", PaymentPresenter.getPayCheckFromPayDayTransaction(Integer.parseInt(request.params(":id"))));
            return new ModelAndView(view, "templates/payment/payEmployee.vtl");
        }, new VelocityTemplateEngine());
		

	}
}
