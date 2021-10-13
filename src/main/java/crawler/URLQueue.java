package crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class URLQueue {

    private Queue<URLNode> urls = new LinkedBlockingQueue<>();

    public void add(String url, int depth) {
        try {
            this.urls.add(new URLNode(new URL(url), depth));
        } catch (MalformedURLException e) {
            System.out.println(String.format("Malformed URl: %s", url));
        }
    }

    public URLNode fetch() {
        return this.urls.poll();
    }

}
