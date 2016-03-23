/**
 * 
 */
package org.naresh.javariders;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Naresh
 *
 */
@Configuration
@EnableAsync
@EnableScheduling
public class Publisher {

      
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Scheduled(fixedRate = 5000)
	public void sendMessage()
	{
		System.out.println("Inside the sendMessage");

		FileInputStream i = null;
		try
		{
			
			
			
			
			File f = new File(Publisher.class.getClassLoader().getResource("CHINTHAKINDINARESHKUMAR.pdf").getFile());
			i = new FileInputStream(f);
			byte[] body = new byte[(int) f.length()];
			i.read(body);
			i.close();
			
			String uuid = UUID.randomUUID().toString();
			
			Message message = MessageBuilder.withBody(body)
					.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
					.setMessageId(uuid)
					.setHeader("fileName", uuid+"_"+f.getName())
					.build();
			rabbitTemplate.send(ConfigValues.pcr_Update_Topic_Exchange,ConfigValues.pcr_update_routing_key,message);
			rabbitTemplate.send(ConfigValues.pcr_Update_Topic_Exchange,ConfigValues.pcr_update_metadata_routing_key, message);
			
			System.out.println("Message sent");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
