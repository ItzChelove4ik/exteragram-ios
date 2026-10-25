package com.google.android.gms.internal.cast;

import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.ModuleUnavailableException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzay {
    private static final Logger zza = null;

    static {
        zza = new Logger("CastDynamiteModule");
    }

    public static com.google.android.gms.cast.framework.zzai zza(Context r1, CastOptions r2, zzbe r3, Map r4) {
        return zzf(r1).zzf(ObjectWrapper.wrap(r1.getApplicationContext()), r2, r3, r4);
    }

    public static com.google.android.gms.cast.framework.zzav zzb(Context r0, String r1, String r2, com.google.android.gms.cast.framework.zzbd r3) {
        return zzf(r0).zzg(r1, r2, r3);
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"newSessionImpl", zzbc.class.getSimpleName()});
        return null;
    }

    public static com.google.android.gms.cast.framework.zzal zzc(Context r1, CastOptions r2, IObjectWrapper r3, com.google.android.gms.cast.framework.zzaf r4) {
        if (r3 != null) goto L12;
        return null;
    L12:
        return zzf(r1).zzh(r2, r3, r4);
    L9:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"newCastSessionImpl", zzbc.class.getSimpleName()});
        return null;
    }

    public static com.google.android.gms.cast.framework.zzas zzd(Service r2, IObjectWrapper r3, IObjectWrapper r4) {
        if (r3 == null) goto L12;
        if (r4 == null) goto L12;
        return zzf(r2.getApplicationContext()).zzi(ObjectWrapper.wrap(r2), r3, r4);
    L8:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"newReconnectionServiceImpl", zzbc.class.getSimpleName()});
    L12:
        return null;
    }

    public static com.google.android.gms.cast.framework.media.internal.zzg zze(Context r13, AsyncTask r14, com.google.android.gms.cast.framework.media.internal.zzi r15, int r16, int r17, boolean r18, long r19, int r21, int r22, int r23) {
        zzbc r1 = zzf(r13.getApplicationContext());     // Catch: Throwable -> L8
        IObjectWrapper r2 = ObjectWrapper.wrap(r13.getApplicationContext());     // Catch: Throwable -> L8
        if (r1.zze() < 233700000) goto L7;
        return r1.zzk(r2, ObjectWrapper.wrap(r14), r15, r16, r17, false, 2097152, 5, 333, 10000);
    L7:
        return r1.zzj(ObjectWrapper.wrap(r14), r15, r16, r17, false, 2097152, 5, 333, 10000);
    L8:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"newFetchBitmapTaskImpl", zzbc.class.getSimpleName()});
        return null;
    }

    private static zzbc zzf(Context r2) {
        IBinder r22 = DynamiteModule.load(r2, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.cast.framework.dynamite").instantiate("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl");     // Catch: DynamiteModule.LoadingException -> L12
        if (r22 != null) goto L6;
        return null;
    L6:
        IInterface r0 = r22.queryLocalInterface("com.google.android.gms.cast.framework.internal.ICastDynamiteModule");     // Catch: DynamiteModule.LoadingException -> L12
        if ((r0 instanceof zzbc) == false) goto L11;
        return (zzbc) r0;
    L11:
        return new zzbb(r22);
    L12:
        e = move-exception;
        throw new ModuleUnavailableException(e);
    }
}
