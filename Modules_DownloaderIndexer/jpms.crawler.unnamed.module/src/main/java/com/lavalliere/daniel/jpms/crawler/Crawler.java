package com.lavalliere.daniel.jpms.crawler;

import com.lavalliere.daniel.jpms.downloader.Downloader;
import com.lavalliere.daniel.jpms.indexer.Indexer;
public class Crawler {

    static void main(String[] args) {
        String url = "http://httpforever.com";
        String htmlPage = (new Downloader()).download(url);
        new Indexer().htmlIndex(htmlPage);
        System.out.println(htmlPage);
    }
}
