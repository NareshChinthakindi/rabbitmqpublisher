/**
 * 
 */
package org.naresh.javariders;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Naresh
 *
 * This is spring boot application where it will load all the configuration values 
 * and resolve the dependency injection
 *
 */
@Configuration
public class RabbitMQConfiguration {


	@Bean(name="rabbitConnectionFactoryBean")
	public RabbitConnectionFactoryBean rabbitConnectionFactoryBean()
	{
		RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
		return factory;
	}

	@Bean(name="amqpAdmin")
	public AmqpAdmin amqpAdmin() {
		
		AmqpAdmin amqpAdmin = new RabbitAdmin(rabbitConnectionFactory());
		
		return amqpAdmin;
	}

	@Bean(name="cachingConnectionFactory")
	public ConnectionFactory rabbitConnectionFactory()
	{
		CachingConnectionFactory connectionFactory =  new CachingConnectionFactory();
		connectionFactory.setHost(ConfigValues.rabbitMQHost);
		connectionFactory.setPassword(ConfigValues.rabbitMQPassword);
		connectionFactory.setUsername(ConfigValues.rabbitMQUserName);
		connectionFactory.setPort(ConfigValues.rabbitMQPort);
		connectionFactory.setChannelCacheSize(Integer.valueOf( 10 ));
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(@Qualifier("cachingConnectionFactory") ConnectionFactory rabbitConnectionFactory)
	{
		//rabbitConnectionFactory.get
		return new RabbitTemplate(rabbitConnectionFactory);
	}

	@Bean(name="pcrUpdateQueue")
	public Queue pcrUpdateQueue()
	{
		return new Queue(ConfigValues.rabbitMQQueueName);
	}
	
	/*@Bean(name="listenerAdapter")
	public MessageListenerAdapter listenerAdapter(@Qualifier("receiver") Receiver receiver) 
	{
		return new MessageListenerAdapter(receiver, "onMessage");
	}*/

	/*@Bean(name="pcrUpdateQueueListner")
	public SimpleMessageListenerContainer container(@Qualifier("cachingConnectionFactory") ConnectionFactory connectionFactory
			, @Qualifier("listenerAdapter") MessageListenerAdapter listenerAdapter, @Qualifier("pcrUpdateQueue") Queue pcrUpdateQueue) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueues(pcrUpdateQueue);
		container.setMessageListener(listenerAdapter);
		return container;
	}*/
	
	@Bean(name="pcrMetaDataQueue")
	public Queue pcrMetaDataQueue()
	{
		return new Queue(ConfigValues.rabbitMQMetaDataQueueName);
	}
	
	/*@Bean(name="metaDatalistenerAdapter")
	public MessageListenerAdapter metaDatalistenerAdapter(@Qualifier("metaDataReceiver") MetaDataReceiver receiver) 
	{
		return new MessageListenerAdapter(receiver, "onMessage");
	}*/

	/*@Bean(name="pcrMetaDataUpdateQueueListner")
	public SimpleMessageListenerContainer pcrMetaDataUpdateQueueListnerContainer(@Qualifier("cachingConnectionFactory") ConnectionFactory connectionFactory
			, @Qualifier("metaDatalistenerAdapter") MessageListenerAdapter listenerAdapter, @Qualifier("pcrMetaDataQueue") Queue pcrUpdateQueue) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueues(pcrUpdateQueue);
		container.setMessageListener(listenerAdapter);
		return container;
	}*/
	
	@Bean(name="pcrUpdateTopicExchange")
    public TopicExchange pcrUpdateDataExchange() {
        return new TopicExchange(ConfigValues.pcr_Update_Topic_Exchange);
    }
	
	@Bean(name="pcrUpdateQueueBinding")
	public Binding pcrUpdateQueueBinding()
	{
		Binding binding = BindingBuilder.bind(
				pcrUpdateQueue()).to(pcrUpdateDataExchange()).with(ConfigValues.pcr_update_routing_key);
		
		return binding;
	}
	
	@Bean(name="pcrMetaDataUpdateQueueBinding")
	public Binding pcrMetaDataUpdateQueueBinding()
	{
		Binding binding = BindingBuilder.bind(pcrMetaDataQueue()).to(pcrUpdateDataExchange()).with(ConfigValues.pcr_update_metadata_routing_key);
		
		return binding;
	}


}
