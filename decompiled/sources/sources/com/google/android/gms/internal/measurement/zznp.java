package com.google.android.gms.internal.measurement;

import android.os.Build;
import android.util.Log;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zznp implements zzom {
    private final String zza;
    private final zzph zzb;

    public zznp(String r1, String r2, zzph r3) {
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // com.google.android.gms.internal.measurement.zzom, com.google.common.base.Supplier
    public final Object get() {
        return Preconditions.checkNotNull(zzb(zzlk.zzb()));
    }

    public abstract Object zza();

    public final Object zzaA(zznw r9, zzlk r10, String r11) {
        int r112 = r9.zzf();
        if (r112 != (-1)) goto L5;
    L6:
        monitor-enter(r9);
        int r113 = r9.zzf();     // Catch: Throwable -> L10
        Object r2 = null;
        if (r113 != (-1)) goto L12;
        zzlk.zzl();     // Catch: Throwable -> L10
        Preconditions.checkNotNull(r10);     // Catch: Throwable -> L10
        zzpg r0 = this.zzb.zza(r10, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L10
        r9.zzj(r0.zzc());     // Catch: Throwable -> L10
    L13:
        int r1 = r9.zzg().zza();     // Catch: Throwable -> L10
        if (r113 >= r1) goto L55;
        zzlk.zzl();     // Catch: Throwable -> L10
        Preconditions.checkNotNull(r10);     // Catch: Throwable -> L10
        Optional r3 = zzlf.zza(r10.zzc());     // Catch: Throwable -> L10
        if (r3.isPresent() == false) goto L19;
        String r4 = ((zzle) r3.get()).zza(zzlg.zza("com.google.android.gms.measurement"), null, null, this.zza);     // Catch: Throwable -> L10
        if (r4 == null) goto L19;
        Object r42 = zzc(r4);     // Catch: Throwable -> L10 IOException -> L23 Throwable -> L25
    L27:
        if (r0 != null) goto L29;
        r0 = this.zzb.zza(r10, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L10
    L29:
        String r114 = r0.zzb();     // Catch: Throwable -> L10
        if (Build.VERSION.SDK_INT >= 26) goto L32;
    L36:
        Object r115 = r0.zza(this.zza, false);     // Catch: Throwable -> L10
        if (r115 == null) goto L46;
        r2 = zzd(r115);     // Catch: Throwable -> L10 Throwable -> L41 ClassCastException -> L43
    L41:
        e = move-exception;
        Log.e("FilePhenotypeFlags", "Invalid Phenotype flag value for flag ".concat(this.zza), e);     // Catch: Throwable -> L10
    L46:
        if (true == r3.isPresent()) goto L49;
        r42 = r2;
    L49:
        if (r42 != null) goto L51;
        r42 = zza();     // Catch: Throwable -> L10
    L51:
        if (r42 == null) goto L53;
        r9.zzh(r42);     // Catch: Throwable -> L10
        r9.zzi(r1);     // Catch: Throwable -> L10
    L53:
        monitor-exit(r9);     // Catch: Throwable -> L10
        return r42;
    L32:
        if (r10.zzc().getPackageName().equals("com.android.vending") == true) goto L36;
        if (r114.startsWith("com.google.android.gms.measurement#") == true) goto L36;
        zzpx.zza(r10.zzg().submit(new zzpn(r10, r114)));     // Catch: Throwable -> L10
    L25:
        e = move-exception;
        Log.e("FilePhenotypeFlags", "Invalid Phenotype flag value for flag ".concat(this.zza), e);     // Catch: Throwable -> L10
    L19:
        r42 = null;
        goto L27
    L55:
        monitor-exit(r9);     // Catch: Throwable -> L10
    L57:
        return r9.zze();
    L12:
        r0 = null;
    L10:
        th = move-exception;
        throw th;
    L5:
        if (r112 >= r9.zzg().zza()) goto L57;
        goto L6
    }

    public abstract Object zzb(zzlk r1);

    public abstract Object zzc(String r1);

    public abstract Object zzd(Object r1);
}
