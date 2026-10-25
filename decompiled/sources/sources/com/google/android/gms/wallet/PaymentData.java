package com.google.android.gms.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.identity.intents.model.UserAddress;

/* loaded from: classes5.dex */
public final class PaymentData extends AbstractSafeParcelable implements AutoResolvableResult {
    public static final Parcelable.Creator<PaymentData> CREATOR = null;
    String zza;
    CardInfo zzb;
    UserAddress zzc;
    PaymentMethodToken zzd;
    String zze;
    Bundle zzf;
    String zzg;
    Bundle zzh;

    static {
        CREATOR = new zzaa();
    }

    public PaymentData(String r1, CardInfo r2, UserAddress r3, PaymentMethodToken r4, String r5, Bundle r6, String r7, Bundle r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
    }

    public static PaymentData getFromIntent(Intent r2) {
        return (PaymentData) SafeParcelableSerializer.deserializeFromIntentExtra(r2, "com.google.android.gms.wallet.PaymentData", CREATOR);
    }

    @Override // com.google.android.gms.wallet.AutoResolvableResult
    public void putIntoIntent(Intent r2) {
        SafeParcelableSerializer.serializeToIntentExtra(this, r2, "com.google.android.gms.wallet.PaymentData");
    }

    public String toJson() {
        return this.zzg;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzb, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzd, r6, false);
        SafeParcelWriter.writeString(r5, 5, this.zze, false);
        SafeParcelWriter.writeBundle(r5, 6, this.zzf, false);
        SafeParcelWriter.writeString(r5, 7, this.zzg, false);
        SafeParcelWriter.writeBundle(r5, 8, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
