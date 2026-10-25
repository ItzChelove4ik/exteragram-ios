package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes5.dex */
public final class zzgu extends zzjf {
    private char zza;
    private long zzb;
    private String zzc;
    private final zzgs zzd;
    private final zzgs zze;
    private final zzgs zzf;
    private final zzgs zzg;
    private final zzgs zzh;
    private final zzgs zzi;
    private final zzgs zzj;
    private final zzgs zzk;
    private final zzgs zzl;

    public zzgu(zzic r4) {
        super(r4);
        this.zza = 0;
        this.zzb = -1;
        this.zzd = new zzgs(this, 6, false, false);
        this.zze = new zzgs(this, 6, true, false);
        this.zzf = new zzgs(this, 6, false, true);
        this.zzg = new zzgs(this, 5, false, false);
        this.zzh = new zzgs(this, 5, true, false);
        this.zzi = new zzgs(this, 5, false, true);
        this.zzj = new zzgs(this, 4, false, false);
        this.zzk = new zzgs(this, 3, false, false);
        this.zzl = new zzgs(this, 2, false, false);
    }

    public static Object zzl(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new zzgt(r1);
    }

    public static String zzo(boolean r2, String r3, Object r4, Object r5, Object r6) {
        String r42 = zzp(r2, r4);
        String r52 = zzp(r2, r5);
        String r22 = zzp(r2, r6);
        StringBuilder r62 = new StringBuilder();
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != null) goto L6;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (TextUtils.isEmpty(r3) == true) goto L8;
        r62.append(r3);
        r0 = ": ";
    L8:
        String r1 = ", ";
        if (TextUtils.isEmpty(r42) == true) goto L12;
        r62.append(r0);
        r62.append(r42);
        r0 = ", ";
    L12:
        if (TextUtils.isEmpty(r52) == true) goto L14;
        r62.append(r0);
        r62.append(r52);
    L16:
        if (TextUtils.isEmpty(r22) == true) goto L19;
        r62.append(r1);
        r62.append(r22);
    L19:
        return r62.toString();
    L14:
        r1 = r0;
        goto L16
    }

    public static String zzp(boolean r9, Object r10) {
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r10 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if ((r10 instanceof Integer) == false) goto L8;
        r10 = Long.valueOf(((Integer) r10).intValue());
    L8:
        int r3 = 0;
        if ((r10 instanceof Long) == false) goto L23;
        if (r9 == false) goto L12;
        Long r92 = (Long) r10;
        if (Math.abs(r92.longValue()) < 100) goto L16;
        char r102 = r10.toString().charAt(0);
        String r93 = String.valueOf(Math.abs(r92.longValue()));
        long r32 = Math.round(Math.pow(10.0d, r93.length() - 1));
        long r5 = Math.round(Math.pow(10.0d, r93.length()) - 1.0d);
        int r94 = String.valueOf(r32).length();
        if (r102 != '-') goto L20;
        r0 = "-";
    L20:
        int r103 = r0.length() + r94;
        StringBuilder r1 = new StringBuilder(((r103 + 3) + r0.length()) + String.valueOf(r5).length());
        r1.append(r0);
        r1.append(r32);
        r1.append("...");
        r1.append(r0);
        r1.append(r5);
        return r1.toString();
    L16:
        return r10.toString();
    L12:
        return r10.toString();
    L23:
        if ((r10 instanceof Boolean) == false) goto L27;
        return r10.toString();
    L27:
        if ((r10 instanceof Throwable) == false) goto L46;
        Throwable r104 = (Throwable) r10;
        if (r9 == false) goto L31;
        String r95 = r104.getClass().getName();
    L32:
        StringBuilder r02 = new StringBuilder(r95);
        String r96 = zzq(zzic.class.getCanonicalName());
        StackTraceElement[] r105 = r104.getStackTrace();
        int r12 = r105.length;
    L33:
        if (r3 >= r12) goto L44;
        StackTraceElement r2 = r105[r3];
        if (r2.isNativeMethod() == true) goto L42;
        String r4 = r2.getClassName();
        if (r4 == null) goto L42;
        if (zzq(r4).equals(r96) == false) goto L42;
        r02.append(": ");
        r02.append(r2);
    L42:
        r3 = r3 + 1;
    L44:
        return r02.toString();
    L31:
        r95 = r104.toString();
        goto L32
    L46:
        if ((r10 instanceof zzgt) == true) goto L48;
        if (r9 == false) goto L52;
        return "-";
    L52:
        return r10.toString();
    L48:
        return ((zzgt) r10).zza();
    }

    public static String zzq(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        int r0 = r3.lastIndexOf(46);
        if (r0 != (-1)) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return r3.substring(0, r0);
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    public final boolean zza() {
        return false;
    }

    public final zzgs zzb() {
        return this.zzd;
    }

    public final zzgs zzc() {
        return this.zze;
    }

    public final zzgs zzd() {
        return this.zzf;
    }

    public final zzgs zze() {
        return this.zzg;
    }

    public final zzgs zzf() {
        return this.zzh;
    }

    public final zzgs zzh() {
        return this.zzi;
    }

    public final zzgs zzi() {
        return this.zzj;
    }

    public final zzgs zzj() {
        return this.zzk;
    }

    public final zzgs zzk() {
        return this.zzl;
    }

    public final void zzm(int r8, boolean r9, boolean r10, String r11, Object r12, Object r13, Object r14) {
        if (r9 == false) goto L4;
    L6:
        if (r10 == false) goto L8;
        return;
    L8:
        if (r8 < 5) goto L23;
        Preconditions.checkNotNull(r11);
        zzhz r92 = this.zzu.zzi();
        if (r92 != null) goto L14;
        Log.println(6, zzn(), "Scheduler not set. Not logging error/warn");
        return;
    L14:
        if (r92.zzv() == true) goto L18;
        Log.println(6, zzn(), "Scheduler not initialized. Not logging error/warn");
        return;
    L18:
        if (r8 < 9) goto L20;
        r8 = 8;
    L20:
        r92.zzj(new zzgr(this, r8, r11, r12, r13, r14));
        return;
    L23:
        return;
    L4:
        if (Log.isLoggable(zzn(), r8) == false) goto L6;
        String r93 = zzo(false, r11, r12, r13, r14);
        Log.println(r8, zzn(), r93);
        goto L6
    }

    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    public final String zzn() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.zzc != null) goto L8;
        this.zzc = this.zzu.zzc().zzb();     // Catch: Throwable -> L6
    L8:
        Preconditions.checkNotNull(this.zzc);     // Catch: Throwable -> L6
        String r0 = this.zzc;     // Catch: Throwable -> L6
        monitor-exit(this);     // Catch: Throwable -> L6
        return r0;
    }

    public final /* synthetic */ char zzr() {
        return this.zza;
    }

    public final /* synthetic */ void zzs(char r1) {
        this.zza = r1;
    }

    public final /* synthetic */ long zzt() {
        return this.zzb;
    }

    public final /* synthetic */ void zzu(long r1) {
        this.zzb = 161000;
    }
}
