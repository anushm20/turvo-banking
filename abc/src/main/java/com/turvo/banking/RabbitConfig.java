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

/**
 * @author anushm
 *
 */
@Configuration
public class RabbitConfig  
{
    public static final String TOKENS_QUEUE= "tokens-queue";
    public static final String TOKENS_EXCHANGE = "tokens-exchange";
 
    @Bean
    Queue tokensQueue() {
        return QueueBuilder.durable(TOKENS_QUEUE).build();
    }
 
    @Bean
    Exchange tokensExchange() {
        return ExchangeBuilder.topicExchange(TOKENS_EXCHANGE).build();
    }
 
    @Bean
    Binding binding(Queue tokensQueue, TopicExchange tokensExchange) {
        return BindingBuilder.bind(tokensQueue).to(tokensExchange).with(TOKENS_QUEUE);
    }
}
