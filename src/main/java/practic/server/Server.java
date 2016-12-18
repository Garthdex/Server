package practic.server;

import practic.server.DataModels.repository.RSSNewsRepository;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Antoine on 16.12.2016.
 */
public class Server {
    private static final int PORT = 8093;
    private static final String ZIPSERVER = "servernewlist.zip";
    private static final int MAX_BUFFER = 1024;

    static String getZIPSERVER() {
        return ZIPSERVER;
    }

    void run() {
        getServerSocket();
    }

    private void getServerSocket() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Start " + serverSocket);
            getAccept(serverSocket);

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void getAccept(ServerSocket serverSocket) throws IOException {

        while (true) {
            Socket clientSocket = serverSocket.accept();
            File file = new File(ZIPSERVER);
            getFileOnClient(clientSocket, file);
            ReadXMLFromZip.extractFileFromZip();
            RSSNewsRepository.getInstance().printRSS();
        }
    }

    private static void getFileOnClient(Socket socket, File file) {

        try(InputStream is = socket.getInputStream();
            OutputStream os = new FileOutputStream(file)) {

            byte[] buffer = new byte[MAX_BUFFER];

            for ( int count = is.read(buffer); count != -1; count = is.read(buffer) ) {
                os.write(buffer, 0, count);
            }

            os.flush();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
