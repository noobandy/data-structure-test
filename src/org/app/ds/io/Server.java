/**
 * @filenameName:org.app.ds.io.Server.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 12:04:46 PM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * @className:org.app.ds.io.Server.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 12:04:46 PM
 */
public class Server {

    private ServerSocket serverSocket;

    private int port;

    /**
     * @param port
     */
    public Server(int port) {
        super();
        this.port = port;
    }

    public void start() throws IOException {

        serverSocket = new ServerSocket(port);

        final Set<Socket> activeConnections = new HashSet<Socket>();

        while (true) {
            final Socket socket = serverSocket.accept();
            activeConnections.add(socket);
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try {
                            BufferedReader bufferedReader = new BufferedReader(
                                    new InputStreamReader(socket
                                            .getInputStream()));

                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line != null) {
                                    for (Socket socket : activeConnections) {
                                        BufferedWriter bufferedWriter = new BufferedWriter(
                                                new OutputStreamWriter(socket
                                                        .getOutputStream()));
                                        bufferedWriter.write(line);
                                        bufferedWriter.flush();
                                        bufferedReader.close();
                                    }
                                }
                            }
                        }
                        catch (IOException e) {

                            e.printStackTrace();
                        }

                    }

                }
            });
            thread.start();
        }

    }

    public void stop() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }

        System.exit(1);
    }
}
