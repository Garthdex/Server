package practic.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Antoine on 16.12.2016.
 */
public class ReadXMLFromZip {
    private static final int MAX_BUFFER = 1024;
    private static String pathToZip = Server.getZIPSERVER();

    static void extractFileFromZip() {
        File pathToServerZip = new File(pathToZip);

        byte[] buffer = new byte[MAX_BUFFER];

        try (ZipInputStream zipInputStream = new ZipInputStream(
                new FileInputStream(pathToServerZip))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            String entryName = zipEntry.getName();

            while (zipEntry != null) {
                int n;

                try (FileOutputStream fileOutputStream = new FileOutputStream(entryName)) {

                    while ( (n = zipInputStream.read(buffer, 0, MAX_BUFFER)) > -1) {
                        fileOutputStream.write(buffer, 0, n);
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
            }
            RSSUnmarchaller.unmarshallXML(entryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
