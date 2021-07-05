package com.cx.tools;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SingleDownload {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Downloader("https://weibo-analysis.oss-cn-shanghai.aliyuncs.com/apk-dists/test/mapping.txt");
        System.out.println("time elapse:"+(System.currentTimeMillis()-start));
    }

    public static void Downloader(String path) throws IOException {
        int len = 0;
        URL url = new URL(path);
        URLConnection connectUrl = url.openConnection();
        System.out.println(len = connectUrl.getContentLength());
        System.out.println(connectUrl.getContentType());

        InputStream input = connectUrl.getInputStream();
        int c = 1024*10;
        System.out.println("=== Content ===");
        File file = new File("downloads", "single.txt");
        System.out.println(file.getAbsoluteFile());
        file.createNewFile();
        FileOutputStream fis = new FileOutputStream(file);
        byte[] data = new byte[c];
        while (((c = input.read(data, 0, c)) != -1)) {
            fis.write(data, 0, c);
        }
        input.close();
        fis.close();
    }

    public static void join(String FilePath) {
        long leninfile=0, leng=0;
        int count=1, data=0;
        try {
            File filename = new File(FilePath);
            RandomAccessFile outfile = new RandomAccessFile(filename,"rw");
            while(true) {
                filename = new File(FilePath + count + ".sp");
                if (filename.exists()) {
                    RandomAccessFile infile = new RandomAccessFile(filename,"r");
                    data=infile.read();
                    while(data != -1) {
                        outfile.write(data);
                        data=infile.read();
                    }
                    leng++;
                    infile.close();
                    count++;
                } else break;
            }
            outfile.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
