package index.startegies;

import index.Document;
import index.InvertedIndex;

import java.util.Arrays;

public class IndexingStartegy {

    private final InvertedIndex index;

    public IndexingStartegy(InvertedIndex index) {
        this.index = index;
    }

    public void apply(Document document) {
        String[] lines = document.getContent().split("\\n");
        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split("\\s");
            for (int j = 0; j < words.length; j++) {
                this.index.add(words[j], document.getId(), i, j);
            }
        }
    }
}
