package index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class InvertedIndex {

    private final Map<String, Map<Integer, Map<Integer, ConcurrentSkipListSet<Integer>>>> index = new ConcurrentHashMap<>();

    public void add(String word, Integer docId, int row, int pos) {
        index.putIfAbsent(word, new ConcurrentHashMap<>());
        Map<Integer, Map<Integer, ConcurrentSkipListSet<Integer>>> docs = index.get(word);
        docs.putIfAbsent(docId, new ConcurrentHashMap<>());
        Map<Integer, ConcurrentSkipListSet<Integer>> rows = docs.get(docId);
        rows.putIfAbsent(row, new ConcurrentSkipListSet<>());
        rows.get(row).add(pos);
    }

    public List<IndexReposne> search(String word) {
        List<IndexReposne> reposnes = new ArrayList<>();
        Map<Integer, Map<Integer, ConcurrentSkipListSet<Integer>>> docs = index.get(word);
        if (docs != null && !docs.isEmpty()) {
            for (Integer docId : docs.keySet()) {
                for (Integer row : docs.get(docId).keySet()) {
                    for (Integer pos : docs.get(docId).get(row)) {
                        reposnes.add(new IndexReposne(docId, row, pos));
                    }
                }
            }
        }
        return reposnes;
    }

}
