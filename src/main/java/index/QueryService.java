package index;

import java.util.ArrayList;
import java.util.List;

public class QueryService {

    final InvertedIndex index;

    public QueryService(InvertedIndex index) {
        this.index = index;
    }

    public List<String> query(String word) {
        List<IndexReposne> reposnes =  this.index.search(word);

    }

}
