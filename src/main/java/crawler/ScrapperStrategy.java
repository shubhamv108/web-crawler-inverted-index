package crawler;

import utils.PatternMatcher;

import java.util.List;
import java.util.stream.Collectors;

public class ScrapperStrategy {

    private static final String URL_PATTERN = "(https|http)://[a-zA-Z0-9/~!@#$%&*()\\-_+={}:;|\\[\\]'<>\\?/]*";

    public List<String> getURLs(String content) {
        return PatternMatcher.match(content, URL_PATTERN)
                .stream()
                .map(url -> url.replaceAll("\\s", ""))
                .collect(Collectors.toList());
    }

}
