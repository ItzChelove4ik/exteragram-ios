package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzkj extends zzkr {
    private final TaskCompletionSource zza;

    public /* synthetic */ zzkj(TaskCompletionSource r1, byte[] r2) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzb(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzc(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzd(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zze(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzf(Status r1, zzjh r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzg(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzh(Status r1, zzjl r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzi(Status r1, zzjj r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzj(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzk(Status r1, zzjo r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzl(Status r1, zzjh r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzm(Status r1, long r2) {
        TaskUtil.setResultOrApiException(r1, null, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzn(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzo(Status r1, zzjs r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzp(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzks
    public final void zzq(Status r1, long r2) {
        TaskUtil.setResultOrApiException(r1, Long.valueOf(r2), this.zza);
    }
}
