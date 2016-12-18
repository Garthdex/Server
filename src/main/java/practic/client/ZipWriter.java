package practic.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Antoine on 16.12.2016.
 */
public class ZipWriter {
    private static final String ZIPNAME = "newlist.zip";
    private static final int MAX_BYTES = 1024;

    static File getClientZipFile() {
        return new File(ZIPNAME);
    }

    static void createZip() {
        String xmlFileName = ClientMarshaller.getPATHTOXML();
        File zipFile = new File(ZIPNAME);

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            addToZip(zos, xmlFileName);

            System.out.println("XML added to ZIP. Path " + zipFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addToZip(ZipOutputStream zipToWrite, String listToWrite) {
        File pathToXML = new File(listToWrite);

        try (FileInputStream writeStream = new FileInputStream(pathToXML)) {
            ZipEntry zipEntry = new ZipEntry(listToWrite);
            zipToWrite.putNextEntry(zipEntry);

            byte[] bytes = new byte[MAX_BYTES];
            int length;

            while ((length = writeStream.read(bytes)) >= 0) {
                zipToWrite.write(bytes, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
