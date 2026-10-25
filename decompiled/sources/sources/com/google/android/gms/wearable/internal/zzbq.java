package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelClient;

/* loaded from: classes5.dex */
public final class zzbq extends AbstractSafeParcelable implements Channel, ChannelClient.Channel {
    public static final Parcelable.Creator<zzbq> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final String zzc;

    static {
        CREATOR = new zzbr();
    }

    public zzbq(String r1, String r2, String r3) {
        this.zza = (String) Preconditions.checkNotNull(r1);
        this.zzb = (String) Preconditions.checkNotNull(r2);
        this.zzc = (String) Preconditions.checkNotNull(r3);
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzbq) == true) goto L8;
        return false;
    L8:
        zzbq r52 = (zzbq) r5;
        if (this.zza.equals(r52.zza) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equal(r52.zzb, this.zzb) == false) goto L15;
        if (Objects.equal(r52.zzc, this.zzc) == false) goto L15;
        return true;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        char[] r0 = this.zza.toCharArray();
        int r1 = r0.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r4 = r4 + r0[r3];
        r3 = r3 + 1;
        goto L3
    L5:
        String r02 = this.zza.trim();
        int r12 = r02.length();
        if (r12 <= 25) goto L9;
        r02 = r02.substring(0, 10) + "..." + r02.substring(r12 - 10, r12) + "::" + r4;
    L9:
        return "Channel{token=" + r02 + ", nodeId=" + this.zzb + ", path=" + this.zzc + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zza, false);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.writeString(r4, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
