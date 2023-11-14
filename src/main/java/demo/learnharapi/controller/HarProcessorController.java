package demo.learnharapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.learnharapi.service.HarProcessorService;

public class HarProcessorController {

	private final HarProcessorService harProcessorService;

	@Autowired
	public HarProcessorController(HarProcessorService harProcessorService) {
		this.harProcessorService = harProcessorService;
	}

	@PostMapping("/processingHarFile")
	public String processHarFile(@RequestParam String harFilePath) {
		try {
			harProcessorService.processHarFile(harFilePath);
			return "Processing completed successfully.";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error processing the .har file: " + e.getMessage();
		}
	}

}
