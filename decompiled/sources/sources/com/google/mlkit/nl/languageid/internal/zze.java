package com.google.mlkit.nl.languageid.internal;

import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class zze implements Callable {
    public final /* synthetic */ LanguageIdentifierImpl zza;
    public final /* synthetic */ zzg zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ boolean zzd;

    public /* synthetic */ zze(LanguageIdentifierImpl r1, zzg r2, String r3, boolean r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
