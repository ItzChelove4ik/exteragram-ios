package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;
import com.google.android.gms.cast.MediaError;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class zzlx implements Closeable {
    private static final Map zza = null;
    private final String zzb;
    private int zzc;
    private double zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private long zzh;

    static {
        zza = new HashMap();
    }

    private zzlx(String r3) {
        this.zzg = 2147483647L;
        this.zzh = -2147483648L;
        this.zzb = r3;
    }

    public /* synthetic */ zzlx(String r1, zzlw r2) {
        this("unusedTag");
    }

    private final void zza() {
        this.zzc = 0;
        this.zzd = 0.0d;
        this.zze = 0;
        this.zzg = 2147483647L;
        this.zzh = -2147483648L;
    }

    public static zzlx zze(String r2) {
        zzmw.zza();
        if (zzmw.zzb() == false) goto L5;
        Map r22 = zza;
        if (r22.get("detectorTaskWithResource#run") != null) goto L10;
        r22.put("detectorTaskWithResource#run", new zzlx("detectorTaskWithResource#run"));
    L10:
        return (zzlx) r22.get("detectorTaskWithResource#run");
    L5:
        return zzlv.zza();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long r0 = this.zze;
        if (r0 == 0) goto L6;
        zzd(r0);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Did you forget to call start()?");
    }

    public zzlx zzb() {
        this.zze = SystemClock.elapsedRealtimeNanos() / 1000;
        return this;
    }

    public void zzc(long r9) {
        long r0 = SystemClock.elapsedRealtimeNanos() / 1000;
        long r2 = this.zzf;
        if (r2 != 0) goto L5;
    L7:
        this.zzf = r0;
        this.zzc++;
        this.zzd += r9;
        this.zzg = Math.min(this.zzg, r9);
        this.zzh = Math.max(this.zzh, r9);
        if ((this.zzc % 50) != 0) goto L11;
        String.format(Locale.US, "[%s] cur=%dus, counts=%d, min=%dus, max=%dus, avg=%dus", new Object[]{this.zzb, Long.valueOf(r9), Integer.valueOf(this.zzc), Long.valueOf(this.zzg), Long.valueOf(this.zzh), Integer.valueOf((int) (this.zzd / this.zzc))});
        zzmw.zza();
    L11:
        if ((this.zzc % MediaError.DetailedErrorCode.SEGMENT_UNKNOWN) != 0) goto L14;
        zza();
        return;
    L14:
        return;
    L5:
        if ((r0 - r2) < 1000000) goto L7;
        zza();
        goto L7
    }

    public void zzd(long r5) {
        zzc((SystemClock.elapsedRealtimeNanos() / 1000) - r5);
    }
}
