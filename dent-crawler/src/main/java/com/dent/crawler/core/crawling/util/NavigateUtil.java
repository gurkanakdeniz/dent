package com.dent.crawler.core.crawling.util;

import java.util.concurrent.TimeUnit;

import com.dent.crawler.core.linkedin.core.WebUnit;

public class NavigateUtil {
    
    public static void scroll(WebUnit webUnit) {
        scroll(webUnit, 100);
    }

    public static void scroll(WebUnit webUnit, int tryCount) {
        Long height = null;
        Long newHeight = null;

        int count = tryCount;
        do {
            count--;
            height = getHeight(webUnit);
            scrollBottom(webUnit);
            
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // ignore
            }

            webUnit.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

            newHeight = getHeight(webUnit);
        } while (newHeight != null && newHeight > height && count > 0);

        webUnit.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

        scrollCenter(webUnit);

        webUnit.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }

    public static Long getHeight(WebUnit webUnit) {
        return (Long) webUnit.executeScript("return document.body.scrollHeight");
    }

    public static void scrollBottom(WebUnit webUnit) {
        webUnit.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
    }

    public static void scrollCenter(WebUnit webUnit) {
        webUnit.executeScript("window.scrollTo({ top: document.body.scrollHeight/2, behavior: 'smooth' });");
    }
    
    public static void zoomIn(WebUnit webUnit) {
        try {
            webUnit.executeScript("document.body.style.webkitTransform = 'scale(1)';");
        } catch (Exception e) {
            // ignore
        }
        
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // ignore
        }
    }

    public static void zoomOut(WebUnit webUnit) {
        try {
            webUnit.executeScript("document.body.style.webkitTransform = 'scale(0.8)';");
        } catch (Exception e) {
            // ignore
        }
        
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // ignore
        }
        
        try {
            webUnit.executeScript("document.body.style.webkitTransform = 'scale(0.2)';");
        } catch (Exception e) {
            // ignore
        }
    }
}
