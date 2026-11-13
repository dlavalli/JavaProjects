package com.lavalliere.daniel.jpms.indexer;

import com.lavalliere.daniel.jpms.downloader.Downloader;

public class Indexer {

    public String downloaderIndex(String url) {
        return (new Downloader()).download(url);
    }

    public void htmlIndex(String htmlPage) {
        System.out.println("Indexing html...");
        System.out.println(htmlPage);
    }

    static void main(String[] args) {
        String url = "http://httpforever.com";
        String htmlPage = (new Indexer()).downloaderIndex(url);

        System.out.println(htmlPage);
    }

}