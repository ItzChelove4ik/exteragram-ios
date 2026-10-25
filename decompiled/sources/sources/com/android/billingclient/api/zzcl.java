package com.android.billingclient.api;

import android.content.Context;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzhv;
import com.google.android.gms.internal.play_billing.zzhx;
import com.google.android.gms.internal.play_billing.zzhz;
import com.google.android.gms.internal.play_billing.zzib;
import com.google.android.gms.internal.play_billing.zzij;
import com.google.android.gms.internal.play_billing.zziq;
import com.google.android.gms.internal.play_billing.zzis;
import com.google.android.gms.internal.play_billing.zzja;
import com.google.android.gms.internal.play_billing.zzjg;
import com.google.android.gms.internal.play_billing.zzji;
import com.google.android.gms.internal.play_billing.zzjo;
import com.google.android.gms.internal.play_billing.zzjs;

/* loaded from: classes.dex */
final class zzcl implements zzch {
    private zzis zzb;
    private final zzcn zzc;

    public zzcl(Context r2, zzis r3) {
        this.zzc = new zzcn(r2);
        this.zzb = r3;
    }

    private final void zzl(zzhx r2, zzis r3) {
        if (r2 != null) goto L9;
        return;
    L9:
        zzjg r0 = zzji.zzc();     // Catch: Throwable -> L6
        r0.zzn(r3);     // Catch: Throwable -> L6
        r0.zza(r2);     // Catch: Throwable -> L6
        zzji r22 = (zzji) r0.zze();     // Catch: Throwable -> L6
        this.zzc.zza(r22);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    private final void zzm(zzib r2, zzis r3) {
        if (r2 != null) goto L9;
        return;
    L9:
        zzjg r0 = zzji.zzc();     // Catch: Throwable -> L6
        r0.zzn(r3);     // Catch: Throwable -> L6
        r0.zzl(r2);     // Catch: Throwable -> L6
        this.zzc.zza((zzji) r0.zze());     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zza(zzhx r2) {
        zzl(r2, this.zzb);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzb(zzhx r2, int r3) {
        zziq r0 = (zziq) this.zzb.zzm();     // Catch: Throwable -> L4
        r0.zzm(r3);     // Catch: Throwable -> L4
        this.zzb = (zzis) r0.zze();     // Catch: Throwable -> L4
        zza(r2);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzc(zzhx r3, int r4, long r5) {
        zziq r0 = (zziq) this.zzb.zzm();     // Catch: Throwable -> L8
        r0.zzm(r4);     // Catch: Throwable -> L8
        zzis r42 = (zzis) r0.zze();     // Catch: Throwable -> L8
        this.zzb = r42;     // Catch: Throwable -> L8
        if (r5 == 0) goto L6;
        zziq r43 = (zziq) r42.zzm();     // Catch: Throwable -> L8
        r43.zzo(r5);     // Catch: Throwable -> L8
        r42 = (zzis) r43.zze();     // Catch: Throwable -> L8
    L6:
        zzl(r3, r42);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzd(zzhx r3, long r4, boolean r6) {
        zzhv r0 = (zzhv) r3.zzm();     // Catch: Throwable -> L9
        zzja r32 = (zzja) r3.zzB().zzm();     // Catch: Throwable -> L9
        r32.zza(r6);     // Catch: Throwable -> L9
        r0.zzn(r32);     // Catch: Throwable -> L9
        zzhx r33 = (zzhx) r0.zze();     // Catch: Throwable -> L9
        zzis r02 = this.zzb;
        if (r4 == 0) goto L7;
        zziq r62 = (zziq) r02.zzm();     // Catch: Throwable -> L9
        r62.zzo(r4);     // Catch: Throwable -> L9
        r02 = (zzis) r62.zze();     // Catch: Throwable -> L9
    L7:
        zzl(r33, r02);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zze(zzhx r3, int r4, long r5, boolean r7) {
        zziq r0 = (zziq) this.zzb.zzm();     // Catch: Throwable -> L9
        r0.zzm(r4);     // Catch: Throwable -> L9
        this.zzb = (zzis) r0.zze();     // Catch: Throwable -> L9
        zzhv r42 = (zzhv) r3.zzm();     // Catch: Throwable -> L9
        zzja r32 = (zzja) r3.zzB().zzm();     // Catch: Throwable -> L9
        r32.zza(r7);     // Catch: Throwable -> L9
        r42.zzn(r32);     // Catch: Throwable -> L9
        zzhx r33 = (zzhx) r42.zze();     // Catch: Throwable -> L9
        zzis r72 = this.zzb;
        if (r5 == 0) goto L7;
        zziq r43 = (zziq) r72.zzm();     // Catch: Throwable -> L9
        r43.zzo(r5);     // Catch: Throwable -> L9
        r72 = (zzis) r43.zze();     // Catch: Throwable -> L9
    L7:
        zzl(r33, r72);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzf(zzib r2) {
        zzm(r2, this.zzb);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzg(zzib r2, int r3) {
        zziq r0 = (zziq) this.zzb.zzm();     // Catch: Throwable -> L4
        r0.zzm(r3);     // Catch: Throwable -> L4
        this.zzb = (zzis) r0.zze();     // Catch: Throwable -> L4
        zzf(r2);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzh(zzib r3, long r4, boolean r6) {
        zzhz r0 = (zzhz) r3.zzm();     // Catch: Throwable -> L9
        zzja r32 = (zzja) r3.zzA().zzm();     // Catch: Throwable -> L9
        r32.zza(r6);     // Catch: Throwable -> L9
        r0.zzm(r32);     // Catch: Throwable -> L9
        zzib r33 = (zzib) r0.zze();     // Catch: Throwable -> L9
        zzis r02 = this.zzb;
        if (r4 == 0) goto L7;
        zziq r62 = (zziq) r02.zzm();     // Catch: Throwable -> L9
        r62.zzo(r4);     // Catch: Throwable -> L9
        r02 = (zzis) r62.zze();     // Catch: Throwable -> L9
    L7:
        zzm(r33, r02);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzi(zzij r3) {
        zzjg r0 = zzji.zzc();     // Catch: Throwable -> L4
        r0.zzn(this.zzb);     // Catch: Throwable -> L4
        r0.zzm(r3);     // Catch: Throwable -> L4
        zzji r32 = (zzji) r0.zze();     // Catch: Throwable -> L4
        this.zzc.zza(r32);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzj(zzjo r3) {
        zzcn r0 = this.zzc;     // Catch: Throwable -> L4
        zzjg r1 = zzji.zzc();     // Catch: Throwable -> L4
        r1.zzn(this.zzb);     // Catch: Throwable -> L4
        r1.zzo(r3);     // Catch: Throwable -> L4
        r0.zza((zzji) r1.zze());     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }

    @Override // com.android.billingclient.api.zzch
    public final void zzk(zzjs r3) {
        if (r3 != null) goto L9;
        return;
    L9:
        zzjg r0 = zzji.zzc();     // Catch: Throwable -> L6
        r0.zzn(this.zzb);     // Catch: Throwable -> L6
        r0.zzp(r3);     // Catch: Throwable -> L6
        this.zzc.zza((zzji) r0.zze());     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        zzc.zzo("BillingLogger", "Unable to log.", th);
    }
}
