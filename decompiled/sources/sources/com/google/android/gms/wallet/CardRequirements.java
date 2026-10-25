package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class CardRequirements extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CardRequirements> CREATOR = null;
    ArrayList zza;
    boolean zzb;
    boolean zzc;
    int zzd;

    static {
        CREATOR = new zzg();
    }

    public CardRequirements(ArrayList r1, boolean r2, boolean r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeIntegerList(r4, 1, this.zza, false);
        SafeParcelWriter.writeBoolean(r4, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.writeInt(r4, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
