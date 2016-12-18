package practic.server.DataModels.repository;

import com.sun.syndication.feed.synd.SyndEntry;
import practic.server.DataModels.models.RSSNewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 16.12.2016.
 */
public class RSSNewsRepository {
    private List<RSSNewsModel> NewsList;

    private static RSSNewsRepository instance;

    private RSSNewsRepository() {
    }

    public List<RSSNewsModel> getNewsList() {
        return NewsList;
    }

    public void setNewsList(List<RSSNewsModel> newsList) {
        NewsList = newsList;
    }

    public static synchronized RSSNewsRepository getInstance() {
        if (instance == null) {
            instance = new RSSNewsRepository();
            instance.init();
        }
        return instance;
    }

    private void init() {
        NewsList = new ArrayList<RSSNewsModel>();
    }

    public void addToNewsList(SyndEntry item) {
        NewsList.add(new RSSNewsModel(item));
    }

    public void printRSS() {
        for (RSSNewsModel newModel : NewsList) {
            System.out.println(newModel.toString());
        }
    }
}
