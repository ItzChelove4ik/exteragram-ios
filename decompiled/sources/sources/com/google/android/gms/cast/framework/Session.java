package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class Session {
    private static final Logger zza = null;
    private final zzav zzb;
    private final zzbg zzc;

    static {
        zza = new Logger("Session");
    }

    public Session(Context r3, String r4, String r5) {
        zzbg r0 = new zzbg(this, null);
        this.zzc = r0;
        this.zzb = com.google.android.gms.internal.cast.zzay.zzb(r3, r4, r5, r0);
    }

    public abstract void end(boolean r1);

    public final String getCategory() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return null;
    L9:
        return r4.zzg();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getCategory", zzav.class.getSimpleName()});
        goto L8
    }

    public final String getSessionId() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return null;
    L9:
        return r4.zzh();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getSessionId", zzav.class.getSimpleName()});
        goto L8
    }

    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return 0;
    }

    public boolean isConnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return false;
    L9:
        return r4.zzi();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"isConnected", zzav.class.getSimpleName()});
        goto L8
    }

    public boolean isConnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return false;
    L9:
        return r4.zzj();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"isConnecting", zzav.class.getSimpleName()});
        goto L8
    }

    public boolean isDisconnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return true;
    L9:
        return r4.zzl();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"isDisconnected", zzav.class.getSimpleName()});
        goto L8
    }

    public boolean isDisconnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return false;
    L9:
        return r4.zzk();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"isDisconnecting", zzav.class.getSimpleName()});
        goto L8
    }

    public boolean isResuming() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return false;
    L9:
        return r4.zzm();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"isResuming", zzav.class.getSimpleName()});
        goto L8
    }

    public boolean isSuspended() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return false;
    L9:
        return r4.zzn();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"isSuspended", zzav.class.getSimpleName()});
        goto L8
    }

    public final void notifyFailedToResumeSession(int r3) {
        zzav r2 = this.zzb;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.zzt(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"notifyFailedToResumeSession", zzav.class.getSimpleName()});
    }

    public final void notifyFailedToStartSession(int r3) {
        zzav r2 = this.zzb;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.zzq(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"notifyFailedToStartSession", zzav.class.getSimpleName()});
    }

    public final void notifySessionEnded(int r3) {
        zzav r2 = this.zzb;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.zzr(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"notifySessionEnded", zzav.class.getSimpleName()});
    }

    public final void notifySessionResumed(boolean r3) {
        zzav r2 = this.zzb;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.zzs(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"notifySessionResumed", zzav.class.getSimpleName()});
    }

    public final void notifySessionStarted(String r3) {
        zzav r2 = this.zzb;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.zzp(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"notifySessionStarted", zzav.class.getSimpleName()});
    }

    public final void notifySessionSuspended(int r3) {
        zzav r2 = this.zzb;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.zzu(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"notifySessionSuspended", zzav.class.getSimpleName()});
    }

    public void onResuming(Bundle r1) {
    }

    public void onStarting(Bundle r1) {
    }

    public abstract void resume(Bundle r1);

    public abstract void start(Bundle r1);

    public void zza(Bundle r1) {
    }

    public final int zzm() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzav r4 = this.zzb;
        if (r4 != null) goto L11;
    L10:
        return 0;
    L11:
    L8:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getSessionStartType", zzav.class.getSimpleName()});
        goto L10
    L5:
        if (r4.zze() < 211100000) goto L10;
        return r4.zzo();
    }

    public final IObjectWrapper zzn() {
        zzav r4 = this.zzb;
        if (r4 != null) goto L9;
    L8:
        return null;
    L9:
        return r4.zzf();
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getWrappedObject", zzav.class.getSimpleName()});
        goto L8
    }
}
