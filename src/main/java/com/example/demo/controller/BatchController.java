package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BatchService;

@RestController
@EnableScheduling
public class BatchController {
	
	@Autowired
	BatchService batchService;
	
	@GetMapping(value = "/batch")
	public String batch() {
		
		batchService.run();
		
		return "Batch successfully";
	}
	
}
