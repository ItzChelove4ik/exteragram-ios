package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzcp extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzcp> CREATOR = null;
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final Boolean zzd;
    private final List zze;
    private final zzcp zzf;
    private final Long zzg;
    private final Long zzh;
    private final List zzi;
    private final Long zzj;
    private final Long zzk;
    private final Double zzl;
    private final Double zzm;
    private final Long zzn;
    private final Long zzo;
    private final String zzp;
    private final List zzq;
    private final List zzr;
    private final String zzs;
    private final Bundle zzt;

    static {
        CREATOR = new zzcq();
    }

    public zzcp(int r1, String r2, String r3, Boolean r4, List r5, zzcp r6, Long r7, Long r8, List r9, Long r10, Long r11, Double r12, Double r13, Long r14, Long r15, String r16, List r17, List r18, String r19, Bundle r20) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = r12;
        this.zzm = r13;
        this.zzn = r14;
        this.zzo = r15;
        this.zzp = r16;
        this.zzq = r17;
        this.zzr = r18;
        this.zzs = r19;
        this.zzt = r20;
    }

    public static zzco zzu() {
        zzu r0 = new zzu();
        r0.zza(0);
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r1, int r2) {
        zzcq.zza(this, r1, r2);
    }

    public final int zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final Boolean zzd() {
        return this.zzd;
    }

    public final List zze() {
        return this.zze;
    }

    public final zzcp zzf() {
        return this.zzf;
    }

    public final Long zzg() {
        return this.zzg;
    }

    public final Long zzh() {
        return this.zzh;
    }

    public final List zzi() {
        return this.zzi;
    }

    public final Long zzj() {
        return this.zzj;
    }

    public final Long zzk() {
        return this.zzk;
    }

    public final Double zzl() {
        return this.zzl;
    }

    public final Double zzm() {
        return this.zzm;
    }

    public final Long zzn() {
        return this.zzn;
    }

    public final Long zzo() {
        return this.zzo;
    }

    public final String zzp() {
        return this.zzp;
    }

    public final List zzq() {
        return this.zzq;
    }

    public final List zzr() {
        return this.zzr;
    }

    public final String zzs() {
        return this.zzs;
    }

    public final Bundle zzt() {
        return this.zzt;
    }
}
