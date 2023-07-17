package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.connfig.CustomPropertyConfig;

import software.amazon.awssdk.services.batch.BatchClient;
import software.amazon.awssdk.services.batch.model.SubmitJobRequest;
import software.amazon.awssdk.services.batch.model.SubmitJobResponse;

@Service
public class BatchService {

	@org.springframework.beans.factory.annotation.Autowired(required = true)
	private final BatchClient batchClient;

	private CustomPropertyConfig customPropertyConfig;

	public BatchService(BatchClient batchClient, final CustomPropertyConfig customPropertyConfig) {
		this.batchClient = batchClient;
		this.customPropertyConfig = customPropertyConfig;
	}


	//	@Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(fixedRate = 300000)
	public void run() {

		String jobDefinitionArn = customPropertyConfig.jobDefinitionArn;
		String jobQueueArn = customPropertyConfig.jobQueueArn;
		String jobName = customPropertyConfig.jobName;

		SubmitJobRequest submitJobRequest = SubmitJobRequest.builder()
				.jobDefinition(jobDefinitionArn)
				.jobQueue(jobQueueArn)
				.jobName(jobName)
				.shareIdentifier("UserA*")
				.build();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		batchClient.submitJob(submitJobRequest);
		System.out.print(formatter.format(date));

		SubmitJobResponse submitJobResponse = batchClient.submitJob(submitJobRequest);
		String jobId = submitJobResponse.jobId();

		System.out.println("Daily batch job submitted. Job ID: " + jobId);
	}
}
