package index;

import index.startegies.DocumentAnalyzingStartegy;

import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DocumentStore {

    private AtomicInteger increementId = new AtomicInteger(1);
    private Map<Integer, Document> documents = new ConcurrentHashMap<>();
    private Set<String> existingURL = ConcurrentHashMap.newKeySet();
    private final DocumentAnalyzingStartegy documentAnalyzingStartegy;

    public DocumentStore(DocumentAnalyzingStartegy documentAnalyzingStartegy) {
        this.documentAnalyzingStartegy = documentAnalyzingStartegy;
    }

    public Document create(URL url, String content, int depth) {
        String urlPath = url.getPath().toLowerCase();
        if (existingURL.contains(urlPath))
            return null;
        Document document = null;
        synchronized (urlPath) {
            if (existingURL.contains(urlPath))
                return null;
            document = new Document(increementId.getAndIncrement(), url, content, depth, documentAnalyzingStartegy);
            this.documents.put(document.id, document);
            this.existingURL.add(urlPath);
        }
        return document;
    }

}
