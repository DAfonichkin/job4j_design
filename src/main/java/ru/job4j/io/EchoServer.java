package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str != null && str.contains("?msg=Exit")) {
                        server.close();
                        break;
                    }
                    if (str != null && str.contains("?msg=Hello")) {
                        out.write("Hello".getBytes());
                    }
                    if (str != null && str.contains("?msg=What")) {
                        for (String nextStr = in.readLine(); nextStr != null && !nextStr.isEmpty(); nextStr = in.readLine()) {
                            out.write((nextStr + "\r\n").getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Error during creating or accessing a Socket", e);
        }
    }
}