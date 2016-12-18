package practic.server.DataModels.models;


import practic.server.DataModels.repository.RSSNewsRepository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Antoine on 16.12.2016.
 */
@XmlRootElement
public class DataModelToXML {
    private List<RSSNewsModel> rssToXml = RSSNewsRepository.getInstance().getNewsList();

    public DataModelToXML() {

    }

    public List<RSSNewsModel> getRssToXml() {
        return rssToXml;
    }
    @XmlElement
    public void setRssToXml(List<RSSNewsModel> rssToXml) {
        this.rssToXml = rssToXml;
    }
}
