package com.google.android.gms.internal.recaptchabase;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptchabase.ExecuteResult;
import com.google.android.gms.recaptchabase.InitResult;

/* loaded from: classes5.dex */
public abstract class zzd extends zzb implements zze {
    public zzd() {
        super("com.google.android.gms.recaptchabase.internal.IRecaptchaBaseCallbacks");
    }

    @Override // com.google.android.gms.internal.recaptchabase.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        Status r12 = (Status) zzc.zza(r2, Status.CREATOR);
        InitResult r42 = (InitResult) zzc.zza(r2, InitResult.CREATOR);
        zzc.zzb(r2);
        zzc(r12, r42);
    L10:
        return true;
    L5:
        if (r1 == 2) goto L8;
        return false;
    L8:
        Status r13 = (Status) zzc.zza(r2, Status.CREATOR);
        ExecuteResult r43 = (ExecuteResult) zzc.zza(r2, ExecuteResult.CREATOR);
        zzc.zzb(r2);
        zzb(r13, r43);
        goto L10
    }
}
