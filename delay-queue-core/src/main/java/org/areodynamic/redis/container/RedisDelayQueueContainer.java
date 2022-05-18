package org.areodynamic.redis.container;

import org.areodynamic.redis.Handler.TopicHandler;
import org.springframework.context.Lifecycle;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class RedisDelayQueueContainer implements Lifecycle {

    private volatile boolean running = false;
    private RedisTemplate redisTemplate;
    private final List<TopicHandler> handlers;

    public RedisDelayQueueContainer(List<TopicHandler> handlers) {
        this.handlers = new ArrayList<>(handlers);
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void start() {
        setRunning(true);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    protected void setRunning(boolean running) {
        this.running = running;
    }
}
