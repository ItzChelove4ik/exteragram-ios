package com.google.android.gms.internal.cast;

import android.os.Parcel;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionResult;

/* loaded from: classes4.dex */
public abstract class zzag extends zzb implements zzah {
    public zzag() {
        super("com.google.android.gms.cast.framework.devicesuggestions.internal.IDeviceSuggestionsCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        DeviceSuggestionResult r12 = (DeviceSuggestionResult) zzc.zzb(r2, DeviceSuggestionResult.CREATOR);
        zzc.zzf(r2);
        zzb(r12);
    L10:
        return true;
    L5:
        if (r1 == 2) goto L8;
        return false;
    L8:
        DeviceSuggestionResult r13 = (DeviceSuggestionResult) zzc.zzb(r2, DeviceSuggestionResult.CREATOR);
        zzc.zzf(r2);
        zzc(r13);
        goto L10
    }
}
