package com.google.android.gms.internal.clearcut;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.UserManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzae<T> {
    private static final Object zzdn = null;
    private static boolean zzdo = false;
    private static volatile Boolean zzdp;
    private static volatile Boolean zzdq;

    @SuppressLint({"StaticFieldLeak"})
    private static Context zzh;
    private final zzao zzdr;
    final String zzds;
    private final String zzdt;
    private final T zzdu;
    private T zzdv;
    private volatile zzab zzdw;
    private volatile SharedPreferences zzdx;

    static {
        zzdn = new Object();
    }

    private zzae(zzao r4, String r5, T r6) {
        this.zzdv = null;
        this.zzdw = null;
        this.zzdx = null;
        if (zzao.zza(r4) != null) goto L10;
        if (zzao.zzb(r4) != null) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Must pass a valid SharedPreferences file name or ContentProvider URI");
        throw null;
    L10:
        if (zzao.zza(r4) != null) goto L12;
    L16:
        this.zzdr = r4;
        String r0 = String.valueOf(zzao.zzc(r4));
        String r1 = String.valueOf(r5);
        if (r1.length() == 0) goto L19;
        String r02 = r0.concat(r1);
    L20:
        this.zzdt = r02;
        String r42 = String.valueOf(zzao.zzd(r4));
        String r52 = String.valueOf(r5);
        if (r52.length() == 0) goto L23;
        String r43 = r42.concat(r52);
    L24:
        this.zzds = r43;
        this.zzdu = r6;
        return;
    L23:
        r43 = new String(r42);
        goto L24
    L19:
        r02 = new String(r0);
        goto L20
    L12:
        if (zzao.zzb(r4) == null) goto L16;
        f$$ExternalSyntheticBUOutline1.m("Must pass one of SharedPreferences file name or ContentProvider URI");
        throw null;
    }

    public /* synthetic */ zzae(zzao r1, String r2, Object r3, zzai r4) {
        this(r1, r2, r3);
    }

    public static void maybeInit(Context r2) {
        if (zzh != null) goto L24;
        Object r0 = zzdn;
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L7:
        if (r2.isDeviceProtectedStorage() == true) goto L14;
        Context r1 = r2.getApplicationContext();     // Catch: Throwable -> L16
        if (r1 == null) goto L14;
        r2 = r1;
    L14:
        if (zzh == r2) goto L18;
        zzdp = null;     // Catch: Throwable -> L16
    L18:
        zzh = r2;     // Catch: Throwable -> L16
        monitor-exit(r0);     // Catch: Throwable -> L16
        zzdo = false;
        return;
    }

    private static <T> zzae<T> zza(zzao r1, String r2, T r3, zzan<T> r4) {
        return new zzal(r1, r2, r3, r4);
    }

    private static zzae<String> zza(zzao r1, String r2, String r3) {
        return new zzak(r1, r2, r3);
    }

    private static zzae<Boolean> zza(zzao r1, String r2, boolean r3) {
        return new zzaj(r1, r2, Boolean.valueOf(r3));
    }

    private static <V> V zza(zzam<V> r2) {
        return r2.zzp();
    L4:
        long r0 = Binder.clearCallingIdentity();
        V r22 = r2.zzp();     // Catch: Throwable -> L8
        Binder.restoreCallingIdentity(r0);
        return r22;
    L8:
        th = move-exception;
        Binder.restoreCallingIdentity(r0);
        throw th;
    }

    public static boolean zza(String r1, boolean r2) {
        if (zzn() == true) goto L5;
        return false;
    L5:
        return ((Boolean) zza(new zzah(r1, false))).booleanValue();
    }

    public static /* synthetic */ zzae zzb(zzao r0, String r1, Object r2, zzan r3) {
        return zza(r0, r1, r2, r3);
    }

    public static /* synthetic */ zzae zzb(zzao r0, String r1, String r2) {
        return zza(r0, r1, null);
    }

    public static /* synthetic */ zzae zzb(zzao r0, String r1, boolean r2) {
        return zza(r0, r1, false);
    }

    public static final /* synthetic */ Boolean zzb(String r1, boolean r2) {
        return Boolean.valueOf(zzy.zza(zzh.getContentResolver(), r1, r2));
    }

    @TargetApi(24)
    private final T zzl() {
        if (zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false) == false) goto L5;
        String r4 = String.valueOf(this.zzds);
        if (r4.length() == 0) goto L36;
        String r42 = "Bypass reading Phenotype values for flag: ".concat(r4);
    L37:
        Log.w("PhenotypeFlag", r42);
    L38:
        return null;
    L36:
        r42 = new String("Bypass reading Phenotype values for flag: ");
        goto L37
    L5:
        if (zzao.zzb(this.zzdr) == null) goto L14;
        if (this.zzdw != null) goto L9;
        this.zzdw = zzab.zza(zzh.getContentResolver(), zzao.zzb(this.zzdr));
    L9:
        String r0 = (String) zza(new zzaf(this, this.zzdw));
        if (r0 == null) goto L38;
        return zzb(r0);
    L14:
        if (zzao.zza(this.zzdr) == null) goto L38;
        if (zzh.isDeviceProtectedStorage() == false) goto L18;
        boolean r02 = true;
    L24:
        if (r02 == true) goto L27;
        return null;
    L27:
        if (this.zzdx != null) goto L29;
        this.zzdx = zzh.getSharedPreferences(zzao.zza(this.zzdr), 0);
    L29:
        SharedPreferences r03 = this.zzdx;
        if (r03.contains(this.zzds) == false) goto L38;
        return zza(r03);
    L18:
        if (zzdq != null) goto L20;
    L21:
        zzdq = Boolean.valueOf(((UserManager) zzh.getSystemService(UserManager.class)).isUserUnlocked());
    L22:
        r02 = zzdq.booleanValue();
        goto L24
    L20:
        if (zzdq.booleanValue() == true) goto L22;
        goto L21
    }

    private final T zzm() {
        if (zzao.zzf(this.zzdr) == false) goto L5;
        return null;
    L5:
        if (zzn() == false) goto L12;
        String r0 = (String) zza(new zzag(this));
        if (r0 != null) goto L9;
        return null;
    L9:
        return zzb(r0);
    L12:
        return null;
    }

    private static boolean zzn() {
        if (zzdp != null) goto L12;
        Context r0 = zzh;
        boolean r1 = false;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (PermissionChecker.checkCallingOrSelfPermission(r0, "com.google.android.providers.gsf.permission.READ_GSERVICES") != 0) goto L9;
        r1 = true;
    L9:
        zzdp = Boolean.valueOf(r1);
    L12:
        return zzdp.booleanValue();
    }

    public final T get() {
        if (zzh != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Must call PhenotypeFlag.init() first");
        return null;
    L5:
        if (zzao.zze(this.zzdr) == false) goto L12;
        T r0 = zzm();
        if (r0 == null) goto L9;
        return r0;
    L9:
        T r02 = zzl();
        if (r02 == null) goto L19;
        return r02;
    L19:
        return this.zzdu;
    L12:
        T r03 = zzl();
        if (r03 == null) goto L15;
        return r03;
    L15:
        T r04 = zzm();
        if (r04 == null) goto L19;
        return r04;
    }

    public abstract T zza(SharedPreferences r1);

    public abstract T zzb(String r1);

    public final /* synthetic */ String zzo() {
        return zzy.zza(zzh.getContentResolver(), this.zzdt, null);
    }
}
