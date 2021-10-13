package crawler;

import index.Document;
import index.DocumentStore;
import utils.HttpClient;

import java.io.IOException;

public class URLFetcher {

    private final URLQueue queue;
    private final DocumentStore documentStore;

    public URLFetcher(URLQueue queue, DocumentStore documentStore) {
        this.queue = queue;
        this.documentStore = documentStore;
    }

    public void fetch() throws IOException {
        URLNode node = this.queue.fetch();
        String content = HttpClient.invoke(node.url);
        this.documentStore.create(node.url, content, node.depth);
    }

}
