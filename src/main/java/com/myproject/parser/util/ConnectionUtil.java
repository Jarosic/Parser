package com.myproject.parser.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class ConnectionUtil {

    public static Document getContext(String... urls) throws InterruptedException {
        Document doc = null;
        try {
            doc = Jsoup.connect(urls[0]).get();
        } catch (IOException e) {
            System.out.println("Crush connection...");
            Thread.sleep(30000);
            try {
                doc = Jsoup.connect(urls[0]).get();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return doc;
    }
}
