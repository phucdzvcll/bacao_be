package com.p5k.bacao;

import com.p5k.bacao.core.pools.TimeZonePool;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class BacaoApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(TimeZonePool.GMT_VN));
    }

    public static void main(String[] args) {
        try {
            log.info("*******************************************************");
            log.info("* Main Service status: Service Started Successfully.");
            log.info("*******************************************************");
            SpringApplication.run(BacaoApplication.class, args);
        } catch (OutOfMemoryError error) {
            System.exit(1);
        }
    }

}
