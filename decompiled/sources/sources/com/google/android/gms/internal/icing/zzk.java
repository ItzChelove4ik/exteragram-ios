package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = null;
    public static final int zza = 0;
    private static final zzs zzf = null;
    public final String zzb;
    final zzs zzc;
    public final int zzd;
    public final byte[] zze;

    static {
        zza = Integer.parseInt("-1");
        CREATOR = new zzl();
        zzr r0 = new zzr("SsbContext");
        r0.zzb(true);
        r0.zza("blob");
        zzf = r0.zze();
    }

    public zzk(String r6, zzs r7, int r8, byte[] r9) {
        int r0 = zza;
        boolean r1 = true;
        if (r8 != r0) goto L5;
    L8:
        StringBuilder r2 = new StringBuilder(32);
        r2.append("Invalid section type ");
        r2.append(r8);
        Preconditions.checkArgument(r1, r2.toString());
        this.zzb = r6;
        this.zzc = r7;
        this.zzd = r8;
        this.zze = r9;
        if (r8 != r0) goto L11;
    L13:
        if (r6 == null) goto L16;
        if (r9 == null) goto L16;
        String r62 = "Both content and blobContent set";
    L17:
        if (r62 != null) goto L19;
        return;
    L19:
        f$$ExternalSyntheticBUOutline1.m(r62);
        throw null;
    L16:
        r62 = null;
        goto L17
    L11:
        if (zzq.zza(r8) != null) goto L13;
        StringBuilder r63 = new StringBuilder(32);
        r63.append("Invalid section type ");
        r63.append(r8);
        r62 = r63.toString();
        goto L17
    L5:
        if (zzq.zza(r8) != null) goto L8;
        r1 = false;
        goto L8
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        SafeParcelWriter.writeByteArray(r5, 5, this.zze, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
