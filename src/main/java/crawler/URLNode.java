package crawler;

import java.net.URL;

public class URLNode {

    final URL url;
    final int depth;


    public URLNode(URL url, int depth) {
        this.url = url;
        this.depth = depth;
    }
}
