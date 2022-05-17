package org.areodynamic.redis.container;

import org.areodynamic.redis.Handler.TopicHandler;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class RedisDelayQueueContainer {

    private RedisTemplate redisTemplate;
    private final List<TopicHandler> handlers;

    public RedisDelayQueueContainer(List<TopicHandler> handlers) {
        this.handlers = new ArrayList<>(handlers);
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
