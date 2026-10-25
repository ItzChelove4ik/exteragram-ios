package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public class SessionManager {
    private static final Logger zza = null;
    private final zzax zzb;
    private final Context zzc;

    static {
        zza = new Logger("SessionManager");
    }

    public SessionManager(zzax r1, Context r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    public void addSessionManagerListener(SessionManagerListener<Session> r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        addSessionManagerListener(r2, Session.class);
    }

    public <T extends Session> void addSessionManagerListener(SessionManagerListener<T> r2, Class<T> r3) {
        if (r2 == null) goto L9;
        Preconditions.checkNotNull(r3);
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzb.zzf(new zzbh(r2, r3));     // Catch: RemoteException -> L6
        return;
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"addSessionManagerListener", zzax.class.getSimpleName()});
        return;
    L9:
        f$$ExternalSyntheticBUOutline2.m("SessionManagerListener can't be null");
    }

    public void endCurrentSession(boolean r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza.i("End session for %s", new Object[]{this.zzc.getPackageName()});     // Catch: RemoteException -> L5
        this.zzb.zzj(true, r4);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"endCurrentSession", zzax.class.getSimpleName()});
    }

    public CastSession getCurrentCastSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Session r1 = getCurrentSession();
        if (r1 != null) goto L5;
        return null;
    L5:
        if ((r1 instanceof CastSession) == true) goto L7;
        return null;
    L7:
        return (CastSession) r1;
    }

    public Session getCurrentSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return (Session) ObjectWrapper.unwrap(this.zzb.zze());
    L5:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getWrappedCurrentSession", zzax.class.getSimpleName()});
        return null;
    }

    public void removeSessionManagerListener(SessionManagerListener<Session> r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        removeSessionManagerListener(r2, Session.class);
    }

    public <T extends Session> void removeSessionManagerListener(SessionManagerListener<T> r2, Class<T> r3) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 != null) goto L10;
        return;
    L10:
        this.zzb.zzg(new zzbh(r2, r3));     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"removeSessionManagerListener", zzax.class.getSimpleName()});
    }

    public void startSession(Intent r6) {
        Logger r1 = zza;     // Catch: RemoteException -> L13
        Context r3 = this.zzc;     // Catch: RemoteException -> L13
        r1.i("Start session for %s", new Object[]{r3.getPackageName()});     // Catch: RemoteException -> L13
        Bundle r12 = r6.getExtras();     // Catch: RemoteException -> L13
        if (r12 != null) goto L6;
        return;
    L6:
        if (r12.getString("CAST_INTENT_TO_CAST_ROUTE_ID_KEY") == null) goto L19;
        String r2 = r12.getString("CAST_INTENT_TO_CAST_DEVICE_NAME_KEY");     // Catch: RemoteException -> L13
        if (r12.getBoolean("CAST_INTENT_TO_CAST_NO_TOAST_KEY") == true) goto L11;
        Toast.makeText(r3, r3.getString(R.string.cast_connecting_to_device, new Object[]{r2}), 0).show();     // Catch: RemoteException -> L13
    L11:
        this.zzb.zzm(new Bundle(r12));     // Catch: RemoteException -> L13
        r6.removeExtra("CAST_INTENT_TO_CAST_ROUTE_ID_KEY");     // Catch: RemoteException -> L13
        return;
    L19:
        return;
    L13:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"startSession", zzax.class.getSimpleName()});
    }

    public final int zza() {
        return this.zzb.zzl();
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"addCastStateListener", zzax.class.getSimpleName()});
        return 1;
    }

    public final void zzb(CastStateListener r3) {
        Preconditions.checkNotNull(r3);
        this.zzb.zzh(new zzaa(r3));     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"addCastStateListener", zzax.class.getSimpleName()});
    }

    public final void zzc(CastStateListener r3) {
        this.zzb.zzi(new zzaa(r3));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"removeCastStateListener", zzax.class.getSimpleName()});
    }

    public final IObjectWrapper zzd() {
        return this.zzb.zzk();
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getWrappedThis", zzax.class.getSimpleName()});
        return null;
    }
}
