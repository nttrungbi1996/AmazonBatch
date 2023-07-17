package com.example.demo.connfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class CustomPropertyConfig {
	
  @Value("${jobDefinitionArn}")
  public String jobDefinitionArn;

  @Value("${jobQueueArn}")
  public String jobQueueArn;
  
  @Value("${jobName}")
  public String jobName;
 
}