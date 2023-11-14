package demo.learnharapi.service;

import java.io.IOException;
import java.util.List;
import java.io.File;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;


import com.fasterxml.jackson.databind.ObjectMapper;

public class HarProcessorService {

	private final ObjectMapper objectMapper;

	public HarProcessorService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void processHarFile(String harFilePath) throws IOException {
		File harFile = new File(harFilePath);
		Har har = objectMapper.readValue(harFile, Har.class);

		HarLog harLog = har.getLog();
		List<HarEntry> entries = harLog.getEntries();

		for (int i = 0; i < entries.size(); i++) {
			HarEntry entry = entries.get(i);
			HarRequest request = entry.getRequest();

			// Extract relevant information (e.g., URL, method, headers) from the request
			String apiUrl = request.getUrl();
			String apiMethod = request.getMethod();
			// ... additional information as needed

			// Create a separate JSON file for each API
			writeApiToJsonFile(apiUrl, apiMethod, entry);
		}
	}

	private void writeApiToJsonFile(String apiUrl, String apiMethod, HarEntry entry) throws IOException {
		// Customize the JSON file name as needed
		String fileName = "api_" + apiMethod + "_" + apiUrl.replaceAll("[^a-zA-Z0-9]", "_") + ".json";

		// Write the API information to the JSON file
		objectMapper.writeValue(new File(fileName), entry);
	}

}
