package fr.weit.epub;

import lombok.Data;

import java.util.Collection;
import java.util.Map;

/**
 * Created by fel on 26/03/2017.
 */

public interface PublicBook {
    Collection<String> getIds();
    Map<String,String> getContents();

}
