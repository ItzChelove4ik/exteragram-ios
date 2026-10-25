package org.webrtc;

import java.util.List;

/* loaded from: classes6.dex */
public interface NetworkChangeDetector {

    public enum ConnectionType extends Enum<ConnectionType> {
        private static final /* synthetic */ ConnectionType[] $VALUES = null;
        public static final ConnectionType CONNECTION_2G = null;
        public static final ConnectionType CONNECTION_3G = null;
        public static final ConnectionType CONNECTION_4G = null;
        public static final ConnectionType CONNECTION_5G = null;
        public static final ConnectionType CONNECTION_BLUETOOTH = null;
        public static final ConnectionType CONNECTION_ETHERNET = null;
        public static final ConnectionType CONNECTION_NONE = null;
        public static final ConnectionType CONNECTION_UNKNOWN = null;
        public static final ConnectionType CONNECTION_UNKNOWN_CELLULAR = null;
        public static final ConnectionType CONNECTION_VPN = null;
        public static final ConnectionType CONNECTION_WIFI = null;

        private static /* synthetic */ ConnectionType[] $values() {
            return new ConnectionType[]{CONNECTION_UNKNOWN, CONNECTION_ETHERNET, CONNECTION_WIFI, CONNECTION_5G, CONNECTION_4G, CONNECTION_3G, CONNECTION_2G, CONNECTION_UNKNOWN_CELLULAR, CONNECTION_BLUETOOTH, CONNECTION_VPN, CONNECTION_NONE};
        }

        static {
            CONNECTION_UNKNOWN = new ConnectionType("CONNECTION_UNKNOWN", 0);
            CONNECTION_ETHERNET = new ConnectionType("CONNECTION_ETHERNET", 1);
            CONNECTION_WIFI = new ConnectionType("CONNECTION_WIFI", 2);
            CONNECTION_5G = new ConnectionType("CONNECTION_5G", 3);
            CONNECTION_4G = new ConnectionType("CONNECTION_4G", 4);
            CONNECTION_3G = new ConnectionType("CONNECTION_3G", 5);
            CONNECTION_2G = new ConnectionType("CONNECTION_2G", 6);
            CONNECTION_UNKNOWN_CELLULAR = new ConnectionType("CONNECTION_UNKNOWN_CELLULAR", 7);
            CONNECTION_BLUETOOTH = new ConnectionType("CONNECTION_BLUETOOTH", 8);
            CONNECTION_VPN = new ConnectionType("CONNECTION_VPN", 9);
            CONNECTION_NONE = new ConnectionType("CONNECTION_NONE", 10);
            $VALUES = $values();
        }

        ConnectionType(String r1, int r2) {
        }

        public static ConnectionType valueOf(String r1) {
            return (ConnectionType) Enum.valueOf(ConnectionType.class, r1);
        }

        public static ConnectionType[] values() {
            return (ConnectionType[]) $VALUES.clone();
        }
    }

    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] r1) {
            this.address = r1;
        }

        @CalledByNative("IPAddress")
        private byte[] getAddress() {
            return this.address;
        }
    }

    public static class NetworkInformation {
        public final long handle;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;
        public final ConnectionType underlyingTypeForVpn;

        public NetworkInformation(String r1, ConnectionType r2, ConnectionType r3, long r4, IPAddress[] r6) {
            this.name = r1;
            this.type = r2;
            this.underlyingTypeForVpn = r3;
            this.handle = r4;
            this.ipAddresses = r6;
        }

        @CalledByNative("NetworkInformation")
        private ConnectionType getConnectionType() {
            return this.type;
        }

        @CalledByNative("NetworkInformation")
        private long getHandle() {
            return this.handle;
        }

        @CalledByNative("NetworkInformation")
        private IPAddress[] getIpAddresses() {
            return this.ipAddresses;
        }

        @CalledByNative("NetworkInformation")
        private String getName() {
            return this.name;
        }

        @CalledByNative("NetworkInformation")
        private ConnectionType getUnderlyingConnectionTypeForVpn() {
            return this.underlyingTypeForVpn;
        }
    }

    public interface Observer {
        void onConnectionTypeChanged(ConnectionType r1);

        void onNetworkConnect(NetworkInformation r1);

        void onNetworkDisconnect(long r1);

        void onNetworkPreference(List<ConnectionType> r1, int r2);
    }

    void destroy();

    List<NetworkInformation> getActiveNetworkList();

    ConnectionType getCurrentConnectionType();

    boolean supportNetworkCallback();
}
