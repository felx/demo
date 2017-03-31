package fr.weit.epub;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fel on 26/03/2017.
 */
@Data
@Slf4j
public class DecoratedBook implements PublicBook {


    @JsonIgnore
    Book book;
    Map<String,String> contents;

    private String getContent(Resource r){
        try {
            return new String(r.getData(), r.getInputEncoding());
        } catch (IOException e) {
            return null;
        }
    };

    public static DecoratedBook fromFile(String fileName) throws IOException {
        EpubReader epubReader = new EpubReader();
        Book book = epubReader.readEpub(new FileInputStream(fileName));
        return new DecoratedBook(book);
    }
    public DecoratedBook(Book book){
        this.book = book;
        log.debug("ids :{}",book.getContents());
        this.contents = book.getContents().stream().collect(
                Collectors.toMap(
                        r -> r.getId(),
                        r -> getContent(r)
                )
        );
    }

    @Override
    public Collection<String> getIds() {
        return contents.keySet();
    }
}
