package index;

import index.startegies.DocumentAnalyzingStartegy;

import java.net.URL;

public class Document {

    final int id;
    final String url;
    final String content;
    final int depth;
    boolean isProcessed;
    final DocumentAnalyzingStartegy documentAnalyzingStartegy;

    public Document(int id, String url, String content, int depth,
                    DocumentAnalyzingStartegy documentAnalyzingStartegy) {
        this.id = id;
        this.url = url;
        this.content = content;
        this.depth = depth;
        this.documentAnalyzingStartegy = documentAnalyzingStartegy;
        this.documentAnalyzingStartegy.apply(this);
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public int getDepth() {
        return depth;
    }
}
