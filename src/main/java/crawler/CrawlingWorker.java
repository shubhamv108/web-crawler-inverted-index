package crawler;

import index.DocumentStore;
import utils.HttpClient;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CrawlingWorker {

    private final URLQueue queue;
    private final DocumentStore documentStore;

    public CrawlingWorker(URLQueue queue, DocumentStore documentStore) {
        this.queue = queue;
        this.documentStore = documentStore;
    }

    public void fetch() {
        URLNode node = null;
        try {
            node = this.queue.fetch();
            this.documentStore.create(node.getUrl(), "", node.depth);
        } catch (Exception ex) {
            System.out.println("Exception occured while processing url: " + node.getUrl());
        } finally {
            this.fetch();
        }
    }

}
