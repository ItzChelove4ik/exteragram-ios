package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.zzah$$ExternalSyntheticBUOutline0;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zzcc {
    private static final String zza = "zzcc";

    @SuppressLint({"StaticFieldLeak"})
    private static Context zzb;
    private static volatile zzf zzc;
    private static final Queue zzd = null;

    static {
        zzd = new ConcurrentLinkedQueue();
    }

    public static zzf zza(Context r5, MapsInitializer.Renderer r6) {
        Preconditions.checkNotNull(r5);
        String r0 = zza;
        Log.d(r0, "preferredRenderer: ".concat(String.valueOf(r6)));
        if (zzc != null) goto L25;
        int r1 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(r5, 13400000);
        if (r1 != 0) goto L38;
        zzc = zze(r5, r6);
        int r2 = zzc.zzd();     // Catch: RemoteException -> L34
        String r3 = r5.getPackageName();
        if (r2 == 2) goto L12;
    L20:
        Log.d(r0, "not early loading native code");
    L39:
        zzf r02 = zzc;     // Catch: RemoteException -> L31
        Context r52 = zzd(r5, r6);     // Catch: RemoteException -> L31
        Objects.requireNonNull(r52);     // Catch: RemoteException -> L31
        r02.zzl(ObjectWrapper.wrap(r52.getResources()), 20000000);     // Catch: RemoteException -> L31
        Queue r53 = zzd;
        if (r53.isEmpty() == true) goto L25;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r53.poll());     // Catch: RemoteException -> L28
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Preconditions.checkNotNull(null));     // Catch: RemoteException -> L28
        throw null;
    L28:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L31:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L12:
        if (r3.equals("com.google.android.apps.photos") == true) goto L20;
        Log.d(r0, "early loading native code");
        zzc.zzn(ObjectWrapper.wrap(zzd(r5, r6)));     // Catch: RemoteException -> L16 UnsatisfiedLinkError -> L19
    L16:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L19:
        Log.w(zza, "Caught UnsatisfiedLinkError attempting to load the LATEST renderer's native library. Attempting to use the LEGACY renderer instead.");
        zzb = null;
        zzc = zze(r5, MapsInitializer.Renderer.LEGACY);
    L34:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L38:
        throw new GooglePlayServicesNotAvailableException(r1);
    L25:
        return zzc;
    }

    private static Context zzc(Exception r2, Context r3) {
        Log.e(zza, "Failed to load maps module, use pre-Chimera", r2);
        return GooglePlayServicesUtil.getRemoteContext(r3);
    }

    private static Context zzd(Context r2, MapsInitializer.Renderer r3) {
        Context r0 = zzb;
        if (r0 == null) goto L5;
        return r0;
    L5:
        if (r3 != MapsInitializer.Renderer.LEGACY) goto L7;
        String r32 = "com.google.android.gms.maps_legacy_dynamite";
    L24:
        Context r22 = DynamiteModule.load(r2, DynamiteModule.PREFER_REMOTE, r32).getModuleContext();     // Catch: Exception -> L10
    L18:
        zzb = r22;
        if (r22 == null) goto L21;
        return r22;
    L21:
        GlShader$$ExternalSyntheticBUOutline1.m("Unable to load maps module, maps container context is null");
        return null;
    L10:
        e = move-exception;
        if (r32.equals("com.google.android.gms.maps_dynamite") == false) goto L26;
        r22 = zzc(e, r2);
        goto L18
    L26:
        Log.d(zza, "Attempting to load maps_dynamite again.");     // Catch: Exception -> L15
        r22 = DynamiteModule.load(r2, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();     // Catch: Exception -> L15
    L15:
        e = move-exception;
        r22 = zzc(e, r2);
        goto L18
    L7:
        r32 = "com.google.android.gms.maps_core_dynamite";
        goto L24
    }

    private static zzf zze(Context r2, MapsInitializer.Renderer r3) {
        Log.i(zza, "Making Creator dynamically");
        IBinder r22 = (IBinder) zzf(((ClassLoader) Preconditions.checkNotNull(zzd(r2, r3).getClassLoader())).loadClass("com.google.android.gms.maps.internal.CreatorImpl"));
        if (r22 == null) goto L12;
        IInterface r32 = r22.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
        if ((r32 instanceof zzf) == false) goto L11;
        return (zzf) r32;
    L11:
        return new zze(r22);
    L12:
        GlShader$$ExternalSyntheticBUOutline1.m("Unable to load maps module, IBinder for com.google.android.gms.maps.internal.CreatorImpl is null");
        return null;
    L14:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Unable to find dynamic class com.google.android.gms.maps.internal.CreatorImpl", e);
        return null;
    }

    private static Object zzf(Class r3) {
        return r3.newInstance();
    L5:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Unable to call the default constructor of ".concat(r3.getName()), e);
        return null;
    L8:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Unable to instantiate the dynamic class ".concat(r3.getName()), e);
        return null;
    }
}
