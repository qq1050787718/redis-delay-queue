package org.areodynamic.redis.config;


import org.areodynamic.redis.DelayQueueProperties;
import org.areodynamic.redis.Handler.TopicHandler;
import org.areodynamic.redis.container.RedisDelayQueueContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@EnableConfigurationProperties(DelayQueueProperties.class)
public class DelayQueueAutoConfiguration implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(DelayQueueAutoConfiguration.class);
    private final DelayQueueProperties delayQueueProperties;

    public DelayQueueAutoConfiguration(DelayQueueProperties delayQueueProperties) {
        this.delayQueueProperties = delayQueueProperties;
    }

    @Bean
    @ConditionalOnMissingBean(RedisDelayQueueContainer.class)
    @ConditionalOnBean({RedisTemplate.class, TopicHandler.class})
    public RedisDelayQueueContainer container(RedisTemplate redisTemplate, List<TopicHandler> handlers) {
        final RedisDelayQueueContainer redisDelayQueueContainer = new RedisDelayQueueContainer(handlers);
        redisDelayQueueContainer.setRedisTemplate(redisTemplate);
        return redisDelayQueueContainer;
    }

    @Override
    public void afterPropertiesSet() {
        final List<DelayQueueProperties.TopicProperties> topics = this.delayQueueProperties.getTopics();
        if (topics.isEmpty()) {
            LOGGER.info("add default topic properties");
            topics.add(new DelayQueueProperties.TopicProperties());
        }
    }
}
