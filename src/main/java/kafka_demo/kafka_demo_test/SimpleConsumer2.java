package kafka_demo.kafka_demo_test;

import java.util.Properties;
import java.util.function.Consumer;

//import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;

public class SimpleConsumer2 {
	
//	private static final String topicName="bnetdata";
	
	public static void main(String[] args) 
	{
		
	}
}
	/*	Properties props=new Properties();
		//props.put("bootstrap.servers", "3.87.215.22:9092");
		props.put("metadata.broker.list","54.81.249.114:9092");
		//props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//System.out.println("Message befor publishing 1");
		props.put("deserializer.class", "kafka.deserializer.StringEncoder");
			
		
		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		
		Consumer<String> consumer = new Consumer<String>(consumerConfig);
		
		KeyedMessage<String, String> record = new KeyedMessage<String, String>(topicName);		
		
		System.out.println("Message befor publishing 2");
		
		
		try{
			System.out.println("Message befor publishing final");
			producer.send(record);
			System.out.println("Message published");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
			
		
	}
	 */

