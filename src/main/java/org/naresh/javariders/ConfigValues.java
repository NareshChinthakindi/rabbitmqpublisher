/**
 * 
 */
package org.naresh.javariders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Naresh
 *
 */
@Configuration
public class ConfigValues {


	public static String rabbitMQQueueName = null;
	public static String rabbitMQUserName = null;
	public static String rabbitMQPassword = null;
	public static String rabbitMQHost = null;
	public static int rabbitMQPort=0;
	public static String rabbitMQMetaDataQueueName = null;
	public static String pcr_update_metadata_routing_key = null;
	public static String pcr_update_routing_key = null;
	public static String pcr_Update_Topic_Exchange = null;
	
	@Value("${verisk.rabbitmq.pcr_Update_Topic_Exchange:pcr_Update_Topic_Exchange}")
	public void setPcr_Update_Topic_Exchange(String pcr_Update_Topic_Exchange) {
		ConfigValues.pcr_Update_Topic_Exchange = pcr_Update_Topic_Exchange;
	}

	@Value("${verisk.rabbitmq.pcr_update_metadata_routing_key:javariders}")
	public void setPcr_update_metadata_routing_key(String pcr_update_metadata_routing_key) {
		ConfigValues.pcr_update_metadata_routing_key = pcr_update_metadata_routing_key;
	}

	@Value("${verisk.rabbitmq.pcr_update_routingkey:pcr_update_routing_key}")
	public void setPcr_update_routing_key(String pcr_update_routing_key) {
		ConfigValues.pcr_update_routing_key = pcr_update_routing_key;
	}

	@Value("${veriks.rabbitmq.metadata.queue.name:javariders}")
	public void setRabbitMQMetaDataQueueName(String rabbitMQMetaDataQueueName) {
		ConfigValues.rabbitMQMetaDataQueueName = rabbitMQMetaDataQueueName;
	}

	@Value("${veriks.rabbitmq.queue.name:javariders}")
	public void setRabbitMQQueueName(String rabbitMQQueueName) {
		ConfigValues.rabbitMQQueueName = rabbitMQQueueName;
	}
	@Value("${verisk.rabbitmq.username:guest}")
	public void setRabbitMQUserName(String rabbitMQUserName) {
		ConfigValues.rabbitMQUserName = rabbitMQUserName;
	}
	@Value("${verisk.rabbitmq.password:guest}")
	public void setRabbitMQPassword(String rabbitMQPassword) {
		ConfigValues.rabbitMQPassword = rabbitMQPassword;
	}
	@Value("${verisk.rabbitmq.host:localhost}")
	public void setRabbitMQHost(String rabbitMQHost) {
		ConfigValues.rabbitMQHost = rabbitMQHost;
	}
	@Value("${verisk.rabbitmq.port:5672}")
	public void setRabbitMQPort(int rabbitMQPort) {
		ConfigValues.rabbitMQPort = rabbitMQPort;
	}



}
