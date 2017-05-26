package payrollcasestudy.boundaries;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class GsonApi {
	public static String toJson(Object object) {
		
		return new Gson().toJson(object);
	}
	public static ResponseTransformer json() {
		return GsonApi::toJson;
		}

}
