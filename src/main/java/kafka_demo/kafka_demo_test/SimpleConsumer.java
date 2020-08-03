package kafka_demo.kafka_demo_test;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleConsumer {
	
	private static int count=1;
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConsumer.class);
	
	public static void main(String[] args) 
	{
		 Properties props = new Properties();
	     props.put("bootstrap.servers", "54.172.45.164:9092");
	     props.put("group.id", "test");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	     consumer.subscribe(Arrays.asList("bnetdata"));
	     
	     try 
	     {
	    	 
	    	 while (true) 
	    	 {
		         ConsumerRecords<String, String> records = consumer.poll(100);
		         
		         for (ConsumerRecord<String, String> record : records)
		        	 {		        	 	
		        	 	System.out.println(record.toString()+"  Received:"+count);
		        	 	//System.out.println("Received:"+count);
		        	 	count++;
		        	 }
		     }
		
	     } 
	     
	     catch(Exception e) 
	     {
	    	 System.out.println("No. of strings received before load test fails(at Consumer):"+(count-1));
	    	 LOGGER.error("Exception occured while consuing messages",e);
	     }
	     
	     finally 
	     {
	    	 consumer.close();
	     }
	     
	}
}
