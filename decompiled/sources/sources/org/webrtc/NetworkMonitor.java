package org.webrtc;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.webrtc.NetworkChangeDetector;

/* loaded from: classes6.dex */
public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private volatile NetworkChangeDetector.ConnectionType currentConnectionType;
    private final ArrayList<Long> nativeNetworkObservers;
    private NetworkChangeDetector networkChangeDetector;
    private NetworkChangeDetectorFactory networkChangeDetectorFactory;
    private final Object networkChangeDetectorLock;
    private final ArrayList<NetworkObserver> networkObservers;
    private int numObservers;

    /* renamed from: org.webrtc.NetworkMonitor$1, reason: invalid class name */
    public class AnonymousClass1 implements NetworkChangeDetectorFactory {
        final /* synthetic */ NetworkMonitor this$0;

        public AnonymousClass1(NetworkMonitor r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.NetworkChangeDetectorFactory
        public NetworkChangeDetector create(NetworkChangeDetector.Observer r1, Context r2) {
            return new NetworkMonitorAutoDetect(r1, r2);
        }
    }

    /* renamed from: org.webrtc.NetworkMonitor$2, reason: invalid class name */
    public class AnonymousClass2 implements NetworkChangeDetector.Observer {
        final /* synthetic */ NetworkMonitor this$0;

        public AnonymousClass2(NetworkMonitor r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.NetworkChangeDetector.Observer
        public void onConnectionTypeChanged(NetworkChangeDetector.ConnectionType r1) {
            NetworkMonitor.m22362$$Nest$mupdateCurrentConnectionType(this.this$0, r1);
        }

        @Override // org.webrtc.NetworkChangeDetector.Observer
        public void onNetworkConnect(NetworkChangeDetector.NetworkInformation r1) {
            NetworkMonitor.m22359$$Nest$mnotifyObserversOfNetworkConnect(this.this$0, r1);
        }

        @Override // org.webrtc.NetworkChangeDetector.Observer
        public void onNetworkDisconnect(long r1) {
            NetworkMonitor.m22360$$Nest$mnotifyObserversOfNetworkDisconnect(this.this$0, r1);
        }

        @Override // org.webrtc.NetworkChangeDetector.Observer
        public void onNetworkPreference(List<NetworkChangeDetector.ConnectionType> r1, int r2) {
            NetworkMonitor.m22361$$Nest$mnotifyObserversOfNetworkPreference(this.this$0, r1, r2);
        }
    }

    public static class InstanceHolder {
        static final NetworkMonitor instance = null;

        static {
            instance = new NetworkMonitor(null);
        }

        private InstanceHolder() {
        }
    }

    public interface NetworkObserver {
        void onConnectionTypeChanged(NetworkChangeDetector.ConnectionType r1);
    }

    /* renamed from: -$$Nest$mnotifyObserversOfNetworkConnect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22359$$Nest$mnotifyObserversOfNetworkConnect(NetworkMonitor r0, NetworkChangeDetector.NetworkInformation r1) {
        r0.notifyObserversOfNetworkConnect(r1);
    }

    /* renamed from: -$$Nest$mnotifyObserversOfNetworkDisconnect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22360$$Nest$mnotifyObserversOfNetworkDisconnect(NetworkMonitor r0, long r1) {
        r0.notifyObserversOfNetworkDisconnect(r1);
    }

    /* renamed from: -$$Nest$mnotifyObserversOfNetworkPreference, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22361$$Nest$mnotifyObserversOfNetworkPreference(NetworkMonitor r0, List r1, int r2) {
        r0.notifyObserversOfNetworkPreference(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateCurrentConnectionType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22362$$Nest$mupdateCurrentConnectionType(NetworkMonitor r0, NetworkChangeDetector.ConnectionType r1) {
        r0.updateCurrentConnectionType(r1);
    }

    private NetworkMonitor() {
        this.networkChangeDetectorFactory = new AnonymousClass1(this);
        this.networkChangeDetectorLock = new Object();
        this.nativeNetworkObservers = new ArrayList();
        this.networkObservers = new ArrayList();
        this.numObservers = 0;
        this.currentConnectionType = NetworkChangeDetector.ConnectionType.CONNECTION_UNKNOWN;
    }

    public /* synthetic */ NetworkMonitor(NetworkMonitorIA r1) {
        this();
    }

    @Deprecated
    public static void addNetworkObserver(NetworkObserver r1) {
        getInstance().addObserver(r1);
    }

    @CalledByNative
    private static int androidSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    private static void assertIsTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected to be true");
    }

    public static NetworkMonitorAutoDetect createAndSetAutoDetectForTest(Context r1, String r2) {
        NetworkMonitor r0 = getInstance();
        NetworkChangeDetector r12 = r0.createNetworkChangeDetector(r1, r2);
        r0.networkChangeDetector = r12;
        return (NetworkMonitorAutoDetect) r12;
    }

    private NetworkChangeDetector createNetworkChangeDetector(Context r2, String r3) {
        return this.networkChangeDetectorFactory.create(new AnonymousClass2(this), r2);
    }

    private NetworkChangeDetector.ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }

    @CalledByNative
    public static NetworkMonitor getInstance() {
        return InstanceHolder.instance;
    }

    private List<Long> getNativeNetworkObserversSync() {
        ArrayList<Long> r0 = this.nativeNetworkObservers;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(this.nativeNetworkObservers);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Deprecated
    public static void init(Context r0) {
    }

    public static boolean isOnline() {
        if (getInstance().getCurrentConnectionType() == NetworkChangeDetector.ConnectionType.CONNECTION_NONE) goto L6;
        return true;
    L6:
        return false;
    }

    private native void nativeNotifyConnectionTypeChanged(long r1);

    private native void nativeNotifyOfActiveNetworkList(long r1, NetworkChangeDetector.NetworkInformation[] r3);

    private native void nativeNotifyOfNetworkConnect(long r1, NetworkChangeDetector.NetworkInformation r3);

    private native void nativeNotifyOfNetworkDisconnect(long r1, long r3);

    private native void nativeNotifyOfNetworkPreference(long r1, NetworkChangeDetector.ConnectionType r3, int r4);

    @CalledByNative
    private boolean networkBindingSupported() {
        Object r0 = this.networkChangeDetectorLock;
        monitor-enter(r0);
        NetworkChangeDetector r1 = this.networkChangeDetector;     // Catch: Throwable -> L9
        if (r1 != null) goto L7;
    L11:
        boolean r12 = false;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L7:
        if (r1.supportNetworkCallback() == false) goto L11;
        r12 = true;
    L9:
        th = move-exception;
        throw th;
    }

    private void notifyObserversOfConnectionTypeChange(NetworkChangeDetector.ConnectionType r4) {
        Iterator<Long> r0 = getNativeNetworkObserversSync().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        nativeNotifyConnectionTypeChanged(r0.next().longValue());
        goto L4
    L6:
        ArrayList<NetworkObserver> r02 = this.networkObservers;
        monitor-enter(r02);
        ArrayList r1 = new ArrayList(this.networkObservers);     // Catch: Throwable -> L14
        monitor-exit(r02);     // Catch: Throwable -> L14
        int r3 = r1.size();
        int r03 = 0;
    L11:
        if (r03 >= r3) goto L13;
        Object r2 = r1.get(r03);
        r03 = r03 + 1;
        ((NetworkObserver) r2).onConnectionTypeChanged(r4);
        goto L11
    L13:
        return;
    L14:
        th = move-exception;
        throw th;
    }

    private void notifyObserversOfNetworkConnect(NetworkChangeDetector.NetworkInformation r4) {
        Iterator<Long> r0 = getNativeNetworkObserversSync().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        nativeNotifyOfNetworkConnect(r0.next().longValue(), r4);
        goto L4
    }

    private void notifyObserversOfNetworkDisconnect(long r4) {
        Iterator<Long> r0 = getNativeNetworkObserversSync().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        nativeNotifyOfNetworkDisconnect(r0.next().longValue(), r4);
        goto L4
    }

    private void notifyObserversOfNetworkPreference(List<NetworkChangeDetector.ConnectionType> r6, int r7) {
        List<Long> r0 = getNativeNetworkObserversSync();
        Iterator<NetworkChangeDetector.ConnectionType> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L9;
        NetworkChangeDetector.ConnectionType r1 = r62.next();
        Iterator<Long> r2 = r0.iterator();
    L7:
        if (r2.hasNext() == false) goto L4;
        nativeNotifyOfNetworkPreference(r2.next().longValue(), r1, r7);
        goto L7
    }

    @Deprecated
    public static void removeNetworkObserver(NetworkObserver r1) {
        getInstance().removeObserver(r1);
    }

    @CalledByNative
    private void startMonitoring(Context r4, long r5, String r7) {
        Logging.d(TAG, "Start monitoring with native observer " + r5 + " fieldTrialsString: " + r7);
        if (r4 != null) goto L6;
        r4 = ContextUtils.getApplicationContext();
    L6:
        startMonitoring(r4, r7);
        ArrayList<Long> r42 = this.nativeNetworkObservers;
        monitor-enter(r42);
        this.nativeNetworkObservers.add(Long.valueOf(r5));     // Catch: Throwable -> L12
        monitor-exit(r42);     // Catch: Throwable -> L12
        updateObserverActiveNetworkList(r5);
        notifyObserversOfConnectionTypeChange(this.currentConnectionType);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    @CalledByNative
    private void stopMonitoring(long r4) {
        Logging.d(TAG, "Stop monitoring with native observer " + r4);
        stopMonitoring();
        ArrayList<Long> r0 = this.nativeNetworkObservers;
        monitor-enter(r0);
        this.nativeNetworkObservers.remove(Long.valueOf(r4));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void updateCurrentConnectionType(NetworkChangeDetector.ConnectionType r1) {
        this.currentConnectionType = r1;
        notifyObserversOfConnectionTypeChange(r1);
    }

    private void updateObserverActiveNetworkList(long r3) {
        Object r0 = this.networkChangeDetectorLock;
        monitor-enter(r0);
        NetworkChangeDetector r1 = this.networkChangeDetector;     // Catch: Throwable -> L13
        if (r1 != null) goto L7;
        List<NetworkChangeDetector.NetworkInformation> r12 = null;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L13
        if (r12 != null) goto L11;
        return;
    L11:
        nativeNotifyOfActiveNetworkList(r3, (NetworkChangeDetector.NetworkInformation[]) r12.toArray(new NetworkChangeDetector.NetworkInformation[r12.size()]));
        return;
    L7:
        r12 = r1.getActiveNetworkList();     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    }

    public void addObserver(NetworkObserver r2) {
        ArrayList<NetworkObserver> r0 = this.networkObservers;
        monitor-enter(r0);
        this.networkObservers.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public NetworkChangeDetector getNetworkChangeDetector() {
        Object r0 = this.networkChangeDetectorLock;
        monitor-enter(r0);
        NetworkChangeDetector r1 = this.networkChangeDetector;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public int getNumObservers() {
        Object r0 = this.networkChangeDetectorLock;
        monitor-enter(r0);
        int r1 = this.numObservers;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void removeObserver(NetworkObserver r2) {
        ArrayList<NetworkObserver> r0 = this.networkObservers;
        monitor-enter(r0);
        this.networkObservers.remove(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setNetworkChangeDetectorFactory(NetworkChangeDetectorFactory r2) {
        if (this.numObservers != 0) goto L5;
        boolean r0 = true;
    L6:
        assertIsTrue(r0);
        this.networkChangeDetectorFactory = r2;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Deprecated
    public void startMonitoring() {
        startMonitoring(ContextUtils.getApplicationContext(), _UrlKt.FRAGMENT_ENCODE_SET);
    }

    @Deprecated
    public void startMonitoring(Context r2) {
        startMonitoring(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public void startMonitoring(Context r3, String r4) {
        Object r0 = this.networkChangeDetectorLock;
        monitor-enter(r0);
        this.numObservers++;
        if (this.networkChangeDetector != null) goto L9;
        this.networkChangeDetector = createNetworkChangeDetector(r3, r4);     // Catch: Throwable -> L7
    L9:
        this.currentConnectionType = this.networkChangeDetector.getCurrentConnectionType();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void stopMonitoring() {
        Object r0 = this.networkChangeDetectorLock;
        monitor-enter(r0);
        int r1 = this.numObservers - 1;
        this.numObservers = r1;     // Catch: Throwable -> L7
        if (r1 != 0) goto L9;
        this.networkChangeDetector.destroy();     // Catch: Throwable -> L7
        this.networkChangeDetector = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
