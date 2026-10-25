package com.google.android.gms.internal.measurement;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzrx extends zzsy {
    private final Context zza;
    private final zzsx zzb;
    private final Object zzc;
    private String zzd;

    public /* synthetic */ zzrx(zzrw r2, byte[] r3) {
        this.zzc = new Object();
        this.zzb = new zzsd(r2.zzc());
        this.zza = r2.zzb();
    }

    public static zzrw zza(Context r2) {
        return new zzrw(r2, null);
    }

    private final boolean zzh(Uri r2) {
        if (TextUtils.isEmpty(r2.getAuthority()) == false) goto L5;
        return false;
    L5:
        if (this.zza.getPackageName().equals(r2.getAuthority()) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private static final void zzi() {
        throw new zzsg("Android backend cannot perform remote operations without a remote backend");
    }

    @Override // com.google.android.gms.internal.measurement.zzsy
    public final zzsx zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final String zzc() {
        return "android";
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final InputStream zzd(Uri r2) {
        if (zzh(r2) == false) goto L5;
        zzi();
        throw null;
    L5:
        return zzsl.zzb(zzsc.zza(zzf(r2)));
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final boolean zze(Uri r2) {
        if (zzh(r2) == false) goto L5;
        zzi();
        throw null;
    L5:
        return zzsc.zza(zzf(r2)).exists();
    }

    @Override // com.google.android.gms.internal.measurement.zzsy
    public final Uri zzf(Uri r2) {
        if (zzh(r2) == true) goto L7;
        File r1 = zzg(r2);
        zzsb r22 = new zzsb(null);
        r22.zza(r1);
        return r22.zzb();
    L7:
        throw new zzsi("Operation across authorities is not allowed.");
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final File zzg(Uri r10) {
        if (zzh(r10) == true) goto L78;
        Context r0 = this.zza;
        if (r10.getScheme().equals("android") == false) goto L77;
        if (r10.getPathSegments().isEmpty() == true) goto L75;
        if (TextUtils.isEmpty(r10.getQuery()) == false) goto L73;
        ArrayList r2 = new ArrayList(r10.getPathSegments());
        String r4 = (String) r2.get(0);
        switch(r4.hashCode()) {
            case -1820761141: goto L49;
            case 94416770: goto L46;
            case 97434231: goto L43;
            case 835260319: goto L20;
            case 988548496: goto L17;
            case 991565957: goto L14;
            default: goto L71;
        };
    L14:
        if (r4.equals("directboot-files") == false) goto L71;
        File r102 = r0.createDeviceProtectedStorageContext().getFilesDir();
    L51:
        File r1 = new File(r102, TextUtils.join(File.separator, r2.subList(1, r2.size())));
        if (zzky.zzc(r0) == true) goto L69;
        Object r103 = this.zzc;
        monitor-enter(r103);
    L58:
        th = move-exception;
        throw th;
    L56:
        if (this.zzd != null) goto L60;
        this.zzd = zzry.zza(r0.createDeviceProtectedStorageContext()).getParentFile().getAbsolutePath();     // Catch: Throwable -> L58
    L60:
        String r9 = this.zzd;     // Catch: Throwable -> L58
        monitor-exit(r103);     // Catch: Throwable -> L58
        if (r1.getAbsolutePath().startsWith(r9) == true) goto L69;
        throw new zzsg("Cannot access credential-protected data from direct boot");
    L69:
        return r1;
    L17:
        if (r4.equals("directboot-cache") == false) goto L71;
        r102 = r0.createDeviceProtectedStorageContext().getCacheDir();
        goto L51
    L20:
        if (r4.equals("managed") == false) goto L71;
        File r42 = new File(zzry.zza(r0), "managed");
        if (r2.size() >= 3) goto L82;
    L41:
        r102 = r42;
        goto L51
    L82:
        String r104 = (String) r2.get(2);     // Catch: IllegalArgumentException -> L38
        Account r12 = zzrv.zza;     // Catch: IllegalArgumentException -> L38
        if ("shared".equals(r104) == false) goto L27;
        Account r105 = zzrv.zza;     // Catch: IllegalArgumentException -> L38
    L34:
        if (zzrv.zza.equals(r105) == true) goto L41;
        throw new zzsi("AccountManager cannot be null");
    L27:
        int r13 = r104.indexOf(58);     // Catch: IllegalArgumentException -> L38
        if (r13 < 0) goto L30;
        boolean r5 = true;
    L31:
        zzsq.zza(r5, "Malformed account", new Object[0]);     // Catch: IllegalArgumentException -> L38
        r105 = new Account(r104.substring(r13 + 1), r104.substring(0, r13));     // Catch: IllegalArgumentException -> L38
        goto L34
    L30:
        r5 = false;
    L38:
        e = move-exception;
        throw new zzsi(e);
    L43:
        if (r4.equals("files") == false) goto L71;
        r102 = zzry.zza(r0);
        goto L51
    L46:
        if (r4.equals("cache") == false) goto L71;
        r102 = r0.getCacheDir();
        goto L51
    L49:
        if (r4.equals("external") == false) goto L71;
        r102 = r0.getExternalFilesDir(null);
    L71:
        throw new zzsi(String.format("Path must start with a valid logical location: %s", new Object[]{r10}));
    L73:
        throw new zzsi("Did not expect uri to have query");
    L75:
        throw new zzsi(String.format("Path must start with a valid logical location: %s", new Object[]{r10}));
    L77:
        throw new zzsi("Scheme must be 'android'");
    L78:
        Model$$ExternalSyntheticBUOutline0.m("operation is not permitted in other authorities.");
        return null;
    }
}
