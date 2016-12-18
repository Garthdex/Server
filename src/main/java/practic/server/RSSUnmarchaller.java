package practic.server;

import practic.server.DataModels.models.DataModelToXML;
import practic.server.DataModels.repository.RSSNewsRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Antoine on 16.12.2016.
 */
public class RSSUnmarchaller {
    static void unmarshallXML(String path) {
        File file = new File(path);
        DataModelToXML rssFromXML = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DataModelToXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            rssFromXML = (DataModelToXML) unmarshaller.unmarshal(file);
            System.out.println("Unmarsh done, Path " + file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rssFromXML != null) {
            RSSNewsRepository.getInstance().setNewsList(rssFromXML.getRssToXml());
        }
    }
}
