package com.github.cimsbioko.validator;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Throwables {
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
