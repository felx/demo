package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by fel on 07/04/2017.
 */
@Slf4j
public class LoaderDemo {

    class A extends I{

    }

    class B extends I{

    }
    class I{

    }

    class Loader<T extends I>{

        public Set<T> ts;

        protected Loader(String s, T[] is) throws IllegalAccessException, InstantiationException {

            ts = Stream.of(is).collect(Collectors.toSet());
            log.info("{}: is({}):{} ts({}):{}",s,is.getClass().getCanonicalName(),is,ts.getClass().getCanonicalName(),ts);

        }
    }

    class LoaderA extends Loader<A> {
        public LoaderA() throws InstantiationException, IllegalAccessException {
            super("a",new A[1]);
        }
    }

    class LoaderB extends Loader<B> {

        public LoaderB() throws InstantiationException, IllegalAccessException {
            super("b",new B[] {new B()});
        }

    }


    @Test
    public void go() throws IllegalAccessException, InstantiationException {
        new LoaderA();
        new LoaderB();
    }
}
