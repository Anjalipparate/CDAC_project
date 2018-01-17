package socket.org.com.socket;

import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import java.lang.*;


import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    TextView state, response;
    EditText address;
    EditText port;
    Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = (EditText) findViewById(R.id.address);
        port = (EditText) findViewById(R.id.portno);
        connect = (Button) findViewById(R.id.connect);
        state = (TextView) findViewById(R.id.state);
        response = (TextView) findViewById(R.id.response);

        connect.setOnClickListener(connectOnClickListener);

        UdpClientHandler udpClientHandler
                = new UdpClientHandler(this);
    }


    View.OnClickListener connectOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v1) {

            UdpClientHandler udpClientThread = new UdpClientThread(
                    address.getText().toString(), Integer.parseInt(port.getText().toString()),udpClientHandler);

            udpClientThread.start();

            connect.setEnabled(false);

        }
    };

    private void updateState(String state) {
        state.setText(getText(state));
    }

    private void updateRxMsg(String rxmsg) {
        textViewRx.append(rxmsg + "\n");
    }

    private void clientEnd() {
        UdpClientThread = null;
        state.setText("clientEnd()");
        connect.setEnabled(true);

    }

    public static class UdpClientHandler extends Handler {
        public static final int UPDATE_STATE = 0;
        public static final int UPDATE_MSG = 1;
        public static final int UPDATE_END = 2;
        private MainActivity parent;

        public UdpClientHandler(MainActivity parent) {
            super();
            this.parent = parent;
        }

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case UPDATE_STATE:
                    parent.updateState((String) msg.obj);
                    break;
                case UPDATE_MSG:
                    parent.updateRxMsg((String) msg.obj);
                    break;
                case UPDATE_END:
                    parent.clientEnd();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
