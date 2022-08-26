package com.liang.xmlParse.util;

import java.io.*;

public class Util {
    public static String getXmlMessage(String url) throws Exception {
        File file = new File(url);
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int temp = -1;
        try {
            is = new FileInputStream(file);
            while ((temp = is.read(buffer)) != -1) {
                bos.write(buffer, 0, temp);
            }
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
