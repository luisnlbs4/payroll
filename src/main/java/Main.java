import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

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
		post("/Employees", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/indexEmployee.vm");
		    }, new VelocityTemplateEngine());
		
		post("/newEmployee", (request, response) -> EmployeePresenter.registrar_empleado_Asalariado(request.queryParams("nombre"),request.queryParams("direccion"),request.queryParams("ci"), request.queryParams("salario")));

		
		

		//get("/", (request, response) -> Index.idexView());
	
		/*get("/createNewEmployee", (request, response) -> EmployeeView.registrationForm());
		post("/newEmployee", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address")));
		get("/showEmployee", (request, response) -> EmployeeView.showEmployee());
		get("/showAllEmployees", (request, response) -> EmployeeView.showAllEmployees());*/
	}
}
