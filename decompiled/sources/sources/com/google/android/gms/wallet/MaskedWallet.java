package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.identity.intents.model.UserAddress;

/* loaded from: classes5.dex */
public final class MaskedWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MaskedWallet> CREATOR = null;
    String zza;
    String zzb;
    String[] zzc;
    String zzd;
    zza zze;
    zza zzf;
    LoyaltyWalletObject[] zzg;
    OfferWalletObject[] zzh;
    UserAddress zzi;
    UserAddress zzj;
    InstrumentInfo[] zzk;

    static {
        CREATOR = new zzt();
    }

    public MaskedWallet(String r1, String r2, String[] r3, String r4, zza r5, zza r6, LoyaltyWalletObject[] r7, OfferWalletObject[] r8, UserAddress r9, UserAddress r10, InstrumentInfo[] r11) {
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
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, this.zza, false);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeStringArray(r5, 4, this.zzc, false);
        SafeParcelWriter.writeString(r5, 5, this.zzd, false);
        SafeParcelWriter.writeParcelable(r5, 6, this.zze, r6, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzf, r6, false);
        SafeParcelWriter.writeTypedArray(r5, 8, this.zzg, r6, false);
        SafeParcelWriter.writeTypedArray(r5, 9, this.zzh, r6, false);
        SafeParcelWriter.writeParcelable(r5, 10, this.zzi, r6, false);
        SafeParcelWriter.writeParcelable(r5, 11, this.zzj, r6, false);
        SafeParcelWriter.writeTypedArray(r5, 12, this.zzk, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
