package com.insa;

import com.insa.Model.Node;
import com.insa.Model.Peer;
import com.insa.ui.Login;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Hello world!
 *
 */
public class App {

    public static void main (String[] args) throws Exception {

        String ip = getAddressIP();


        Node node = new Node(new Peer(ip));

            Login login = new Login(node);
            login.display();
        }

        public static String getAddressIP() throws SocketException {

            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                int j = 0;

                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if(!i.isLoopbackAddress() && j == 1) {
                        return i.getHostAddress();
                    }
                    j++;
                }
            }
            return null;
        }

    }

