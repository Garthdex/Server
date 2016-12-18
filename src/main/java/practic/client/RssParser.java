package practic.client;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import practic.client.DataModels.repository.RSSNewsRepository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Antoine on 16.12.2016.
 */
public class RssParser {
    private URL url;

    private void newUrl() {
        System.out.println("Enter URL to RSS");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        try {
            url = new URL(scanner.nextLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private List getRssByUrl() {
        List rssByLink = null;
        newUrl();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(new XmlReader(httpURLConnection));
            rssByLink = feed.getEntries();
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
        return rssByLink;
    }

    void convertToNewsModel() {
        List listToConvert = getRssByUrl();

        for (Object item : listToConvert) {
            SyndEntry entry = (SyndEntry) item;
            RSSNewsRepository.getInstance().addToNewsList(entry);
        }
    }
}
