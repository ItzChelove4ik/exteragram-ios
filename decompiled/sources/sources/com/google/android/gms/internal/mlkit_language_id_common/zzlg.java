package com.google.android.gms.internal.mlkit_language_id_common;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzlg implements Runnable {
    public final /* synthetic */ zzli zza;
    public final /* synthetic */ zzla zzb;
    public final /* synthetic */ zzhy zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzlg(zzli r1, zzla r2, zzhy r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb(this.zzb, this.zzc, this.zzd);
    }
}
