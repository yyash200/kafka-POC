package kafka_demo.kafka_demo_test;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;



public class SimpleProducer {

		private static final String topicName="bnetdata";
		static String key="sample key";
		static String value="wtOA5wqvAzpOu5cVrs1c0VU00JF9gGrOq1qCWj8dfV9jzSNPxezxEI7lqEnaC3ysWqZZNeYnRFpsi6KA88JRrOoFcxVCHyJ7BNaYqp7iykUVgN9SvvLFKZbTkVJfq4BlxgiZzSRQTLjgE0LOFyUDOojzOj5a8n7QfgxDWbUJ7exGfBYqLt6qJkTFh20Zwn42AmuSfibHCxL4UAHQSe0KPuZiofS6K4BNajeKa2wjkgsM7Goa0agY3JuZLo1dSlm48YaVRqy2qkphFeZ6kqlbVIecxFkI7eluNpdrL7tp9mcRFk3RjKbqYGbeoFohvXp2UWy2qvGj9MYJZiyaodVVixbd2iHknjCumgJwJXHY8L51MjdhbuTPJNJOSg6DzUEHel8tWq0xDF8g2ijSuS4tXg2LS56CR51rbSC0DN1HTv9RhcYorc9x5S0kHmi3Y3QNmqIsg16p71a4y1kQWfb6k7KWdmmlsyJ2yDFu4zeUwZLEDjq5NUTq";
		static int load=1000000; 
	
		
		public static void main( String[] args )
	    { 
			int count=1;
			
			Properties props=new Properties();
			props.put("metadata.broker.list","54.172.45.164:9092");
			//System.out.println("Message 1 before publishing");
			props.put("serializer.class", "kafka.serializer.StringEncoder");
				
			ProducerConfig producerConfig = new ProducerConfig(props);
			Producer<String,String> producer = new Producer<String,String>(producerConfig);
			//KeyedMessage<String, String> record = new KeyedMessage<String, String>(topicName,value);		
			
			//System.out.println("Message 2 before publishing");
			
			
			try
			{
				//System.out.println("Message final before publishing");
				
				//for (count = 1; count <= load; count++)
				while(true)
				{
					producer.send(new KeyedMessage<String, String>(topicName,value));
					System.out.println("Sent:"+count);
					count++;
				}
				//producer.send(record);
				
				//System.out.println("Message published");
			}
			
			catch(Exception e) 
			{
				
				System.out.println("No. of strings sent before load test fails(at Producer):"+(count-1));
				System.out.println(e);
			}
			
			finally
			{
				producer.close();
			}
			
			
	    }
	

}
