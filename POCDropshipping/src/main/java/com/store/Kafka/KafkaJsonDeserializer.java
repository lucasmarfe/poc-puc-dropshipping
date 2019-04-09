package com.store.Kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class KafkaJsonDeserializer<T> implements Deserializer {

    private Logger logger = LogManager.getLogger(this.getClass());

    private Class <T> type;

    public KafkaJsonDeserializer(Class type) {
        this.type = type;
    }

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        try {
            obj = mapper.readValue(bytes, type);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        return obj;
    }

    @Override
    public void close() {

    }
}
