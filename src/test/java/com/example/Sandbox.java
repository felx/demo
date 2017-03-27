package com.example;

import fr.weit.epub.DecoratedBook;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by fel on 25/03/2017.
 */
@Slf4j
public class Sandbox {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    List<String> mots;

    public Sandbox() throws IOException {
        mots = IOUtils.readLines(this.getClass().getResourceAsStream("/liste.de.mots.francais.frgut.txt"), "latin1");
    }

    @Test
    public void test() {
        assertThat(alphabet.length(), is(26));
        assertThat(mots.size(), is(336531));

        List mots8 = mots.stream().filter((m) -> m.length() == 8).collect(Collectors.toList());

        System.out.println(mots8.get(1));

    }


    @Test
    public void readEpub() throws IOException {
        // read epub
        EpubReader epubReader = new EpubReader();
        Book book = epubReader.readEpub(new FileInputStream("./misc/Stendhal-LaChartreuseDeParme.epub"));
        log.info("title: {}",book.getTitle());
        DecoratedBook decoredBook = new DecoratedBook(book);
        decoredBook.getContents().keySet().stream().forEach((id)->log.info("id:{}",id));

    }
}
