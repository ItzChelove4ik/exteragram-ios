package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class BlockingServiceConnection implements ServiceConnection {
    boolean zza;
    private final BlockingQueue zzb;

    public BlockingServiceConnection() {
        this.zza = false;
        this.zzb = new LinkedBlockingQueue();
    }

    public IBinder getServiceWithTimeout(long r2, TimeUnit r4) {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.zza == true) goto L9;
        this.zza = true;
        IBinder r1 = (IBinder) this.zzb.poll(r2, r4);
        if (r1 == null) goto L8;
        return r1;
    L8:
        throw new TimeoutException("Timed out waiting for the service connection");
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot call get on this connection more than once");
        return null;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r1, IBinder r2) {
        this.zzb.add(r2);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r1) {
    }
}
