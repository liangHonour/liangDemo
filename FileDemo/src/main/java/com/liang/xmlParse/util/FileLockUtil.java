package com.liang.xmlParse.util;

import java.io.File;

import java.io.RandomAccessFile;

import java.nio.channels.FileChannel;

import java.nio.channels.FileLock;

public class FileLockUtil {
    private static RandomAccessFile raf;
    private static FileChannel fc;
    private static FileLock fileLock;

    public static FileLock getFileLock(String fileName) throws Exception {

        File file = new File(fileName);

        raf = new RandomAccessFile(file, "rw");
        fc = raf.getChannel();
        fileLock = fc.tryLock();
        return fileLock;

    }

    public static void main(String[] args) throws Exception {
        FileLock lock = getFileLock("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\logback.xml");
        Thread.sleep(10000);
        lock.release();
        raf.close();
        fc.close();

    }

}
