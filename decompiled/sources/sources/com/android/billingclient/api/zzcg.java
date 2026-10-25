package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzbj;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzhv;
import com.google.android.gms.internal.play_billing.zzhx;
import com.google.android.gms.internal.play_billing.zzhz;
import com.google.android.gms.internal.play_billing.zzib;
import com.google.android.gms.internal.play_billing.zzic;
import com.google.android.gms.internal.play_billing.zzie;
import com.google.android.gms.internal.play_billing.zzig;
import com.google.android.gms.internal.play_billing.zzil;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class zzcg {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        int r0 = zzch.$r8$clinit;
    }

    public static String zza(Exception r3) {
        if (r3 != null) goto L14;
        return null;
    L14:
        String r32 = r3.getClass().getSimpleName() + ":" + zzbj.zzb(r3.getMessage());     // Catch: Throwable -> L9
        int r1 = zzc.zza;     // Catch: Throwable -> L9
        if (r32.length() <= 40) goto L11;
        return r32.substring(0, 40);
    L11:
        return r32;
    L9:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to get truncated exception info", th);
        return null;
    }

    public static zzhx zzb(zzie r2, int r3, BillingResult r4, String r5, zzil r6) {
        zzic r0 = zzig.zzc();     // Catch: Throwable -> L14
        r0.zzo(r4.getResponseCode());     // Catch: Throwable -> L14
        r0.zzl(r4.getDebugMessage());     // Catch: Throwable -> L14
        if (r4.getOnPurchasesUpdatedSubResponseCode() == 0) goto L5;
        r0.zzm(r4.getOnPurchasesUpdatedSubResponseCode());     // Catch: Throwable -> L14
    L5:
        if (r2 == null) goto L7;
        r0.zzn(r2);     // Catch: Throwable -> L14
    L7:
        if (r5 == null) goto L9;
        r0.zza(r5);     // Catch: Throwable -> L14
    L9:
        zzhv r22 = zzhx.zzc();     // Catch: Throwable -> L14
        r22.zzl(r0);     // Catch: Throwable -> L14
        r22.zzp(r3);     // Catch: Throwable -> L14
        if (r6.equals(zzil.zza) == true) goto L12;
        r22.zza(r6);     // Catch: Throwable -> L14
    L12:
        return (zzhx) r22.zze();
    L14:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to create logging payload", th);
        return null;
    }

    public static zzib zzc(int r1, zzil r2) {
        zzhz r0 = zzib.zzc();     // Catch: Exception -> L7
        r0.zzo(r1);     // Catch: Exception -> L7
        if (r2.equals(zzil.zza) == true) goto L5;
        r0.zza(r2);     // Catch: Exception -> L7
    L5:
        return (zzib) r0.zze();
    L7:
        e = move-exception;
        zzc.zzo("BillingLogger", "Unable to create logging payload", e);
        return null;
    }
}
