package crawler;

import java.net.URL;

public class URLNode {

    final String url;
    final int depth;


    public URLNode(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    public String getUrl() {
        return url;
    }
}
