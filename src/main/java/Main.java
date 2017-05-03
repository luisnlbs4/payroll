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
		
		get("/", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/hello.vm");
		    }, new VelocityTemplateEngine());
		
		get("/NewEmployee", (request, response) -> {
			 		      return new ModelAndView(new HashMap(), "templates/newEmployee.vm");
			 		    }, new VelocityTemplateEngine());
		
		get("/NewEmployeeComisioned", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/newEmployeeComisioned.vm");
		    }, new VelocityTemplateEngine());
		
		get("/NewEmployeeByHours", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/newEmployeeByHours.vm");
		    }, new VelocityTemplateEngine());
		
		post("/newEmployee", (request, response) -> EmployeePresenter.registrar_empleado_Asalariado(request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario")));
		post("/newEmployeeComisioned", (request, response) -> EmployeePresenter.registrar_empleado_Asalariado_con_Comision(request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario"),request.queryParams("comision")));
		post("/newEmployeeByHours", (request, response) -> EmployeePresenter.registrar_empleado_Asalariado(request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario")));
		
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/Employees", (request, response) -> {
			view.put("employees", EmployeePresenter.get_Empleados());
		      return new ModelAndView(view, "templates/indexEmployee.vm");
		    }, new VelocityTemplateEngine());
		
		

		//get("/", (request, response) -> Index.idexView());
	
		/*get("/createNewEmployee", (request, response) -> EmployeeView.registrationForm());
		post("/newEmployee", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address")));
		get("/showEmployee", (request, response) -> EmployeeView.showEmployee());
		get("/showAllEmployees", (request, response) -> EmployeeView.showAllEmployees());*/
	}
}
