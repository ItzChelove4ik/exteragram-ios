package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public interface zzq {
    /* synthetic */ ApiKey getApiKey();

    boolean zza();

    void zzb(zzp r1);

    Task zzc();

    Task zzd();

    Task zze();

    Task zzf(String r1, String r2);

    Task zzg(String r1, LaunchOptions r2);

    Task zzh(String r1);

    Task zzi(double r1);

    double zzj();

    Task zzk(boolean r1);

    boolean zzl();

    int zzm();

    int zzn();

    ApplicationMetadata zzo();

    String zzp();

    Task zzq(String r1, Cast.MessageReceivedCallback r2);

    Task zzr(String r1);
}
