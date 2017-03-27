package com.example;

import fr.weit.epub.DecoratedBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
@EnableCaching
public class DemoApplication {


    @Bean
    DecoratedBook book() throws IOException {
        return DecoratedBook.fromFile("./misc/Stendhal-LaChartreuseDeParme.epub");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
