package practic.client;

import practic.client.DataModels.models.DataModelToXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Antoine on 16.12.2016.
 */
public class ClientMarshaller {
    private static final String PATHTOXML = "newlist.xml";

    static String getPATHTOXML() {
        return PATHTOXML;
    }

    static void marshallingNewModel() {

        RssParser reader = new RssParser();
        reader.convertToNewsModel();

        File fileToXML = new File(PATHTOXML);
        DataModelToXML marshalDataFormRss = new DataModelToXML();

        try {
            writeToXML(marshalDataFormRss, fileToXML);

            System.out.println("Creating XML success, Path " + fileToXML.getAbsolutePath());
            ZipWriter.createZip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  static void writeToXML(DataModelToXML marshalDataFormRss, File fileToXML) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataModelToXML.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(marshalDataFormRss, fileToXML);
    }
}
