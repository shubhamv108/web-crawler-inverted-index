package index.startegies;

import crawler.ScrapperStrategy;
import crawler.URLQueue;
import index.Document;

public class DocumentAnalyzingStartegy {

    final ScrapperStrategy scrapperStrategy;
    final IndexingStartegy indexingStartegy;
    final URLQueue urlQueue;
    final int maxDepth;

    public DocumentAnalyzingStartegy(ScrapperStrategy scrapperStrategy, IndexingStartegy indexingStartegy, URLQueue urlQueue, int maxDepth) {
        this.scrapperStrategy = scrapperStrategy;
        this.indexingStartegy = indexingStartegy;
        this.urlQueue = urlQueue;
        this.maxDepth = maxDepth;
    }

    public void apply(Document document) {
        this.depthSearch(document);
        this.index(document);
    }

    private void index(Document document) {
        this.indexingStartegy.apply(document);
    }

    private void depthSearch(Document document) {
        if (document.getDepth() < maxDepth) {
            this.scrapperStrategy.getURLs(document.getContent())
                    .stream()
                    .forEach(url -> this.urlQueue.add(url, document.getDepth() + 1));
        }
    }

}
