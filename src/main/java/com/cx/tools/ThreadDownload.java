package com.cx.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class ThreadDownload implements Callable<Void> {
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
    public Void call() throws Exception {
        int c = 10*1024;
        byte[] data = new byte[c];

        try {
            System.out.println("start,"+byteStart+"==end:"+byteEnd);
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Range", "bytes=" + byteStart + "-" + byteEnd);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();

            if(code==206){
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
        return null;
    }
}
