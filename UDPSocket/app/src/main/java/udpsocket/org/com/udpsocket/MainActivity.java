package udpsocket.org.com.udpsocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button sendbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbutton = (Button)findViewById(R.id.sendbutton);
        sendbutton.setOnClickListener(sendbuttonOnClickListener);
    }
    private static final int UDP_SERVER_PORT = 9800;
    View.OnClickListener sendbuttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            runUdpClient();
            finish();
        }
    };

    private void runUdpClient()
    {
        String udpMsg = "hello world from UDP client " + UDP_SERVER_PORT;
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getLocalHost();
            DatagramPacket dp;
            dp = new DatagramPacket(udpMsg.getBytes(), udpMsg.length(), serverAddr, UDP_SERVER_PORT);
            ds.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }


}
