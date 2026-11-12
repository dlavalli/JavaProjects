package com.lavalliere.daniel.jpms.downloader;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.lavalliere.daniel.jpms.util.HttpConnect;

public class Downloader {
    public String download(String url) {
        String htmlPage = null;

        try {
            htmlPage = HttpConnect.download(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return htmlPage;
    }

    public static void main(String args[]) {

    }
}