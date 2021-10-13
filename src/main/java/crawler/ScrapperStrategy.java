package crawler;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.Html;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.parser.HTMLParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.html.HTMLBRElement;
import utils.PatternMatcher;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScrapperStrategy {

    private static final String URL_PATTERN = "(https|http)://[a-zA-Z0-9\\./~!@#$%&*()\\-_+={}:;|\\[\\]'<>\\?/]*";

    public List<String> getURLs(String url, String content) {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
        } catch (IOException e) {
            System.out.println("Error getting page for url + " + url);
        }
        webClient.getCurrentWindow().getJobManager().removeAllJobs();
        webClient.close();
        List<HtmlAnchor> links = page.getAnchors();
        return links
                .stream()
                .map(link -> link.getHrefAttribute())
                .filter(link -> link.startsWith("http"))
                .collect(Collectors.toList());
    }

    public List<String> scrap(String url, String content) {
        List<String> result = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.add(document.title());
        Elements hTags = document.select("h1, h2, h3, h4, h5, h6");
        hTags.stream().forEach(hTag -> result.add(hTag.text()));
        Elements paragraphs = document.select("p");
        for(Element p : paragraphs)
            result.add(p.text());
        return result;
    }

}
