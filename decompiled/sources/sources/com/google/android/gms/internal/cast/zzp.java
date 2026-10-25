package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import java.math.BigInteger;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public final class zzp {
    private static final Logger zza = null;
    private static final String zzb = null;
    private final String zzc;
    private final Map zzd;
    private final Map zze;

    static {
        zza = new Logger("ApplicationAnalyticsUtils");
        zzb = "22.3.1";
    }

    public zzp(Bundle r1, String r2) {
        this.zzc = r2;
        this.zzd = zzaz.zza(r1, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR");
        this.zze = zzaz.zza(r1, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON");
    }

    private final zzqq zzh(zzo r7) {
        zzqq r0 = zzqr.zzc();
        r0.zza(r7.zzd);
        int r1 = r7.zze;
        r7.zze = r1 + 1;
        r0.zzg(r1);
        String r12 = r7.zzc;
        if (r12 == null) goto L5;
        r0.zzf(r12);
    L5:
        zzur r13 = zzus.zza();
        if (TextUtils.isEmpty(r7.zzh) == true) goto L9;
        r0.zzb(r7.zzh);
        r13.zza(r7.zzh);
    L9:
        if (TextUtils.isEmpty(r7.zzi) == true) goto L12;
        r13.zzb(r7.zzi);
    L12:
        if (TextUtils.isEmpty(r7.zzj) == true) goto L15;
        r13.zzc(r7.zzj);
    L15:
        if (TextUtils.isEmpty(r7.zzk) == true) goto L18;
        r13.zzd(r7.zzk);
    L18:
        if (TextUtils.isEmpty(r7.zzl) == true) goto L21;
        r13.zze(r7.zzl);
    L21:
        if (TextUtils.isEmpty(r7.zzm) == true) goto L23;
        r13.zzf(r7.zzm);
    L23:
        r13.zzg(zzco.zza(r7.zzn));
        r0.zzn((zzus) r13.zzu());
        zzqb r14 = zzqc.zza();
        r14.zzb(zzb);
        r14.zza(this.zzc);
        r0.zzl((zzqc) r14.zzu());
        zzqf r6 = zzqg.zza();
        if (r7.zzb == null) goto L26;
        zzro r15 = zzrp.zza();
        r15.zza(r7.zzb);
        r6.zza((zzrp) r15.zzu());
    L26:
        r6.zzb(false);
        String r2 = r7.zzf;
        if (r2 != null) goto L35;
    L33:
        r6.zzf(r7.zzg);
        r6.zzg(r7.zzb());
        r6.zzj(r7.zzo);
        r0.zzj(r6);
        return r0;
    L35:
        String r3 = r2.replace("-", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: NumberFormatException -> L30
        long r16 = new BigInteger(r3.substring(0, Math.min(16, r3.length())), 16).longValue();     // Catch: NumberFormatException -> L30
    L32:
        r6.zzc(r16);
    L30:
        e = move-exception;
        zza.w(e, "receiverSessionId %s is not valid for hash", new Object[]{r2});
        r16 = 0;
        goto L32
    }

    private static void zzi(zzqq r1, boolean r2) {
        zzqf r0 = zzqg.zzc(r1.zzh());
        r0.zzb(r2);
        r1.zzj(r0);
    }

    public final zzqr zza(zzo r1) {
        return (zzqr) zzh(r1).zzu();
    }

    public final zzqr zzb(zzo r2) {
        zzqq r1 = zzh(r2);
        if (r2.zzp != 1) goto L6;
        zzqf r22 = zzqg.zzc(r1.zzh());
        r22.zzd(17);
        r1.zzi((zzqg) r22.zzu());
    L6:
        return (zzqr) r1.zzu();
    }

    public final zzqr zzc(zzo r2) {
        zzqq r1 = zzh(r2);
        zzqf r22 = zzqg.zzc(r1.zzh());
        r22.zzd(10);
        r1.zzi((zzqg) r22.zzu());
        zzi(r1, true);
        return (zzqr) r1.zzu();
    }

    public final zzqr zzd(zzo r1, boolean r2) {
        zzqq r0 = zzh(r1);
        zzi(r0, r2);
        return (zzqr) r0.zzu();
    }

    @Pure
    public final zzqr zze(zzo r5, int r6) {
        zzqq r52 = zzh(r5);
        zzqf r0 = zzqg.zzc(r52.zzh());
        Map r1 = this.zze;
        if (r1 == null) goto L8;
        Integer r2 = Integer.valueOf(r6);
        if (r1.containsKey(r2) == false) goto L8;
        int r12 = ((Integer) Preconditions.checkNotNull((Integer) r1.get(r2))).intValue();
    L9:
        r0.zzd(r12);
        Map r4 = this.zzd;
        if (r4 == null) goto L15;
        Integer r13 = Integer.valueOf(r6);
        if (r4.containsKey(r13) == false) goto L15;
        int r42 = ((Integer) Preconditions.checkNotNull((Integer) r4.get(r13))).intValue();
    L16:
        r0.zze(r42);
        r52.zzi((zzqg) r0.zzu());
        return (zzqr) r52.zzu();
    L15:
        r42 = r6 + 10000;
    L8:
        r12 = r6 + 10000;
        goto L9
    }

    public final zzqr zzf(zzo r1, int r2) {
        zzqq r0 = zzh(r1);
        zzqf r12 = zzqg.zzc(r0.zzh());
        r12.zzh(r2);
        r0.zzi((zzqg) r12.zzu());
        return (zzqr) r0.zzu();
    }

    public final zzqr zzg(zzo r1, int r2, int r3) {
        zzqq r0 = zzh(r1);
        zzqf r12 = zzqg.zzc(r0.zzh());
        r12.zzh(r2);
        r12.zzi(r3);
        r0.zzi((zzqg) r12.zzu());
        return (zzqr) r0.zzu();
    }
}
