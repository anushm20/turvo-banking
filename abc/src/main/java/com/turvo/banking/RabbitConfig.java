/**
 * Configuraton class for RabbitMQ
 */
package com.turvo.banking;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.turvo.banking.common.BankingConstants;

/**
 * @author anushm
 *
 */
@Configuration
public class RabbitConfig  
{
    @Bean
    Queue tokensQueue() {
        return QueueBuilder.durable(BankingConstants.CREATED_TOKEN_QUEUE).build();
    }
 
    @Bean
    Exchange tokensExchange() {
        return ExchangeBuilder.topicExchange(BankingConstants.CREATED_TOKEN_EXCHANGE).build();
    }
 
    @Bean
    Binding tokenBinding(Queue tokensQueue, TopicExchange tokensExchange) {
        return BindingBuilder.bind(tokensQueue).to(tokensExchange).
        				with(BankingConstants.CREATED_TOKEN_QUEUE);
    }
    
    
    @Bean
    Queue countersExchangeQueue() {
        return QueueBuilder.durable(BankingConstants.TOKEN_COUNTER_EXCHANGE_QUEUE).build();
    }
 
    @Bean
    Exchange countersExchange() {
        return ExchangeBuilder.topicExchange(BankingConstants.TOKEN_COUNTER_EXCHANGE).build();
    }
 
    @Bean
    Binding countersBinding(Queue countersExchangeQueue, TopicExchange countersExchange) {
        return BindingBuilder.bind(countersExchangeQueue).to(countersExchange).
        			with(BankingConstants.TOKEN_COUNTER_EXCHANGE_QUEUE);
    }
}
