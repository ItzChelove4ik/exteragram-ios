package com.google.android.gms.wearable;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;
import com.google.android.gms.wearable.internal.zzbc;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class WearableListenerService extends Service implements ChannelApi.ChannelListener {
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private ComponentName zza;
    private zzn zzb;
    private IBinder zzc;
    private Intent zzd;
    private Looper zze;
    private final Object zzf;
    private boolean zzg;
    private zzbc zzh;

    public WearableListenerService() {
        this.zzf = new Object();
        this.zzh = new zzbc(new zzk(this, null));
    }

    public static /* bridge */ /* synthetic */ ComponentName zza(WearableListenerService r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ Intent zzb(WearableListenerService r0) {
        return r0.zzd;
    }

    public static /* bridge */ /* synthetic */ zzn zzc(WearableListenerService r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ zzbc zzd(WearableListenerService r0) {
        return r0.zzh;
    }

    public static /* bridge */ /* synthetic */ Object zze(WearableListenerService r0) {
        return r0.zzf;
    }

    public static /* bridge */ /* synthetic */ boolean zzf(WearableListenerService r0) {
        return r0.zzg;
    }

    public Looper getLooper() {
        if (this.zze != null) goto L6;
        HandlerThread r0 = new HandlerThread("WearableListenerService");
        r0.start();
        this.zze = r0.getLooper();
    L6:
        return this.zze;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        String r1 = r4.getAction();
        if (r1 != null) goto L9;
        return null;
    L9:
        switch(r1.hashCode()) {
            case -1487371046: goto L27;
            case -1140095138: goto L24;
            case -786751258: goto L21;
            case 915816236: goto L18;
            case 1003809169: goto L15;
            case 1460975593: goto L12;
            default: goto L31;
        };
    L12:
        if (r1.equals(BIND_LISTENER_INTENT_ACTION) == false) goto L31;
    L29:
        return this.zzc;
    L15:
        if (r1.equals("com.google.android.gms.wearable.CHANNEL_EVENT") == false) goto L31;
    L18:
        if (r1.equals("com.google.android.gms.wearable.DATA_CHANGED") == false) goto L31;
    L21:
        if (r1.equals("com.google.android.gms.wearable.MESSAGE_RECEIVED") == false) goto L31;
    L24:
        if (r1.equals("com.google.android.gms.wearable.REQUEST_RECEIVED") == false) goto L31;
    L27:
        if (r1.equals("com.google.android.gms.wearable.CAPABILITY_CHANGED") == true) goto L29;
    L31:
        if (Log.isLoggable("WearableLS", 3) == false) goto L33;
        Log.d("WearableLS", "onBind: Provided bind intent (" + r4.toString() + ") is not allowed");
    L33:
        return null;
    }

    public void onCapabilityChanged(CapabilityInfo r1) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onChannelClosed(Channel r1, int r2, int r3) {
    }

    public void onChannelClosed(ChannelClient.Channel r1, int r2, int r3) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onChannelOpened(Channel r1) {
    }

    public void onChannelOpened(ChannelClient.Channel r1) {
    }

    public void onConnectedNodes(List<Node> r1) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.zza = new ComponentName(this, getClass().getName());
        if (Log.isLoggable("WearableLS", 3) == false) goto L5;
        Log.d("WearableLS", "onCreate: ".concat(String.valueOf(this.zza)));
    L5:
        this.zzb = new zzn(this, getLooper());
        Intent r0 = new Intent(BIND_LISTENER_INTENT_ACTION);
        this.zzd = r0;
        r0.setComponent(this.zza);
        this.zzc = new zzaa(this, null);
    }

    public void onDataChanged(DataEventBuffer r1) {
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (Log.isLoggable("WearableLS", 3) == false) goto L5;
        Log.d("WearableLS", "onDestroy: ".concat(String.valueOf(this.zza)));
    L5:
        Object r1 = this.zzf;
        monitor-enter(r1);
        this.zzg = true;     // Catch: Throwable -> L14
        zzn r2 = this.zzb;     // Catch: Throwable -> L14
        if (r2 == null) goto L17;
        r2.zzb();     // Catch: Throwable -> L14
        monitor-exit(r1);     // Catch: Throwable -> L14
        super.onDestroy();
        return;
    L17:
        throw new IllegalStateException("onDestroy: mServiceHandler not set, did you override onCreate() but forget to call super.onCreate()? component=".concat(String.valueOf(this.zza)));     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    public void onEntityUpdate(zza r1) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onInputClosed(Channel r1, int r2, int r3) {
    }

    public void onInputClosed(ChannelClient.Channel r1, int r2, int r3) {
    }

    public abstract void onMessageReceived(MessageEvent r1);

    public void onNotificationReceived(zzb r1) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onOutputClosed(Channel r1, int r2, int r3) {
    }

    public void onOutputClosed(ChannelClient.Channel r1, int r2, int r3) {
    }

    public void onPeerConnected(Node r1) {
    }

    public void onPeerDisconnected(Node r1) {
    }

    public Task<byte[]> onRequest(String r1, String r2, byte[] r3) {
        return null;
    }
}
