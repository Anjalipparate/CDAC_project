package com.example.asim.gesticulate;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by asim on 30/1/18.
 */

public class Client extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
        private DatagramSocket socket = null;
        private FileEvent event = null;
        private String sourceFilePath = "";
        private String destinationPath = "/home/asim/";
        //private String hostName = "localHost";

        Client()
        {

        }

        Client(String filepath)
        {
            this.sourceFilePath=filepath;
        }

        public void createConnection() {
            try {

                socket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("192.168.43.190");
                byte[] incomingData = new byte[1024];
                event = getFileEvent();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(event);
                byte[] data = outputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9876);
                socket.send(sendPacket);
                System.out.println("File sent from client");
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                String response = new String(incomingPacket.getData());
                System.out.println("Response from server:" + response);
                Thread.sleep(2000);
                System.exit(0);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public FileEvent getFileEvent() {
            FileEvent fileEvent = new FileEvent();
            String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
            String path = sourceFilePath.substring(0, sourceFilePath.lastIndexOf("/") + 1);
            fileEvent.setDestinationDirectory(destinationPath);
            fileEvent.setFilename(fileName);
            fileEvent.setSourceDirectory(sourceFilePath);
            File file = new File(sourceFilePath);
            if (file.isFile()) {
                try {
                    DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                    long len = (int) file.length();
                    byte[] fileBytes = new byte[(int) len];
                    int read = 0;
                    int numRead = 0;
                    while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                        read = read + numRead;
                    }
                    fileEvent.setFileSize(len);
                    fileEvent.setFileData(fileBytes);
                    fileEvent.setStatus("Success");
                } catch (Exception e) {
                    e.printStackTrace();
                    fileEvent.setStatus("Error");
                }
            } else {
                System.out.println("path specified is not pointing to a file");
                fileEvent.setStatus("Error");
            }
            return fileEvent;
        }

}
