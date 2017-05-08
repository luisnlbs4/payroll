import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.Employee;
import Presenters.EmployeePresenter;
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
		post("/newEmployee", (request, response) -> {		
			 EmployeePresenter.registrar_empleado(request.queryParams("kind"),request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario"),request.queryParams("comision"));
			 response.redirect("/Employees");
	         return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
		}, new VelocityTemplateEngine());
			
		get("/Employees", (request, response) -> {
			view.put("employees", EmployeePresenter.Devolver_empleados());
		      return new ModelAndView(view, "templates/employee/indexEmployee.vtl");
		    }, new VelocityTemplateEngine());		
		get("/EmployeeAddHours/:id", (request, response) -> {			
			Employee employee = EmployeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			  return new ModelAndView(view, "templates/employee/employeeAddhours.vtl");
			}, new VelocityTemplateEngine());

	}
}
