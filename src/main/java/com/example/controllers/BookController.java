package com.example.controllers;


import fr.weit.epub.DecoratedBook;
import fr.weit.epub.PublicBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by fel on 26/03/2017.
 */
@RestController
@RequestMapping("book")
@Slf4j
public class BookController {

    @Autowired
    DecoratedBook book;

    @RequestMapping("ids")
    public Collection<String> ids() {
        return book.getIds();
    }


    @RequestMapping("raw")

    public PublicBook raw() {
        return book;
    }

    @RequestMapping(value = "content/{id}")
    @Cacheable("contents")
    public String content(@PathVariable String id) throws InterruptedException {
        log.info("accessing resource...");
        Thread.sleep(5000L);
        return book.getContents().get(id);
    }
}
