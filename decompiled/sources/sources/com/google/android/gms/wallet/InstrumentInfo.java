package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class InstrumentInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<InstrumentInfo> CREATOR = null;
    private String zza;
    private String zzb;
    private int zzc;

    static {
        CREATOR = new zzo();
    }

    public InstrumentInfo(String r1, String r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public int getCardClass() {
        int r1 = this.zzc;
        if (r1 != 1) goto L5;
        return r1;
    L5:
        if (r1 != 2) goto L7;
        return r1;
    L7:
        if (r1 == 3) goto L12;
        return 0;
    L12:
        return r1;
    }

    public String getInstrumentDetails() {
        return this.zzb;
    }

    public String getInstrumentType() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getInstrumentType(), false);
        SafeParcelWriter.writeString(r4, 3, getInstrumentDetails(), false);
        SafeParcelWriter.writeInt(r4, 4, getCardClass());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
