package kafka_demo.kafka_demo_test;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class TwitterProducer {

	private static final String topicName="bnetdata";
	static String key="sample key";
	static String value="sample value3";


	public static void PushTwittermessage(Producer<String,String> producer) throws Exception
	{
		String consumerKey="3ifZjPGQWlAuOXBvJJksWngI8";
		String consumerSecret="23N65lzPZXHs2u82LSsK4SJI3WnhCUXIDw5nCrfrfVHgUsJMsZ";
		String token="99439626-Jl2PTvCvODK1hs6BGvnQbkoWkt8dmdBMQyaIRygNb";
		String secret="mG4rFTVFE84EHBI8x1pEXGGrQ2qExgAef30zx8BkH6wV0";
		
		KeyedMessage<String, String> message = null;
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		
		endpoint.trackTerms(Lists.newArrayList("twitterapi","#Brexit"));
		
		Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
		
		
		Client client = new ClientBuilder()
					.hosts(Constants.STREAM_HOST)
					.endpoint(endpoint)
					.authentication(auth)
					.processor(new StringDelimitedProcessor(queue))
					.build();
		
		client.connect();
		
		for(int msgread = 0;msgread < 2 ; msgread++)
		{
			
			try
			{
				String msg = queue.take();
				System.out.println(msg);
				message = new KeyedMessage<String, String>(topicName,queue.take());
			}
			
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			producer.send(message);
		}
		
		producer.close();
		client.stop();
		
	}
	
	
	public static void main( String[] args )
    {
		Properties props=new Properties();
		props.put("metadata.broker.list","34.207.166.244:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
			
		
		ProducerConfig producerConfig = new ProducerConfig(props);
		Producer<String,String> producer = new Producer<String,String>(producerConfig);
		
		//KeyedMessage<String, String> record = new KeyedMessage<String, String>("bnetdata","sample");		
		
		try
		{
			//producer.send(record);
			TwitterProducer.PushTwittermessage(producer);
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
    }
}
