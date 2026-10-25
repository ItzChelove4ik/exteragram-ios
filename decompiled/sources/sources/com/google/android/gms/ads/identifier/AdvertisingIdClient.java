package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads_identifier.zze;
import com.google.android.gms.internal.ads_identifier.zzf;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* loaded from: classes4.dex */
public class AdvertisingIdClient {
    BlockingServiceConnection zza;
    zzf zzb;
    boolean zzc;
    final Object zzd;
    zzb zze;
    final long zzf;
    private final Context zzg;

    public static final class Info {
        private final String zza;
        private final boolean zzb;

        @Deprecated
        public Info(String r1, boolean r2) {
            this.zza = r1;
            this.zzb = r2;
        }

        public String getId() {
            return this.zza;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzb;
        }

        public String toString() {
            String r0 = this.zza;
            boolean r3 = this.zzb;
            StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 7);
            r2.append("{");
            r2.append(r0);
            r2.append("}");
            r2.append(r3);
            return r2.toString();
        }
    }

    @VisibleForTesting
    public AdvertisingIdClient(Context r1, long r2, boolean r4, boolean r5) {
        this.zzd = new Object();
        Preconditions.checkNotNull(r1);
        if (r4 == false) goto L7;
        Context r42 = r1.getApplicationContext();
        if (r42 == null) goto L7;
        r1 = r42;
    L7:
        this.zzg = r1;
        this.zzc = false;
        this.zzf = r2;
    }

    public static Info getAdvertisingIdInfo(Context r9) {
        AdvertisingIdClient r1 = new AdvertisingIdClient(r9, -1, true, false);
        long r2 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L6
        r1.zzb(false);     // Catch: Throwable -> L6
        Info r92 = r1.zzd(-1);     // Catch: Throwable -> L6
        r1.zzc(r92, true, 0.0f, SystemClock.elapsedRealtime() - r2, _UrlKt.FRAGMENT_ENCODE_SET, null);     // Catch: Throwable -> L6
        r1.zza();
        return r92;
    L6:
        th = move-exception;
        r1.zzc(null, true, 0.0f, -1, _UrlKt.FRAGMENT_ENCODE_SET, th);     // Catch: Throwable -> L10
        throw th;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        r1.zza();
        throw th;
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean r0) {
    }

    private final Info zzd(int r4) {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        monitor-enter(this);
    L20:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == true) goto L31;
        Object r42 = this.zzd;     // Catch: Throwable -> L20
        monitor-enter(r42);     // Catch: Throwable -> L20
        zzb r0 = this.zze;     // Catch: Throwable -> L25
        if (r0 == null) goto L28;
        if (r0.zzb == false) goto L28;
        monitor-exit(r42);     // Catch: Throwable -> L25
        zzb(false);     // Catch: Throwable -> L20 Exception -> L22
    L16:
        if (this.zzc == true) goto L31;
        throw new IOException("AdvertisingIdClient cannot reconnect.");     // Catch: Throwable -> L20
    L22:
        e = move-exception;
        throw new IOException("AdvertisingIdClient cannot reconnect.", e);     // Catch: Throwable -> L20
    L28:
        throw new IOException("AdvertisingIdClient is not connected.");     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L31:
        Preconditions.checkNotNull(this.zza);     // Catch: Throwable -> L20
        Preconditions.checkNotNull(this.zzb);     // Catch: Throwable -> L20
        Info r43 = new Info(this.zzb.zzc(), this.zzb.zze(true));     // Catch: Throwable -> L20 RemoteException -> L36
        monitor-exit(this);     // Catch: Throwable -> L20
        zze();
        return r43;
    L36:
        e = move-exception;
        Log.i("AdvertisingIdClient", "GMS remote exception ", e);     // Catch: Throwable -> L20
        throw new IOException("Remote exception");     // Catch: Throwable -> L20
    }

    private final void zze() {
        Object r0 = this.zzd;
        monitor-enter(r0);
        zzb r1 = this.zze;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.zza.countDown();     // Catch: Throwable -> L9
        this.zze.join();     // Catch: Throwable -> L9 InterruptedException -> L18
    L11:
        long r12 = this.zzf;     // Catch: Throwable -> L9
        if (r12 <= 0) goto L14;
        this.zze = new zzb(this, r12);     // Catch: Throwable -> L9
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final void finalize() {
        zza();
        super.finalize();
    }

    public final void zza() {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            this = this;
            java.lang.String r0 = "Calling this from your main thread can lead to deadlock"
            com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r0)
            monitor-enter(r3)
            android.content.Context r0 = r3.zzg     // Catch: java.lang.Throwable -> L31
            if (r0 == 0) goto L33
            com.google.android.gms.common.BlockingServiceConnection r0 = r3.zza     // Catch: java.lang.Throwable -> L31
            if (r0 != 0) goto Lf
            goto L33
        Lf:
            boolean r0 = r3.zzc     // Catch: java.lang.Throwable -> L1f
            if (r0 == 0) goto L27
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch: java.lang.Throwable -> L1f
            android.content.Context r1 = r3.zzg     // Catch: java.lang.Throwable -> L1f
            com.google.android.gms.common.BlockingServiceConnection r2 = r3.zza     // Catch: java.lang.Throwable -> L1f
            r0.unbindService(r1, r2)     // Catch: java.lang.Throwable -> L1f
            goto L27
        L1f:
            r0 = move-exception
            java.lang.String r1 = "AdvertisingIdClient"
            java.lang.String r2 = "AdvertisingIdClient unbindService failed."
            android.util.Log.i(r1, r2, r0)     // Catch: java.lang.Throwable -> L31
        L27:
            r0 = 0
            r3.zzc = r0     // Catch: java.lang.Throwable -> L31
            r0 = 0
            r3.zzb = r0     // Catch: java.lang.Throwable -> L31
            r3.zza = r0     // Catch: java.lang.Throwable -> L31
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L31
            return
        L31:
            r0 = move-exception
            goto L35
        L33:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L31
            return
        L35:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L31
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.zza():void");
    }

    @VisibleForTesting
    public final void zzb(boolean r6) {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == false) goto L9;
        zza();     // Catch: Throwable -> L7
    L9:
        Context r0 = this.zzg;     // Catch: Throwable -> L7
        r0.getPackageManager().getPackageInfo("com.android.vending", 0);     // Catch: Throwable -> L7 PackageManager.NameNotFoundException -> L38
        int r1 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(r0, 12451000);     // Catch: Throwable -> L7
        if (r1 != 0) goto L14;
    L18:
        BlockingServiceConnection r12 = new BlockingServiceConnection();     // Catch: Throwable -> L7
        Intent r2 = new Intent("com.google.android.gms.ads.identifier.service.START");     // Catch: Throwable -> L7
        r2.setPackage("com.google.android.gms");     // Catch: Throwable -> L7
        if (ConnectionTracker.getInstance().bindService(r0, r2, r12, 1) == false) goto L34;
        this.zza = r12;     // Catch: Throwable -> L7
        this.zzb = zze.zza(r12.getServiceWithTimeout(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS));     // Catch: Throwable -> L7
        this.zzc = true;     // Catch: Throwable -> L7
        if (r6 == false) goto L26;
        zze();     // Catch: Throwable -> L7
    L26:
        monitor-exit(this);     // Catch: Throwable -> L7
        return;
    L28:
        th = move-exception;
        throw new IOException(th);     // Catch: Throwable -> L7
    L32:
        throw new IOException("Interrupted exception");     // Catch: Throwable -> L7
    L34:
        throw new IOException("Connection failure");     // Catch: Throwable -> L7
    L35:
        th = move-exception;
        throw new IOException(th);     // Catch: Throwable -> L7
    L14:
        if (r1 == 2) goto L18;
        throw new IOException("Google Play services not available");     // Catch: Throwable -> L7
    L39:
        throw new GooglePlayServicesNotAvailableException(9);     // Catch: Throwable -> L7
    }

    @VisibleForTesting
    public final boolean zzc(Info r3, boolean r4, float r5, long r6, String r8, Throwable r9) {
        if (Math.random() > 0.0d) goto L16;
        HashMap r42 = new HashMap();
        String r82 = "1";
        r42.put("app_context", "1");
        if (r3 != null) goto L7;
    L12:
        if (r9 == null) goto L14;
        r42.put("error", r9.getClass().getName());
    L14:
        r42.put("tag", "AdvertisingIdClient");
        r42.put("time_spent", Long.toString(r6));
        new zza(this, r42).start();
        return true;
    L7:
        if (true == r3.isLimitAdTrackingEnabled()) goto L9;
        r82 = MVEL.VERSION_SUB;
    L9:
        r42.put("limit_ad_tracking", r82);
        String r32 = r3.getId();
        if (r32 == null) goto L12;
        r42.put("ad_id_size", Integer.toString(r32.length()));
        goto L12
    L16:
        return false;
    }
}
