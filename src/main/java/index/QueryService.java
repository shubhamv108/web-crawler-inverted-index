package index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QueryService {

    final InvertedIndex index;
    final DocumentStore documentStore;

    public QueryService(InvertedIndex index, DocumentStore documentStore) {
        this.index = index;
        this.documentStore = documentStore;
    }

    public List<String> query(String query) {
        String[] words = query.split("\\s");
        List<IndexReposne> responses = null;
        for (String word : words) {
            List<IndexReposne> indexReposnes = this.index.search(word);
            responses = this.resolve(responses, indexReposnes);
        }
        if (responses != null) {
            return responses.stream()
                    .map(response -> this.documentStore.getUrl(response.getDocId()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<IndexReposne> resolve(List<IndexReposne> reposnes, List<IndexReposne> newResponses) {
        List<IndexReposne> result = new ArrayList<>();
        if (reposnes == null) return newResponses;
        for (IndexReposne newResponse : newResponses)
            for (IndexReposne reposne : reposnes)
                if (newResponse.after(reposne))
                    result.add(newResponse);
        return result;
    }

}
