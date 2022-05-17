package org.areodynamic.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "delay.queue")
public class DelayQueueProperties {

    private ThreadModel threadModel = ThreadModel.SINGLE;

    private List<TopicProperties> topics = new ArrayList<>();


    public enum ThreadModel {
        SINGLE,
        MULTIPLE
    }

    public static class TopicProperties {

        /**
         * topic name
         */
        private String topic = "delay:queue:default";

        /**
         * time interval of polling from queue
         */
        private int pollTimeInterval = 1;

        public int getPollTimeInterval() {
            return pollTimeInterval;
        }

        public void setPollTimeInterval(int pollTimeInterval) {
            this.pollTimeInterval = pollTimeInterval;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }
    }


    public List<TopicProperties> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicProperties> topics) {
        this.topics = topics;
    }
}
