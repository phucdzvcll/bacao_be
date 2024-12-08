package com.p5k.bacao.redis.config;

import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.client.RedisJSONClient;
import io.github.dengliming.redismodule.redistimeseries.RedisTimeSeries;
import io.github.dengliming.redismodule.redistimeseries.client.RedisTimeSeriesClient;
import lombok.RequiredArgsConstructor;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    @Value("${spring.redis.lettuce.host}")
    private String host;

    @Value("${spring.redis.lettuce.port}")
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public RedisTimeSeriesClient redisTimeSeriesClient(Config config) {
        return new RedisTimeSeriesClient(config);
    }

    @Bean
    public RedisTimeSeries redisTimeSeries(RedisTimeSeriesClient redisTimeSeriesClient) {
        return redisTimeSeriesClient.getRedisTimeSeries();
    }

    @Bean
    public RedisJSONClient redisJSONClient(Config config) {
        return new RedisJSONClient(config);
    }

    @Bean
    public RedisJSON redisJSON(RedisJSONClient redisJSONClient) {
        return redisJSONClient.getRedisJSON();
    }
}