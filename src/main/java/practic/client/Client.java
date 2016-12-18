package practic.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by Antoine on 16.12.2016.
 */
public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8093;
    private static final int MAX_BUFFER = 1024;

    void run() {
        ClientMarshaller.marshallingNewModel();
        getConnection();
    }

    private void getConnection() {

        try {
            Socket socket = new Socket(HOST, PORT);

            File clientZip = ZipWriter.getClientZipFile();
            putDataOnServer(socket, clientZip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putDataOnServer(Socket socket, File file) {
        try(OutputStream os = socket.getOutputStream();
            InputStream is = new FileInputStream(file)) {

            byte[] buffer = new byte[MAX_BUFFER];

            for (int count = -1; ( count = is.read(buffer) ) != -1; ) {
                os.write(buffer, 0, count);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
