package com.vetline.app;

import java.awt.Desktop;
import java.net.URI;

public class MapUtil {

    public static void openMap(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            System.out.println("Failed to open map.");
        }
    }
}


/*Simple explaination: Mo gana ang mga google maps URL and mo show og error if invalid and URL mao ra*/