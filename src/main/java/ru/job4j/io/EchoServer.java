package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
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
        }
    }
}