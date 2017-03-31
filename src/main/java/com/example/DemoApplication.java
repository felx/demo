package com.example;

import fr.weit.epub.DecoratedBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableCaching
@Slf4j
public class DemoApplication {


    @Bean
    DecoratedBook book() throws IOException {
        return DecoratedBook.fromFile("./misc/Stendhal-LaChartreuseDeParme.epub");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Value("${app.name}")
    String appName;
    @Value("${env}")
    String env;

    @PostConstruct
    public void yeld(){
        log.info("env: {}, application name : {}",env,appName);
    }
}
