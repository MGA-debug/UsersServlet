package ru.appline.logic.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class RequestReader {

    public static StringBuffer read(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return jb;
    }
}
