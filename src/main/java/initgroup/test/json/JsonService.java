package initgroup.test.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class JsonService {

	private final Gson gson;

	public JsonService() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new DateDeserializer());
		builder.setPrettyPrinting();
		gson = builder.create();
	}

	public String toJson(Object src) {
		return gson.toJson(src);
	}
}
