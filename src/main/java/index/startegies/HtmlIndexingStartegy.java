package index.startegies;

import index.Document;
import index.InvertedIndex;

import java.util.Arrays;
import java.util.List;

public class HtmlIndexingStartegy {

    private final InvertedIndex index;

    public HtmlIndexingStartegy(InvertedIndex index) {
        this.index = index;
    }

    public void apply(List<String> lines, Document document) {
        for (int i = 0; i < lines.size(); i++) {
            String[] words = lines.get(i).split("\\s");
            for (int j = 0; j < words.length; j++) {
                this.index.add(words[j], document.getId(), i, j);
            }
        }
    }
}
