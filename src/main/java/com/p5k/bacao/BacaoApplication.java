package com.p5k.bacao;

import com.p5k.bacao.http.core.pools.TimeZonePool;
import com.p5k.bacao.socket.config.SocketService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class BacaoApplication implements CommandLineRunner {
    private final SocketService socketService;

    public BacaoApplication(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run(String... args) {
        socketService.start();
    }

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
