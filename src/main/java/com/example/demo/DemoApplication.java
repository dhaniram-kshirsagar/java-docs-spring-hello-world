package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Include the following imports to use queue APIs
import com.azure.core.util.*;
import com.azure.storage.queue.*;
import com.azure.storage.queue.models.*;

@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer {

	// Define the connection-string with your values.
   final String storageConnectionString =
	"DefaultEndpointsProtocol=https;" +
	"AccountName=webqarch;" +
	"AccountKey=5MqbBIuOOfrhXbPU0yZEbbzpoKANVbUxfiVH4puBmVVvvrY4JL/Nwgi8pxqlNYlMLTT4CLg2tvro+AStXKsHyw==";

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	String sayHello() {
		return "Hello WebQSample by Dhani!";
	}

	@RequestMapping("/q")
	String getQ() {
		return "This is my Q!";
	}

	@RequestMapping("/qp")
	String getQP() {
		String message = getMsgFromgQueue();
		return message;
	}

	private String getMsgFromgQueue(){
		try{
			// Instantiate a QueueClient which will be
			// used to create and manipulate the queue
			QueueClient queueClient = new QueueClientBuilder()
										.connectionString(storageConnectionString)
										.queueName("webqarchi")
										.buildClient();
	
			// Peek at the first message
			PeekedMessageItem peekedMessageItem = queueClient.peekMessage();
			System.out.println("Peeked message: " + peekedMessageItem.getMessageText());
			return peekedMessageItem.getMessageText();
		}
		catch (QueueStorageException e){
			// Output the exception message and stack trace
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "Failed to read from queue";
	}
}
