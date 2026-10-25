package com.google.android.gms.cast.internal;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzfk;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
final class zzw extends zzai {
    private final AtomicReference zza;
    private final Handler zzb;

    public zzw(zzx r2) {
        this.zza = new AtomicReference(r2);
        this.zzb = new zzfk(r2.getLooper());
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzb(int r4) {
        zzx r3 = zzq();
        if (r3 == null) goto L10;
        Object[] r0 = {Integer.valueOf(r4)};
        zzx.zzN().d("ICastDeviceControllerListener.onDisconnected: %d", r0);
        if (r4 == 0) goto L9;
        r3.triggerConnectionSuspended(2);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzc(int r1) {
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzd(int r1) {
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zze(ApplicationMetadata r9, String r10, String r11, boolean r12) {
        zzx r8 = (zzx) this.zza.get();
        if (r8 != null) goto L5;
        return;
    L5:
        r8.zzO(r9);
        r8.zzT(r9.getApplicationId());
        r8.zzU(r11);
        r8.zzS(r10);
        Object r1 = zzx.zzX();
        monitor-enter(r1);
    L10:
        th = move-exception;
        throw th;
    L8:
        if (r8.zzV() == null) goto L12;
        r8.zzV().setResult(new zzr(new Status(0), r9, r10, r11, r12));     // Catch: Throwable -> L10
        r8.zzW(null);     // Catch: Throwable -> L10
    L12:
        monitor-exit(r1);     // Catch: Throwable -> L10
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzf(int r1) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zzH(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzg(int r1) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zzM(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzh(int r1) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zzM(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzi(int r4) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 == null) goto L10;
        r0.zzT(null);
        r0.zzU(null);
        r0.zzM(r4);
        if (r0.zzQ() == null) goto L9;
        this.zzb.post(new zzs(this, r0, r4));
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzj(String r1, double r2, boolean r4) {
        zzx.zzN().d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzk(zzac r5) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        zzx.zzN().d("onDeviceStatusChanged", new Object[0]);
        this.zzb.post(new zzt(this, r0, r5));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzl(zza r5) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        zzx.zzN().d("onApplicationStatusChanged", new Object[0]);
        this.zzb.post(new zzu(this, r0, r5));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzm(String r5, String r6) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        Logger r2 = zzx.zzN();
        r2.d("Receive (type=text, ns=%s) %s", new Object[]{r5, r6});
        this.zzb.post(new zzv(this, r0, r5, r6));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzn(String r1, byte[] r2) {
        if (((zzx) this.zza.get()) != null) goto L5;
        return;
    L5:
        int r0 = zzx.zze;
        Object[] r02 = {r1, Integer.valueOf(r2.length)};
        zzx.zzN().d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", r02);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzo(String r1, long r2, int r4) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zzL(r2, r4);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzp(String r1, long r2) {
        zzx r0 = (zzx) this.zza.get();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zzL(r2, 0);
    }

    public final zzx zzq() {
        zzx r1 = (zzx) this.zza.getAndSet(null);
        if (r1 != null) goto L5;
        return null;
    L5:
        r1.zzI();
        return r1;
    }

    public final boolean zzr() {
        if (this.zza.get() != null) goto L6;
        return true;
    L6:
        return false;
    }
}
