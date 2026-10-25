package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzb> CREATOR = null;
    private int zza;
    private final boolean zzb;
    private final String zzc;
    private final String zzd;
    private final byte[] zze;
    private final boolean zzf;

    static {
        CREATOR = new zzy();
    }

    public zzb(int r1, boolean r2, String r3, String r4, byte[] r5, boolean r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public zzb(boolean r1, String r2, String r3, byte[] r4, boolean r5) {
        this.zza = 0;
        this.zzb = r1;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("MetadataImpl { { eventStatus: '");
        r0.append(this.zza);
        r0.append("' } { uploadable: '");
        r0.append(this.zzb);
        r0.append("' } ");
        if (this.zzc == null) goto L6;
        r0.append("{ completionToken: '");
        r0.append(this.zzc);
        r0.append("' } ");
    L6:
        if (this.zzd == null) goto L9;
        r0.append("{ accountName: '");
        r0.append(this.zzd);
        r0.append("' } ");
    L9:
        if (this.zze == null) goto L14;
        r0.append("{ ssbContext: [ ");
        byte[] r1 = this.zze;
        int r2 = r1.length;
        int r3 = 0;
    L11:
        if (r3 >= r2) goto L13;
        byte r4 = r1[r3];
        r0.append("0x");
        r0.append(Integer.toHexString(r4));
        r0.append(" ");
        r3 = r3 + 1;
        goto L11
    L13:
        r0.append("] } ");
    L14:
        r0.append("{ contextOnly: '");
        r0.append(this.zzf);
        r0.append("' } }");
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 1, this.zza);
        SafeParcelWriter.writeBoolean(r4, 2, this.zzb);
        SafeParcelWriter.writeString(r4, 3, this.zzc, false);
        SafeParcelWriter.writeString(r4, 4, this.zzd, false);
        SafeParcelWriter.writeByteArray(r4, 5, this.zze, false);
        SafeParcelWriter.writeBoolean(r4, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final void zza(int r1) {
        this.zza = r1;
    }
}
