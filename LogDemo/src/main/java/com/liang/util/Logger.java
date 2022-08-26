package com.liang.util;

import java.util.HashMap;
import java.util.Map;

public class Logger {
    private static final Map<String, org.slf4j.Logger> loggerMap = new HashMap<>();

    public static org.slf4j.Logger getLogger(String name) {
        if (loggerMap.containsKey(name)) {
            return loggerMap.get(name);
        }
    }
}
