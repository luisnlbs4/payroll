import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
		get("/", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/hello.vtl");
		    }, new VelocityTemplateEngine());
		
		//get("/", (request, response) -> Index.idexView());
	
		/*get("/createNewEmployee", (request, response) -> EmployeeView.registrationForm());
		post("/newEmployee", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address")));
		get("/showEmployee", (request, response) -> EmployeeView.showEmployee());
		get("/showAllEmployees", (request, response) -> EmployeeView.showAllEmployees());*/
	}
}
