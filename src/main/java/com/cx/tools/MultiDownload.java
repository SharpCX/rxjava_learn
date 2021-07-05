package com.cx.tools;

import sun.nio.ch.ThreadPool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

public class MultiDownload {
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        Downloader("https://weibo-analysis.oss-cn-shanghai.aliyuncs.com/apk-dists/test/mapping.txt");
        System.out.println("time elapse:" + (System.currentTimeMillis() - start));
    }

    public static void Downloader(String path) throws IOException, InterruptedException {
        int len = 0;
        URL url = new URL(path);
        URLConnection connectUrl = url.openConnection();
        System.out.println(len = connectUrl.getContentLength());
        System.out.println(connectUrl.getContentType());

        int splitCounter = 5;
        int perSize = len/splitCounter;

        ExecutorService executor = Executors.newFixedThreadPool(splitCounter);
        CompletionService<Void> service = new ExecutorCompletionService<Void>(executor);
        for (int i = 0; i < splitCounter; i++) {
            service.submit(new ThreadDownload("downloads\\multi.txt", perSize*i, perSize*i+perSize, path));
        }
        for (int i = 0; i < splitCounter; i++) {
            service.take();
            System.out.println("stepping");
        }
        executor.shutdown();
    }


}
