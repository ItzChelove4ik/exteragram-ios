package org.webrtc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.webrtc.NetworkChangeDetector;

/* loaded from: classes6.dex */
public class NetworkMonitorAutoDetect extends BroadcastReceiver implements NetworkChangeDetector {
    private static final long INVALID_NET_ID = -1;
    private static final String TAG = "NetworkMonitorAutoDetect";
    private final ConnectivityManager.NetworkCallback allNetworkCallback;
    private NetworkChangeDetector.ConnectionType connectionType;
    private ConnectivityManagerDelegate connectivityManagerDelegate;
    private final Context context;
    private final IntentFilter intentFilter;
    private boolean isRegistered;
    private final ConnectivityManager.NetworkCallback mobileNetworkCallback;
    private final NetworkChangeDetector.Observer observer;
    private WifiDirectManagerDelegate wifiDirectManagerDelegate;
    private WifiManagerDelegate wifiManagerDelegate;
    private String wifiSSID;

    public static class ConnectivityManagerDelegate {
        private final ConnectivityManager connectivityManager;

        /* renamed from: -$$Nest$mnetworkToInfo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ NetworkChangeDetector.NetworkInformation m22367$$Nest$mnetworkToInfo(ConnectivityManagerDelegate r0, Network r1) {
            return r0.networkToInfo(r1);
        }

        public ConnectivityManagerDelegate() {
            this.connectivityManager = null;
        }

        public ConnectivityManagerDelegate(Context r2) {
            this.connectivityManager = (ConnectivityManager) r2.getSystemService("connectivity");
        }

        private NetworkState getNetworkState(NetworkInfo r8) {
            if (r8 == null) goto L9;
            if (r8.isConnected() == false) goto L9;
            return new NetworkState(true, r8.getType(), r8.getSubtype(), -1, -1);
        L9:
            return new NetworkState(false, -1, -1, -1, -1);
        }

        @SuppressLint({"NewApi"})
        private NetworkChangeDetector.NetworkInformation networkToInfo(Network r12) {
            if (r12 == null) goto L26;
            ConnectivityManager r1 = this.connectivityManager;
            if (r1 == null) goto L26;
            LinkProperties r13 = r1.getLinkProperties(r12);
            if (r13 != null) goto L12;
            Logging.w(NetworkMonitorAutoDetect.TAG, "Detected unknown network: " + r12.toString());
            return null;
        L12:
            if (r13.getInterfaceName() != null) goto L15;
            Logging.w(NetworkMonitorAutoDetect.TAG, "Null interface name for network " + r12.toString());
            return null;
        L15:
            NetworkState r3 = getNetworkState(r12);
            NetworkChangeDetector.ConnectionType r6 = NetworkMonitorAutoDetect.getConnectionType(r3);
            if (r6 != NetworkChangeDetector.ConnectionType.CONNECTION_NONE) goto L20;
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + r12.toString() + " is disconnected");
            return null;
        L20:
            if (r6 != NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN) goto L22;
        L23:
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + r12.toString() + " connection type is " + r6 + " because it has type " + r3.getNetworkType() + " and subtype " + r3.getNetworkSubType());
        L25:
            return new NetworkChangeDetector.NetworkInformation(r13.getInterfaceName(), r6, NetworkMonitorAutoDetect.m22365$$Nest$smgetUnderlyingConnectionTypeForVpn(r3), NetworkMonitorAutoDetect.m22366$$Nest$smnetworkToNetId(r12), getIPAddresses(r13));
        L22:
            if (r6 != NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN_CELLULAR) goto L25;
        L26:
            return null;
        }

        public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
            if (supportNetworkCallback() == true) goto L6;
            return null;
        L6:
            ArrayList r0 = new ArrayList();
            Network[] r1 = getAllNetworks();
            int r2 = r1.length;
            int r3 = 0;
        L7:
            if (r3 >= r2) goto L12;
            NetworkChangeDetector.NetworkInformation r4 = networkToInfo(r1[r3]);
            if (r4 == null) goto L11;
            r0.add(r4);
        L11:
            r3 = r3 + 1;
            goto L7
        L12:
            return r0;
        }

        @SuppressLint({"NewApi"})
        public Network[] getAllNetworks() {
            ConnectivityManager r0 = this.connectivityManager;
            if (r0 != null) goto L7;
            return new Network[0];
        L7:
            return r0.getAllNetworks();
        }

        @SuppressLint({"NewApi"})
        public long getDefaultNetId() {
            if (supportNetworkCallback() == true) goto L5;
            return -1;
        L5:
            NetworkInfo r0 = this.connectivityManager.getActiveNetworkInfo();
            if (r0 != null) goto L8;
            return -1;
        L8:
            Network[] r3 = getAllNetworks();
            int r4 = r3.length;
            int r5 = 0;
            long r6 = -1;
        L9:
            if (r5 >= r4) goto L23;
            Network r8 = r3[r5];
            if (hasInternetCapability(r8) == false) goto L22;
            NetworkInfo r9 = this.connectivityManager.getNetworkInfo(r8);
            if (r9 == null) goto L22;
            if (r9.getType() != r0.getType()) goto L22;
            if (r6 != (-1)) goto L20;
            r6 = NetworkMonitorAutoDetect.m22366$$Nest$smnetworkToNetId(r8);
            goto L22
        L20:
            GlShader$$ExternalSyntheticBUOutline1.m("Multiple connected networks of same type are not supported.");
            return 0;
        L22:
            r5 = r5 + 1;
            goto L9
        L23:
            return r6;
        }

        @SuppressLint({"NewApi"})
        public NetworkChangeDetector.IPAddress[] getIPAddresses(LinkProperties r4) {
            NetworkChangeDetector.IPAddress[] r3 = new NetworkChangeDetector.IPAddress[r4.getLinkAddresses().size()];
            Iterator<LinkAddress> r42 = r4.getLinkAddresses().iterator();
            int r0 = 0;
        L4:
            if (r42.hasNext() == false) goto L6;
            r3[r0] = new NetworkChangeDetector.IPAddress(r42.next().getAddress().getAddress());
            r0 = r0 + 1;
            goto L4
        L6:
            return r3;
        }

        public NetworkState getNetworkState() {
            ConnectivityManager r0 = this.connectivityManager;
            if (r0 != null) goto L7;
            return new NetworkState(false, -1, -1, -1, -1);
        L7:
            return getNetworkState(r0.getActiveNetworkInfo());
        }

        @SuppressLint({"NewApi"})
        public NetworkState getNetworkState(Network r11) {
            if (r11 == null) goto L36;
            ConnectivityManager r0 = this.connectivityManager;
            if (r0 == null) goto L36;
            NetworkInfo r02 = r0.getNetworkInfo(r11);
            if (r02 != null) goto L11;
            Logging.w(NetworkMonitorAutoDetect.TAG, "Couldn't retrieve information from network " + r11.toString());
            return new NetworkState(false, -1, -1, -1, -1);
        L11:
            if (r02.getType() == 17) goto L22;
            NetworkCapabilities r112 = this.connectivityManager.getNetworkCapabilities(r11);
            if (r112 == null) goto L20;
            if (r112.hasTransport(4) == false) goto L20;
            return new NetworkState(r02.isConnected(), 17, -1, r02.getType(), r02.getSubtype());
        L20:
            return getNetworkState(r02);
        L22:
            if (r02.getType() != 17) goto L34;
            if (r11.equals(this.connectivityManager.getActiveNetwork()) == false) goto L32;
            NetworkInfo r10 = this.connectivityManager.getActiveNetworkInfo();
            if (r10 == null) goto L32;
            if (r10.getType() == 17) goto L32;
            return new NetworkState(r02.isConnected(), 17, -1, r10.getType(), r10.getSubtype());
        L32:
            return new NetworkState(r02.isConnected(), 17, -1, -1, -1);
        L34:
            return getNetworkState(r02);
        L36:
            return new NetworkState(false, -1, -1, -1, -1);
        }

        @SuppressLint({"NewApi"})
        public boolean hasInternetCapability(Network r2) {
            ConnectivityManager r1 = this.connectivityManager;
            if (r1 != null) goto L5;
            return false;
        L5:
            NetworkCapabilities r12 = r1.getNetworkCapabilities(r2);
            if (r12 != null) goto L8;
        L11:
            return false;
        L8:
            if (r12.hasCapability(12) == false) goto L11;
            return true;
        }

        @SuppressLint({"NewApi"})
        public void registerNetworkCallback(ConnectivityManager.NetworkCallback r3) {
            this.connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), r3);
        }

        @SuppressLint({"NewApi"})
        public void releaseCallback(ConnectivityManager.NetworkCallback r3) {
            if (supportNetworkCallback() == false) goto L6;
            Logging.d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
            this.connectivityManager.unregisterNetworkCallback(r3);
            return;
        }

        @SuppressLint({"NewApi"})
        public void requestMobileNetwork(ConnectivityManager.NetworkCallback r4) {
            NetworkRequest.Builder r0 = new NetworkRequest.Builder();
            r0.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(r0.build(), r4);
        }

        public boolean supportNetworkCallback() {
            if (this.connectivityManager == null) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class NetworkState {
        private final boolean connected;
        private final int subtype;
        private final int type;
        private final int underlyingNetworkSubtypeForVpn;
        private final int underlyingNetworkTypeForVpn;

        public NetworkState(boolean r1, int r2, int r3, int r4, int r5) {
            this.connected = r1;
            this.type = r2;
            this.subtype = r3;
            this.underlyingNetworkTypeForVpn = r4;
            this.underlyingNetworkSubtypeForVpn = r5;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }

        public int getNetworkType() {
            return this.type;
        }

        public int getUnderlyingNetworkSubtypeForVpn() {
            return this.underlyingNetworkSubtypeForVpn;
        }

        public int getUnderlyingNetworkTypeForVpn() {
            return this.underlyingNetworkTypeForVpn;
        }

        public boolean isConnected() {
            return this.connected;
        }
    }

    @SuppressLint({"NewApi"})
    public class SimpleNetworkCallback extends ConnectivityManager.NetworkCallback {
        final /* synthetic */ NetworkMonitorAutoDetect this$0;

