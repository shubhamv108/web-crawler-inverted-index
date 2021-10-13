package crawler;

import index.DocumentStore;
import utils.HttpClient;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class URLFetcher {

    private final URLQueue queue;
    private final DocumentStore documentStore;

    public URLFetcher(URLQueue queue, DocumentStore documentStore) {
        this.queue = queue;
        this.documentStore = documentStore;
    }

    public void fetch() {
        URLNode node = null;
        try {
            node = this.queue.fetch();
            System.out.println("Processing url: " + node.getUrl());
            String content = HttpClient.invoke(new URL(node.getUrl()));
            Files.write(Paths.get("/home/shubham/git/rapyuto/output"), content.getBytes());
            this.documentStore.create(node.getUrl(), content, node.depth);
        } catch (Exception ex) {
            System.out.println("Exception occured while processing url: " + node.getUrl());
        } finally {
            this.fetch();
        }
    }

}
