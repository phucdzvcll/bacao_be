package com.p5k.bacao.socket.config;

import io.github.dengliming.redismodule.redisearch.client.RediSearchClient;
import lombok.RequiredArgsConstructor;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public Config config() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port);
        //.setPassword("rpZP7FuQHBRYShkVGfZnIzTNEmFU1FdG").setConnectionPoolSize(2).setConnectionMinimumIdleSize(1);
        return config;
    }

    @Bean
    public RediSearchClient rediSearchClient(Config config) {
        return new RediSearchClient(config);
    }


//
//    @Bean
//    public RedisJSONClient redisJSONClient(Config config) {
//        return new RedisJSONClient(config);
//    }
//
//    @Bean
//    public RedisJSON redisJSON(RedisJSONClient redisJSONClient) {
//        return redisJSONClient.getRedisJSON();
//    }
//
//    @Bean
//    public RedissonClient redissonClient(Config config) {
//        return org.redisson.Redisson.create(config);
//    }

//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }


}