        private SimpleNetworkCallback(NetworkMonitorAutoDetect r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ SimpleNetworkCallback(NetworkMonitorAutoDetect r1, NetworkMonitorAutoDetectIA r2) {
            this(r1);
        }

        private void onNetworkChanged(Network r2) {
            NetworkChangeDetector.NetworkInformation r22 = ConnectivityManagerDelegate.m22367$$Nest$mnetworkToInfo(NetworkMonitorAutoDetect.m22363$$Nest$fgetconnectivityManagerDelegate(this.this$0), r2);
            if (r22 == null) goto L6;
            NetworkMonitorAutoDetect.m22364$$Nest$fgetobserver(this.this$0).onNetworkConnect(r22);
            return;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network r3) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network becomes available: " + r3.toString());
            onNetworkChanged(r3);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network r3, NetworkCapabilities r4) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "capabilities changed: " + r4.toString());
            onNetworkChanged(r3);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network r2, LinkProperties r3) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "link properties changed");
            onNetworkChanged(r2);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network r2, int r3) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + r2.toString() + " is about to lose in " + r3 + "ms");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network r3) {
            Logging.d(NetworkMonitorAutoDetect.TAG, "Network " + r3.toString() + " is disconnected");
            NetworkMonitorAutoDetect.m22364$$Nest$fgetobserver(this.this$0).onNetworkDisconnect(NetworkMonitorAutoDetect.m22366$$Nest$smnetworkToNetId(r3));
        }
    }

    public static class WifiDirectManagerDelegate extends BroadcastReceiver {
        private static final int WIFI_P2P_NETWORK_HANDLE = 0;
        private final Context context;
        private final NetworkChangeDetector.Observer observer;
        private NetworkChangeDetector.NetworkInformation wifiP2pNetworkInfo;

        public static /* synthetic */ void $r8$lambda$6gq2ENfpxuwxkjTLhcSsLus4oHM(WifiDirectManagerDelegate r0, WifiP2pGroup r1) {
            r0.lambda$new$0(r1);
        }

        public WifiDirectManagerDelegate(NetworkChangeDetector.Observer r3, Context r4) {
            this.context = r4;
            this.observer = r3;
            IntentFilter r32 = new IntentFilter();
            r32.addAction("android.net.wifi.p2p.STATE_CHANGED");
            r32.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
            int r0 = Build.VERSION.SDK_INT;
            if (r0 < 33) goto L5;
            r4.registerReceiver(this, r32, 4);
        L7:
            if (r0 <= 28) goto L10;
            WifiP2pManager r33 = (WifiP2pManager) r4.getSystemService("wifip2p");
            r33.requestGroupInfo(r33.initialize(r4, r4.getMainLooper(), null), new NetworkMonitorAutoDetect$WifiDirectManagerDelegate$$ExternalSyntheticLambda0(this));
            return;
        L10:
            return;
        L5:
            r4.registerReceiver(this, r32);
            goto L7
        }

        private /* synthetic */ void lambda$new$0(WifiP2pGroup r1) {
            onWifiP2pGroupChange(r1);
        }

        private void onWifiP2pGroupChange(WifiP2pGroup r10) {
            if (r10 != null) goto L4;
            return;
        L4:
            if (r10.getInterface() == null) goto L20;
            ArrayList r0 = Collections.list(NetworkInterface.getByName(r10.getInterface()).getInetAddresses());
            NetworkChangeDetector.IPAddress[] r8 = new NetworkChangeDetector.IPAddress[r0.size()];
            int r1 = 0;
        L9:
            if (r1 >= r0.size()) goto L11;
            r8[r1] = new NetworkChangeDetector.IPAddress(((InetAddress) r0.get(r1)).getAddress());
            r1 = r1 + 1;
            goto L9
        L11:
            NetworkChangeDetector.NetworkInformation r2 = new NetworkChangeDetector.NetworkInformation(r10.getInterface(), NetworkChangeDetector.ConnectionType.CONNECTION_WIFI, NetworkChangeDetector.ConnectionType.CONNECTION_NONE, 0, r8);
            this.wifiP2pNetworkInfo = r2;
            this.observer.onNetworkConnect(r2);
            return;
        L13:
            e = move-exception;
            Logging.e(NetworkMonitorAutoDetect.TAG, "Unable to get WifiP2p network interface", e);
            return;
        }

        private void onWifiP2pStateChange(int r3) {
            if (r3 != 1) goto L6;
            this.wifiP2pNetworkInfo = null;
            this.observer.onNetworkDisconnect(0);
            return;
        }

        public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
            NetworkChangeDetector.NetworkInformation r0 = this.wifiP2pNetworkInfo;
            if (r0 == null) goto L7;
            return Collections.singletonList(r0);
        L7:
            return Collections.EMPTY_LIST;
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"InlinedApi"})
        public void onReceive(Context r2, Intent r3) {
            if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(r3.getAction()) == false) goto L7;
            onWifiP2pGroupChange((WifiP2pGroup) r3.getParcelableExtra("p2pGroupInfo"));
            return;
        L7:
            if ("android.net.wifi.p2p.STATE_CHANGED".equals(r3.getAction()) == false) goto L10;
            onWifiP2pStateChange(r3.getIntExtra("wifi_p2p_state", 0));
            return;
        }

        public void release() {
            this.context.unregisterReceiver(this);
        }
    }

    public static class WifiManagerDelegate {
        private final Context context;

        public WifiManagerDelegate() {
            this.context = null;
        }

        public WifiManagerDelegate(Context r1) {
            this.context = r1;
        }

        public String getWifiSSID() {
            int r0 = Build.VERSION.SDK_INT;
            Context r4 = this.context;
            if (r0 < 33) goto L5;
            Intent r42 = r4.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"), 4);
        L6:
            if (r42 == null) goto L12;
            WifiInfo r43 = (WifiInfo) r42.getParcelableExtra("wifiInfo");
            if (r43 == null) goto L14;
            String r44 = r43.getSSID();
            if (r44 == null) goto L15;
            return r44;
        L15:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            r42 = r4.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            goto L6
        }
    }

    /* renamed from: -$$Nest$fgetconnectivityManagerDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConnectivityManagerDelegate m22363$$Nest$fgetconnectivityManagerDelegate(NetworkMonitorAutoDetect r0) {
        return r0.connectivityManagerDelegate;
    }

    /* renamed from: -$$Nest$fgetobserver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NetworkChangeDetector.Observer m22364$$Nest$fgetobserver(NetworkMonitorAutoDetect r0) {
        return r0.observer;
    }

    /* renamed from: -$$Nest$smgetUnderlyingConnectionTypeForVpn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NetworkChangeDetector.ConnectionType m22365$$Nest$smgetUnderlyingConnectionTypeForVpn(NetworkState r0) {
        return getUnderlyingConnectionTypeForVpn(r0);
    }

    /* renamed from: -$$Nest$smnetworkToNetId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22366$$Nest$smnetworkToNetId(Network r2) {
        return networkToNetId(r2);
    }

    @SuppressLint({"NewApi"})
    public NetworkMonitorAutoDetect(NetworkChangeDetector.Observer r3, Context r4) {
        this.observer = r3;
        this.context = r4;
        this.connectivityManagerDelegate = new ConnectivityManagerDelegate(r4);
        this.wifiManagerDelegate = new WifiManagerDelegate(r4);
        NetworkState r0 = this.connectivityManagerDelegate.getNetworkState();
        this.connectionType = getConnectionType(r0);
        this.wifiSSID = getWifiSSID(r0);
        this.intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        if (PeerConnectionFactory.fieldTrialsFindFullName("IncludeWifiDirect").equals(PeerConnectionFactory.TRIAL_ENABLED) == false) goto L5;
        this.wifiDirectManagerDelegate = new WifiDirectManagerDelegate(r3, r4);
    L5:
        registerReceiver();
        if (this.connectivityManagerDelegate.supportNetworkCallback() == false) goto L13;
        ConnectivityManager.NetworkCallback r32 = new ConnectivityManager.NetworkCallback();
        this.connectivityManagerDelegate.requestMobileNetwork(r32);     // Catch: SecurityException -> L10
    L11:
        this.mobileNetworkCallback = r32;
        SimpleNetworkCallback r33 = new SimpleNetworkCallback(this, null);
        this.allNetworkCallback = r33;
        this.connectivityManagerDelegate.registerNetworkCallback(r33);
        return;
    L10:
        Logging.w(TAG, "Unable to obtain permission to request a cellular network.");
        r32 = null;
        goto L11
    L13:
        this.mobileNetworkCallback = null;
        this.allNetworkCallback = null;
    }

    private void connectionTypeChanged(NetworkState r3) {
        NetworkChangeDetector.ConnectionType r0 = getConnectionType(r3);
        String r32 = getWifiSSID(r3);
        if (r0 == this.connectionType) goto L5;
    L7:
        this.connectionType = r0;
        this.wifiSSID = r32;
        Logging.d(TAG, "Network connectivity changed, type is: " + this.connectionType);
        this.observer.onConnectionTypeChanged(r0);
        return;
    L5:
        if (r32.equals(this.wifiSSID) == false) goto L7;
    }

    public static NetworkChangeDetector.ConnectionType getConnectionType(NetworkState r2) {
        return getConnectionType(r2.isConnected(), r2.getNetworkType(), r2.getNetworkSubType());
    }

    private static NetworkChangeDetector.ConnectionType getConnectionType(boolean r0, int r1, int r2) {
        if (r0 == false) goto L4;
        if (r1 != 0) goto L7;
        switch(r2) {
            case 1: goto L38;
            case 2: goto L38;
            case 3: goto L36;
            case 4: goto L38;
            case 5: goto L36;
            case 6: goto L36;
            case 7: goto L38;
            case 8: goto L36;
            case 9: goto L36;
            case 10: goto L36;
            case 11: goto L38;
            case 12: goto L36;
            case 13: goto L34;
            case 14: goto L36;
            case 15: goto L36;
            case 16: goto L38;
            case 17: goto L36;
            case 18: goto L34;
            case 19: goto L30;
            case 20: goto L32;
            default: goto L30;
        };
    L30:
        return NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN_CELLULAR;
    L32:
        return NetworkChangeDetector.ConnectionType.CONNECTION_5G;
    L34:
        return NetworkChangeDetector.ConnectionType.CONNECTION_4G;
    L36:
        return NetworkChangeDetector.ConnectionType.CONNECTION_3G;
    L38:
        return NetworkChangeDetector.ConnectionType.CONNECTION_2G;
    L7:
        if (r1 == 1) goto L27;
        if (r1 == 6) goto L25;
        if (r1 == 7) goto L23;
        if (r1 == 9) goto L21;
        if (r1 == 17) goto L19;
        return NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN;
    L19:
        return NetworkChangeDetector.ConnectionType.CONNECTION_VPN;
    L21:
        return NetworkChangeDetector.ConnectionType.CONNECTION_ETHERNET;
    L23:
        return NetworkChangeDetector.ConnectionType.CONNECTION_BLUETOOTH;
    L25:
        return NetworkChangeDetector.ConnectionType.CONNECTION_4G;
    L27:
        return NetworkChangeDetector.ConnectionType.CONNECTION_WIFI;
    L4:
        return NetworkChangeDetector.ConnectionType.CONNECTION_NONE;
    }

    private static NetworkChangeDetector.ConnectionType getUnderlyingConnectionTypeForVpn(NetworkState r2) {
        if (r2.getNetworkType() == 17) goto L7;
        return NetworkChangeDetector.ConnectionType.CONNECTION_NONE;
    L7:
        return getConnectionType(r2.isConnected(), r2.getUnderlyingNetworkTypeForVpn(), r2.getUnderlyingNetworkSubtypeForVpn());
    }

    private String getWifiSSID(NetworkState r2) {
        if (getConnectionType(r2) == NetworkChangeDetector.ConnectionType.CONNECTION_WIFI) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return this.wifiManagerDelegate.getWifiSSID();
    }

    @SuppressLint({"NewApi"})
    private static long networkToNetId(Network r2) {
        return r2.getNetworkHandle();
    }

    private void registerReceiver() {
        if (this.isRegistered == false) goto L5;
        return;
    L5:
        this.isRegistered = true;
        int r0 = Build.VERSION.SDK_INT;
        Context r1 = this.context;
        if (r0 < 33) goto L9;
        r1.registerReceiver(this, this.intentFilter, 4);
        return;
    L9:
        r1.registerReceiver(this, this.intentFilter);
    }

    private void unregisterReceiver() {
        if (this.isRegistered == true) goto L5;
        return;
    L5:
        this.isRegistered = false;
        this.context.unregisterReceiver(this);
    }

    @Override // org.webrtc.NetworkChangeDetector
    public void destroy() {
        ConnectivityManager.NetworkCallback r0 = this.allNetworkCallback;
        if (r0 == null) goto L5;
        this.connectivityManagerDelegate.releaseCallback(r0);
    L5:
        ConnectivityManager.NetworkCallback r02 = this.mobileNetworkCallback;
        if (r02 == null) goto L8;
        this.connectivityManagerDelegate.releaseCallback(r02);
    L8:
        WifiDirectManagerDelegate r03 = this.wifiDirectManagerDelegate;
        if (r03 == null) goto L11;
        r03.release();
    L11:
        unregisterReceiver();
    }

    @Override // org.webrtc.NetworkChangeDetector
    public List<NetworkChangeDetector.NetworkInformation> getActiveNetworkList() {
        List<NetworkChangeDetector.NetworkInformation> r0 = this.connectivityManagerDelegate.getActiveNetworkList();
        if (r0 != null) goto L6;
        return null;
    L6:
        ArrayList r1 = new ArrayList(r0);
        WifiDirectManagerDelegate r2 = this.wifiDirectManagerDelegate;
        if (r2 == null) goto L9;
        r1.addAll(r2.getActiveNetworkList());
    L9:
        return r1;
    }

    @Override // org.webrtc.NetworkChangeDetector
    public NetworkChangeDetector.ConnectionType getCurrentConnectionType() {
        return getConnectionType(getCurrentNetworkState());
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public long getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    public boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r2, Intent r3) {
        NetworkState r22 = getCurrentNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(r3.getAction()) == false) goto L6;
        connectionTypeChanged(r22);
        return;
    }

    public void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate r1) {
        this.connectivityManagerDelegate = r1;
    }

    public void setWifiManagerDelegateForTests(WifiManagerDelegate r1) {
        this.wifiManagerDelegate = r1;
    }

    @Override // org.webrtc.NetworkChangeDetector
    public boolean supportNetworkCallback() {
        return this.connectivityManagerDelegate.supportNetworkCallback();
    }
}
