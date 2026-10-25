package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class PaymentMethodTokenizationParameters extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentMethodTokenizationParameters> CREATOR = null;
    int zza;
    final Bundle zzb;

    static {
        CREATOR = new zzaf();
    }

    public PaymentMethodTokenizationParameters(int r2, Bundle r3) {
        new Bundle();
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, this.zza);
        SafeParcelWriter.writeBundle(r3, 3, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
