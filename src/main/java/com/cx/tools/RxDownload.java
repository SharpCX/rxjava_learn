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

    public static class ThreadDownload implements Callable<Object> {
        final String file;
        final int byteStart;
        final int byteEnd;
        final String url;

        public ThreadDownload(String file, int byteStart, int byteEnd, String url) {
            this.file = file;
            this.byteStart = byteStart;
            this.byteEnd = byteEnd;
            this.url = url;
        }

        @Override
        public Object call() throws Exception {
            int c = 10 * 1024;
            byte[] data = new byte[c];

            try {
                System.out.println("start," + byteStart + "==end:" + byteEnd);
                URL url = new URL(this.url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "bytes=" + byteStart + "-" + byteEnd);
                conn.setConnectTimeout(5000);
                int code = conn.getResponseCode();
                System.out.println(code);
                if (code == 206) {
                    InputStream input = conn.getInputStream();
                    RandomAccessFile fis = new RandomAccessFile(this.file, "rw");
                    fis.seek(byteStart);
                    while (((c = input.read(data, 0, c)) != -1)) {
                        fis.write(data, 0, c);
                    }
                    input.close();
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }
}
