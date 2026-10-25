package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.icing.zzbp;
import com.google.firebase.appindexing.Action;

/* loaded from: classes5.dex */
public final class zzc extends AbstractSafeParcelable implements Action {
    public static final Parcelable.Creator<zzc> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final zzb zze;
    private final String zzf;
    private final Bundle zzg;

    static {
        CREATOR = new zzd();
    }

    public zzc(String r1, String r2, String r3, String r4, zzb r5, String r6, Bundle r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        if (r7 == null) goto L5;
        this.zzg = r7;
    L6:
        ClassLoader r12 = zzc.class.getClassLoader();
        zzbp.zza(r12);
        this.zzg.setClassLoader(r12);
        return;
    L5:
        this.zzg = Bundle.EMPTY;
        goto L6
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("ActionImpl { { actionType: '");
        r0.append(this.zza);
        r0.append("' } { objectName: '");
        r0.append(this.zzb);
        r0.append("' } { objectUrl: '");
        r0.append(this.zzc);
        r0.append("' } ");
        if (this.zzd == null) goto L6;
        r0.append("{ objectSameAs: '");
        r0.append(this.zzd);
        r0.append("' } ");
    L6:
        if (this.zze == null) goto L9;
        r0.append("{ metadata: '");
        r0.append(this.zze.toString());
        r0.append("' } ");
    L9:
        if (this.zzf == null) goto L12;
        r0.append("{ actionStatus: '");
        r0.append(this.zzf);
        r0.append("' } ");
    L12:
        if (this.zzg.isEmpty() == true) goto L14;
        r0.append("{ ");
        r0.append(this.zzg);
        r0.append(" } ");
    L14:
        r0.append("}");
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, this.zza, false);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeString(r5, 3, this.zzc, false);
        SafeParcelWriter.writeString(r5, 4, this.zzd, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zze, r6, false);
        SafeParcelWriter.writeString(r5, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(r5, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final zzb zza() {
        return this.zze;
    }
}
