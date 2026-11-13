package com.lavalliere.daniel.jpms.indexer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.lavalliere.daniel.jpms.downloader.Downloader;

public class Indexer {

    public String downloaderIndex(String url) {
        return (new Downloader()).download(url);
    }

    public void htmlIndex(String htmlPage) {
        System.out.println("Indexing html...");
        // System.out.println(htmlPage);
        System.out.println("The html page name is: " + getTitle(htmlPage));
    }

    private String getTitle(String htmlPage) {
        Document document = Jsoup.parse(htmlPage);
        return document.title();
    }

    static void main(String[] args) {
        String url = "http://httpforever.com";
        String htmlPage = (new Indexer()).downloaderIndex(url);

        System.out.println(htmlPage);
    }

}