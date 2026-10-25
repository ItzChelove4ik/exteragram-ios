package androidx.media3.common.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final class NetworkTypeObserver {
    private static NetworkTypeObserver staticInstance;
    private final Executor backgroundExecutor;
    private boolean isInitialized;
    private final CopyOnWriteArrayList<ListenerHolder> listeners;
    private final Object lock;
    private int networkType;

    /* renamed from: androidx.media3.common.util.NetworkTypeObserver$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api31 {

        public static final class DisplayInfoCallback extends TelephonyCallback implements TelephonyCallback.DisplayInfoListener {
            private final NetworkTypeObserver instance;

            public DisplayInfoCallback(NetworkTypeObserver r1) {
                this.instance = r1;
            }

            public void onDisplayInfoChanged(TelephonyDisplayInfo r3) {
                int r32 = r3.getOverrideNetworkType();
                int r1 = 5;
                if (r32 != 3) goto L5;
            L9:
                boolean r33 = true;
            L10:
                NetworkTypeObserver r2 = this.instance;
                if (r33 == false) goto L13;
                r1 = 10;
            L13:
                NetworkTypeObserver.access$300(r2, r1);
                return;
            L5:
                if (r32 == 4) goto L9;
                if (r32 == 5) goto L9;
                r33 = false;
                goto L10
            }
        }

        public static void disambiguate4gAnd5gNsa(Context r2, NetworkTypeObserver r3) {
            TelephonyManager r22 = (TelephonyManager) Assertions.checkNotNull((TelephonyManager) r2.getSystemService("phone"));     // Catch: RuntimeException -> L4
            DisplayInfoCallback r0 = new DisplayInfoCallback(r3);     // Catch: RuntimeException -> L4
            r22.registerTelephonyCallback(NetworkTypeObserver.access$100(r3), r0);     // Catch: RuntimeException -> L4
            r22.unregisterTelephonyCallback(r0);     // Catch: RuntimeException -> L4
            return;
        L4:
            NetworkTypeObserver.access$300(r3, 5);
        }
    }

    public interface Listener {
        void onNetworkTypeChanged(int r1);
    }

    public final class ListenerHolder {
        private final Executor executor;
        private final WeakReference<Listener> listener;
        final /* synthetic */ NetworkTypeObserver this$0;

        public static /* synthetic */ void $r8$lambda$iBfAT9qc_pBlJI_KzduSDBdK32I(ListenerHolder r1) {
            Listener r0 = r1.listener.get();
            if (r0 == null) goto L6;
            r0.onNetworkTypeChanged(r1.this$0.getNetworkType());
            return;
        }

        public ListenerHolder(NetworkTypeObserver r1, Listener r2, Executor r3) {
            this.this$0 = r1;
            this.listener = new WeakReference(r2);
            this.executor = r3;
        }

        public void callOnNetworkTypeChanged() {
            this.executor.execute(new NetworkTypeObserver$ListenerHolder$$ExternalSyntheticLambda0(this));
        }

        public boolean canBeRemoved() {
            if (this.listener.get() != null) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public final class Receiver extends BroadcastReceiver {
        final /* synthetic */ NetworkTypeObserver this$0;

        public static /* synthetic */ void $r8$lambda$oGNpXheZNrYiJIsGL7z4WdTfjyc(Receiver r0, Context r1) {
            NetworkTypeObserver.access$200(r0.this$0, r1);
        }

        private Receiver(NetworkTypeObserver r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Receiver(NetworkTypeObserver r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            NetworkTypeObserver.access$100(this.this$0).execute(new NetworkTypeObserver$Receiver$$ExternalSyntheticLambda0(this, r2));
        }
    }

    public static /* synthetic */ void $r8$lambda$LtqLe6kF1wQt_M0EJO9YhjNpymg(NetworkTypeObserver r0, Context r1) {
        r0.init(r1);
    }

    private NetworkTypeObserver(Context r3) {
        Executor r0 = BackgroundExecutor.get();
        this.backgroundExecutor = r0;
        this.listeners = new CopyOnWriteArrayList();
        this.lock = new Object();
        this.networkType = 0;
        r0.execute(new NetworkTypeObserver$$ExternalSyntheticLambda0(this, r3));
    }

    public static /* synthetic */ Executor access$100(NetworkTypeObserver r0) {
        return r0.backgroundExecutor;
    }

    public static /* synthetic */ void access$200(NetworkTypeObserver r0, Context r1) {
        r0.handleConnectivityActionBroadcast(r1);
    }

    public static /* synthetic */ void access$300(NetworkTypeObserver r0, int r1) {
        r0.updateNetworkType(r1);
    }

    public static synchronized NetworkTypeObserver getInstance(Context r2) {
        monitor-enter(NetworkTypeObserver.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (staticInstance != null) goto L9;
        staticInstance = new NetworkTypeObserver(r2);     // Catch: Throwable -> L7
    L9:
        NetworkTypeObserver r22 = staticInstance;     // Catch: Throwable -> L7
        monitor-exit(NetworkTypeObserver.class);
        return r22;
    }

    private static int getMobileNetworkType(NetworkInfo r1) {
        switch(r1.getSubtype()) {
            case 1: goto L18;
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L16;
            case 5: goto L16;
            case 6: goto L16;
            case 7: goto L16;
            case 8: goto L16;
            case 9: goto L16;
            case 10: goto L16;
            case 11: goto L16;
            case 12: goto L16;
            case 13: goto L14;
            case 14: goto L16;
            case 15: goto L16;
            case 16: goto L4;
            case 17: goto L16;
            case 18: goto L12;
            case 19: goto L4;
            case 20: goto L7;
            default: goto L4;
        };
    L4:
        return 6;
    L12:
        return 2;
    L14:
        return 5;
    L16:
        return 4;
    L18:
        return 3;
    L7:
        if (Build.VERSION.SDK_INT < 29) goto L10;
        return 9;
    L10:
        return 0;
    }

    private static int getNetworkTypeFromConnectivityManager(Context r2) {
        ConnectivityManager r22 = (ConnectivityManager) r2.getSystemService("connectivity");
        int r0 = 0;
        if (r22 != null) goto L33;
        return 0;
    L33:
        NetworkInfo r23 = r22.getActiveNetworkInfo();     // Catch: SecurityException -> L32
        r0 = 1;
        if (r23 == null) goto L31;
        if (r23.isConnected() == false) goto L31;
        int r1 = r23.getType();
        if (r1 == 0) goto L30;
        if (r1 != 1) goto L15;
        return 2;
    L15:
        if (r1 == 4) goto L30;
        if (r1 == 5) goto L30;
        if (r1 != 6) goto L21;
        return 5;
    L21:
        if (r1 == 9) goto L24;
        return 8;
    L24:
        return 7;
    L30:
        return getMobileNetworkType(r23);
    L31:
        return r0;
    }

    private void handleConnectivityActionBroadcast(Context r4) {
        int r0 = getNetworkTypeFromConnectivityManager(r4);
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L8:
        updateNetworkType(r0);
        return;
    L5:
        if (r0 != 5) goto L8;
        Api31.disambiguate4gAnd5gNsa(r4, this);
    }

    @SuppressLint({"UnprotectedReceiver"})
    private void init(Context r4) {
        IntentFilter r0 = new IntentFilter();
        r0.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        r4.registerReceiver(new Receiver(this, null), r0);
    }

    private void removeClearedReferences() {
        Iterator<ListenerHolder> r0 = this.listeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        ListenerHolder r1 = r0.next();
        if (r1.canBeRemoved() == false) goto L4;
        this.listeners.remove(r1);
        goto L4
    }

    private void updateNetworkType(int r3) {
        removeClearedReferences();
        Object r0 = this.lock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.isInitialized == true) goto L7;
    L12:
        this.isInitialized = true;     // Catch: Throwable -> L10
        this.networkType = r3;     // Catch: Throwable -> L10
        Iterator<ListenerHolder> r2 = this.listeners.iterator();     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
    L15:
        if (r2.hasNext() == false) goto L17;
        r2.next().callOnNetworkTypeChanged();
        goto L15
    L17:
        return;
    L7:
        if (this.networkType != r3) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L10
    }

    public int getNetworkType() {
        Object r0 = this.lock;
        monitor-enter(r0);
        int r1 = this.networkType;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void register(Listener r2, Executor r3) {
        removeClearedReferences();
        ListenerHolder r0 = new ListenerHolder(this, r2, r3);
        Object r22 = this.lock;
        monitor-enter(r22);
        this.listeners.add(r0);     // Catch: Throwable -> L9
        boolean r1 = this.isInitialized;     // Catch: Throwable -> L9
        monitor-exit(r22);     // Catch: Throwable -> L9
        if (r1 == false) goto L14;
        r0.callOnNetworkTypeChanged();
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
