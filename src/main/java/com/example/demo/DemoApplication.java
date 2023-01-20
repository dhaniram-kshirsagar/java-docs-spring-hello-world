package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Include the following imports to use queue APIs
// Include the following imports to use queue APIs.
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;

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
		try
		{
			// Retrieve storage account from connection-string.

			CloudStorageAccount storageAccount =
			CloudStorageAccount.parse(storageConnectionString);

			// Create the queue client.
			CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

			// Retrieve a reference to a queue.
			CloudQueue queue = queueClient.getQueueReference("myqueue");

			// Create the queue if it doesn't already exist.
			queue.createIfNotExists();
		}
		catch (Exception e)
		{
			// Output the stack trace.
			e.printStackTrace();
		}
		return "This is my QP!";
	}

	private addMsgToQ(){
		try
		{
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount =
			CloudStorageAccount.parse(storageConnectionString);

			// Create the queue client.
			CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

			// Retrieve a reference to a queue.
			CloudQueue queue = queueClient.getQueueReference("myqueue");

			// Create the queue if it doesn't already exist.
			queue.createIfNotExists();

			// Create a message and add it to the queue.
			CloudQueueMessage message = new CloudQueueMessage("Hello, World");
			queue.addMessage(message);
		}
		catch (Exception e)
		{
			// Output the stack trace.
			e.printStackTrace();
		}
	}
}
