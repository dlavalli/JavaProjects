package com.lavalliere.daniel.jpms.indexer;

import com.lavalliere.daniel.jpms.downloader.Downloader;

public class Indexer {
    public String index(String url) {
        return (new Downloader()).download(url);
    }

    static void main(String args[]) {
        String url = "http://httpforever.com";
        String htmlPage = (new Indexer()).index(url);

        System.out.println(htmlPage);
    }

}