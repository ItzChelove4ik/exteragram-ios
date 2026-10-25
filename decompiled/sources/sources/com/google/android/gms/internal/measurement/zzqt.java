package com.google.android.gms.internal.measurement;

import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzqt {
    private static Boolean zza;
    private final zzlk zzb;
    private final Uri zzc;
    private final String zzd;
    private final String zze;

    static {
    }

    public zzqt(zzlk r1, String r2, String r3, boolean r4) {
        this.zzb = r1;
        this.zzd = r2;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        zzrz r12 = zzsa.zza(r1.zzc());
        r12.zzb("phenotype");
        StringBuilder r42 = new StringBuilder(String.valueOf(r2).length() + 4);
        r42.append("/");
        r42.append(r2);
        r42.append(".pb");
        r12.zzc(r42.toString());
        this.zzc = r12.zzd();
    }

    private static boolean zzf() {
        if (zza != null) goto L11;
        if (Build.VERSION.SDK_INT < 28) goto L12;
        zza = Boolean.valueOf(Process.isIsolated());
        goto L11
    L12:
        Boolean r0 = (Boolean) Preconditions.checkNotNull(Process.class.getMethod("isIsolated", null).invoke(Process.class, null));     // Catch: ReflectiveOperationException -> L9
        r0.booleanValue();     // Catch: ReflectiveOperationException -> L9
        zza = r0;     // Catch: ReflectiveOperationException -> L9
    L9:
        zza = Boolean.FALSE;
    L11:
        return zza.booleanValue();
    }

    public final zzqs zza() {
        zzlk r0 = this.zzb;
        if (zzky.zzb(r0.zzc()) == false) goto L7;
        return zzqs.zza(zzqv.zzi(), new zzqr(3, 17));
    L7:
        if (zzf() == true) goto L56;
        zzqn r1 = r0.zzd().zzc(false);
        String r4 = this.zzd;
        zzabz r5 = zzabz.zzd;
        int r6 = zzlg.$r8$clinit;
        int r62 = r4.indexOf("#");
        if (r62 < 0) goto L11;
        String r63 = r4.substring(0, r62);
    L16:
        int r52 = r1.zzg(r5, r63);
        if (r52 == 0) goto L58;
        zznv r12 = new zznv(null, new zzqr(r52));
    L44:
        if (r12.zza() != null) goto L46;
        int r02 = r12.zzb().zzb();
        return zzqs.zza((zzqv) this.zzb.zzi().zza(this.zzc, zzss.zzb(zzqv.zzi())), new zzqr(4, r02));
    L50:
        zzlk r03 = this.zzb;
        zzlz.zza(Level.INFO, r03.zzg(), "Unable to retrieve flag snapshot for %s, using defaults.", new Object[]{this.zzd});
        if (this.zzb() == false) goto L54;
        return zzqs.zzc(zznd.zza(), new zzqr(3, 16));
    L54:
        return zzqs.zza(zzqv.zzi(), new zzqr(3, 11));
    L46:
        return zzqs.zzc((zznd) Preconditions.checkNotNull(r12.zza()), r12.zzb());
    L58:
        String r53 = r1.zzc();     // Catch: Exception -> L25
        if (r53.isEmpty() == false) goto L28;
        Optional r54 = r0.zze();     // Catch: Exception -> L25
        if (r54.isPresent() == true) goto L27;
        zzlz.zza(Level.WARNING, r0.zzg(), "Unable to get GMS application info, using defaults.", new Object[0]);     // Catch: Exception -> L25
        zznv r3 = new zznv(zznd.zza(), new zzqr(3, 7));     // Catch: Exception -> L25
    L24:
        r12 = r3;
        goto L44
    L27:
        r53 = ((ApplicationInfo) r54.get()).dataDir;     // Catch: Exception -> L25
    L28:
        String r32 = File.separator;     // Catch: Exception -> L25
        String r64 = r1.zzb();     // Catch: Exception -> L25
        StringBuilder r9 = new StringBuilder((String.valueOf(r53).length() + String.valueOf(r32).length()) + String.valueOf(r64).length());     // Catch: Exception -> L25
        r9.append(r53);     // Catch: Exception -> L25
        r9.append(r32);     // Catch: Exception -> L25
        r9.append(r64);     // Catch: Exception -> L25
        String r55 = r9.toString();     // Catch: Exception -> L25
        zzmz r65 = new zzmz(r1.zza(), r4, this.zze);     // Catch: Exception -> L25
        Uri.Builder r42 = new Uri.Builder().scheme("file");     // Catch: Exception -> L25
        String r66 = r65.zza().toString();     // Catch: Exception -> L25
        StringBuilder r92 = new StringBuilder(((String.valueOf(r32).length() + r55.length()) + String.valueOf(r32).length()) + r66.length());     // Catch: Exception -> L25
        r92.append(r32);     // Catch: Exception -> L25
        r92.append(r55);     // Catch: Exception -> L25
        r92.append(r32);     // Catch: Exception -> L25
        r92.append(r66);     // Catch: Exception -> L25
        Uri r33 = r42.appendEncodedPath(r92.toString()).build();     // Catch: Exception -> L25
        StrictMode.ThreadPolicy r43 = StrictMode.getThreadPolicy();     // Catch: Exception -> L25
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(r43).permitDiskReads().build());     // Catch: Exception -> L25
        r3 = new zznv((zznd) r0.zzi().zza(r33, new zzna(r1.zzf().zza())), new zzqr(5, 2));     // Catch: Throwable -> L32 zzaeh -> L34 FileNotFoundException -> L38
        StrictMode.setThreadPolicy(r43);     // Catch: Exception -> L25
        goto L24
    L32:
        th = move-exception;
        StrictMode.setThreadPolicy(r43);     // Catch: Exception -> L25
        throw th;     // Catch: Exception -> L25
    L34:
        e = move-exception;
        zzlz.zzb(Level.SEVERE, this.zzb.zzg(), e, "Failed to parse snapshot from shared storage for %s", new Object[]{this.zzd});     // Catch: Throwable -> L32
        r12 = new zznv(null, new zzqr(9));     // Catch: Throwable -> L32
    L36:
        StrictMode.setThreadPolicy(r43);     // Catch: Exception -> L25
    L38:
        zzlz.zza(Level.INFO, this.zzb.zzg(), "Shared storage file not found for %s", new Object[]{this.zzd});     // Catch: Throwable -> L32
        r12 = new zznv(null, new zzqr(8));     // Catch: Throwable -> L32
    L25:
        e = move-exception;
        zzlk r13 = this.zzb;
        zzlz.zzb(Level.WARNING, r13.zzg(), e, "Failed to read shared file for %s", new Object[]{this.zzd});
        r3 = new zznv(zznd.zza(), new zzqr(3, 10));
        goto L24
    L11:
        if (r4.contains("@") == true) goto L13;
        r63 = r4;
        goto L16
    L13:
        f$$ExternalSyntheticBUOutline1.m("Invalid package name: ".concat(r4));
        return null;
    L56:
        return zzqs.zza(zzqv.zzi(), new zzqr(3, 18));
    }

    public final boolean zzb() {
        return this.zzb.zzd().zzb(false, zzabz.zzd);
    }

    public final ListenableFuture zzc(zzqv r2) {
        return Futures.submit(new zzqq(this, r2), this.zzb.zzg());
    }

    public final ListenableFuture zzd(String r3) {
        zzlk r32 = this.zzb;
        return Futures.transform(r32.zzh().zza(this.zzd, _UrlKt.FRAGMENT_ENCODE_SET), zzqp.zza, r32.zzg());
    }

    public final /* synthetic */ Void zze(zzqv r6) {
        zzse r0 = new zzse();
        zzru r1 = this.zzb.zzi();     // Catch: Throwable -> L5 IOException -> L7
        Uri r2 = this.zzc;     // Catch: Throwable -> L5 IOException -> L7
        zzsv r62 = zzsv.zzb(r6);     // Catch: Throwable -> L5 IOException -> L7
        r62.zzc(new zzro[]{r0});     // Catch: Throwable -> L5 IOException -> L7
        Void r63 = (Void) r1.zza(r2, r62);     // Catch: Throwable -> L5 IOException -> L7
        return null;
    L5:
        e = move-exception;
        zzlz.zzb(Level.WARNING, this.zzb.zzg(), e, "Failed to update snapshot for %s flags may be stale.", new Object[]{this.zzd});
        return null;
    }
}
