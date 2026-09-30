package uu.app.registryclient.factory;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

@UtilityClass
public class InstanceAddressResolver {

    public String resolve(String configuredIp, String destinationHost, int destinationPort) throws IOException {

        if (configuredIp != null && !configuredIp.isBlank()) {
            return configuredIp;
        }

        return resolveFromDestination(destinationHost, destinationPort);
    }

    private String resolveFromDestination(String host, int port) throws IOException {

        InetAddress address = InetAddress.getByName(host);

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.connect(address, port);

            return socket.getLocalAddress().getHostAddress();
        }
    }
}
