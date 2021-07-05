package com.cx.tools;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

public class RxDownload {
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
        int perSize = len / splitCounter;

        Observable.range(0, splitCounter)
                .flatMap(i -> Observable.fromCallable(new ThreadDownload("downloads\\multirx.txt", perSize * i, perSize * i + perSize, path)).subscribeOn(Schedulers.io()))
                .blockingSubscribe();

    }

}
