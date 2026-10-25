package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.internal.zzf;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes5.dex */
public abstract class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc;

    public enum Renderer extends Enum<Renderer> {
        public static final Renderer LATEST = null;

        @Deprecated
        public static final Renderer LEGACY = null;
        private static final /* synthetic */ Renderer[] zza = null;

        static {
            Renderer r0 = new Renderer("LEGACY", 0);
            LEGACY = r0;
            Renderer r1 = new Renderer("LATEST", 1);
            LATEST = r1;
            zza = new Renderer[]{r0, r1};
        }

        Renderer(String r1, int r2) {
        }

        public static Renderer valueOf(String r1) {
            return (Renderer) Enum.valueOf(Renderer.class, r1);
        }

        public static Renderer[] values() {
            return (Renderer[]) zza.clone();
        }
    }

    static {
        zzc = Renderer.LEGACY;
    }

    public static synchronized int initialize(Context r2) {
        monitor-enter(MapsInitializer.class);
        int r22 = initialize(r2, null, null);     // Catch: Throwable -> L8
        monitor-exit(MapsInitializer.class);
        return r22;
    L8:
        th = move-exception;
        throw th;
    }

    public static synchronized int initialize(Context r5, Renderer r6, OnMapsSdkInitializedCallback r7) {
        monitor-enter(MapsInitializer.class);
        Preconditions.checkNotNull(r5, "Context is null");     // Catch: Throwable -> L9
        Log.d(zza, "preferredRenderer: ".concat(String.valueOf(r6)));     // Catch: Throwable -> L9
        if (zzb == false) goto L50;
        if (r7 == null) goto L34;
        r7.onMapsSdkInitialized(zzc);     // Catch: Throwable -> L9
    L34:
        monitor-exit(MapsInitializer.class);
        return 0;
    L50:
        zzf r1 = zzcc.zza(r5, r6);     // Catch: Throwable -> L9 GooglePlayServicesNotAvailableException -> L39
        CameraUpdateFactory.zza(r1.zze());     // Catch: Throwable -> L9 RemoteException -> L36
        BitmapDescriptorFactory.zza(r1.zzj());     // Catch: Throwable -> L9 RemoteException -> L36
        int r3 = 1;
        zzb = true;     // Catch: Throwable -> L9
        if (r6 == null) goto L22;
        int r62 = r6.ordinal();     // Catch: Throwable -> L9
        if (r62 == 0) goto L46;
        if (r62 != 1) goto L21;
        r3 = 2;
        goto L46
    L21:
        throw new RuntimeException(null, null);     // Catch: Throwable -> L9
    L46:
    L26:
        e = move-exception;
        Log.e(zza, "Failed to retrieve renderer type or log initialization.", e);     // Catch: Throwable -> L9
    L31:
        Log.d(zza, "loadedRenderer: ".concat(String.valueOf(zzc)));     // Catch: Throwable -> L9
        if (r7 == null) goto L34;
        r7.onMapsSdkInitialized(zzc);     // Catch: Throwable -> L9
        goto L34
    L24:
        if (r1.zzd() != 2) goto L28;
        zzc = Renderer.LATEST;     // Catch: Throwable -> L9 RemoteException -> L26
    L28:
        r1.zzm(ObjectWrapper.wrap(r5), r3);     // Catch: Throwable -> L9 RemoteException -> L26
    L22:
        r3 = 0;
        goto L46
    L36:
        e = move-exception;
        throw new RuntimeRemoteException(e);     // Catch: Throwable -> L9
    L39:
        e = move-exception;
        return e.errorCode;
    L9:
        th = move-exception;
        throw th;
    }
}
