package drims.poc.deploiement.api.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTools {

	private static Gson gson = new GsonBuilder().create();

	public static Object fromJson(String json, Class typeOfT) {
		return gson.fromJson(json, typeOfT);
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}
}
