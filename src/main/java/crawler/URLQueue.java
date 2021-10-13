package crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class URLQueue {

    private Queue<URLNode> urls = new LinkedBlockingQueue<>();

    public void add(String url, int depth) {
        this.urls.add(new URLNode(url, depth));
    }

    public URLNode fetch() {
        return this.urls.poll();
    }

}
