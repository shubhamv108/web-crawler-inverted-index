package orchestrator;

import crawler.ScrapperStrategy;
import crawler.URLFetcher;
import crawler.URLQueue;
import index.DocumentStore;
import index.InvertedIndex;
import index.QueryService;
import index.startegies.DocumentAnalyzingStartegy;
import index.startegies.IndexingStartegy;
import readers.ConsoleReader;
import readers.InputReader;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppOrchetrator {

    private final InputReader reader;
    private final URLFetcher fetcher;
    private final QueryService queryService;

    private final int workerPoolSize = 20;
    ExecutorService fetchers = Executors.newFixedThreadPool(workerPoolSize);

    public AppOrchetrator(List<String> seedUrls) {
        this.reader = new ConsoleReader();
        InvertedIndex index = new InvertedIndex();
        URLQueue urlQueue = new URLQueue();
        DocumentAnalyzingStartegy documentAnalyzingStartegy = new DocumentAnalyzingStartegy(
                new ScrapperStrategy(),
                new IndexingStartegy(index),
                urlQueue,
                0);
        DocumentStore documentStore = new DocumentStore(documentAnalyzingStartegy);
        this.fetcher = new URLFetcher(urlQueue, documentStore);
        for (String seedUrl : seedUrls)
            urlQueue.add(seedUrl, 0);
        for (int i = 0; i < workerPoolSize; i++)
            this.fetchers.submit(() -> this.fetcher.fetch());
        this.queryService = new QueryService(index, documentStore);
    }

    public void execute() {
        Iterator<String> iterator = reader.iterator();
        while (iterator.hasNext()) {
            String input = iterator.next();
            System.out.println(this.queryService.query(input));
        }
    }

}
