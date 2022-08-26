package com.liang.file;

import java.io.InputStream;
import java.io.OutputStream;

public class FileHandling {
    private InputStream inputStream;
    private OutputStream outputStream;

    public FileHandling() {
    }

    public FileHandling(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void fileUpload() {

    }
}
