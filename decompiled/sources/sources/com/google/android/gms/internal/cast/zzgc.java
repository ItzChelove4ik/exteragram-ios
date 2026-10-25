package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzgc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgc> CREATOR = null;
    private final int zza;
    private final boolean zzb;
    private final List zzc;
    private final int zzd;
    private final String zze;
    private final boolean zzf;

    static {
        CREATOR = new zzgd();
    }

    public zzgc(int r2, boolean r3, List r4, int r5, String r6, boolean r7) {
        ArrayList r0 = new ArrayList();
        this.zzc = r0;
        this.zza = r2;
        this.zzb = r3;
        if (r4 == null) goto L5;
        r0.addAll(r4);
    L5:
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzb);
        SafeParcelWriter.writeStringList(r4, 4, this.zzc, false);
        SafeParcelWriter.writeInt(r4, 5, this.zzd);
        SafeParcelWriter.writeString(r4, 6, this.zze, false);
        SafeParcelWriter.writeBoolean(r4, 7, this.zzf);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final int zza() {
        return this.zza;
    }
}
