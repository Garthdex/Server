package practic.server.DataModels.models;


import com.sun.syndication.feed.synd.SyndEntry;
import javax.xml.bind.annotation.XmlElement;

import java.util.Date;

/**
 * Created by Antoine on 16.12.2016.
 */
public class RSSNewsModel {

    private String title;
    private String author;
    private Date publishDate;

    public RSSNewsModel() {
    }

    public RSSNewsModel(SyndEntry syndEntry) {
        this.title = syndEntry.getTitle();
        this.author = syndEntry.getAuthor();
        this.publishDate = syndEntry.getPublishedDate();
    }

    public String getTitle() {
        return title;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }
    @XmlElement
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "----------------------- \n" +
                "RSSNewsModel{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
