package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zzlg implements Runnable {
    private final /* synthetic */ zzlj zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzlg(zzlj r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzic r0 = this.zza.zzu;
        if (r0.zzv().zzq(this.zzb) == false) goto L6;
        r0.zzv().zzi();
        return;
    }
}
