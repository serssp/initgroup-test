package initgroup.test.runner;

import initgroup.test.json.JsonService;
import initgroup.test.order.struct.Order;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Runner {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java -jar JAR_FILE <sourceBinFile> <targetFile>");
			System.exit(1);
		}

		File source = new File(args[0]);
		if (!source.exists()) {
			System.out.println("File not found: " + source.getAbsolutePath());
			System.exit(1);
		}

		convert(source, new File(args[1]));
	}

	private static void convert(File source, File target) throws IOException {
		OrderReader reader = new OrderReader();
		Order order = reader.read(new FileInputStream(source));

		JsonService jsonService = new JsonService();
		String json = jsonService.toJson(order);
		byte[] bytes = json.getBytes("UTF-8");
		Files.write(target.toPath(), bytes);
	}
}
