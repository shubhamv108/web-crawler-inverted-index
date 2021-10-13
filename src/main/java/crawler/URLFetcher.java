package crawler;

import index.DocumentStore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
//            Document html = Jsoup.parse(content);
//            String title = html.title();
//            String finalContent = title;
//            for (int i = 1; i < 4; i++)
//                finalContent  += (" " + html.body().getElementsByTag("h" + i).text());
//
//            Files.write(Paths.get("/home/shubham/git/rapyuto/output"), (content +"\n---\n"+ finalContent).getBytes());
            this.documentStore.create(node.getUrl(), content, node.depth);
        } catch (Exception ex) {
            System.out.println("Exception occured while processing url: " + node.getUrl());
        } finally {
            this.fetch();
        }
    }

}
