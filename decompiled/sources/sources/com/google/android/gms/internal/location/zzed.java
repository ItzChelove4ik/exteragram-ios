package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzv;

/* loaded from: classes4.dex */
public final class zzed extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzed> CREATOR = null;
    private final int zza;
    private final IBinder zzb;
    private final IBinder zzc;
    private final PendingIntent zzd;
    private final String zze;

    static {
        CREATOR = new zzee();
    }

    public zzed(int r1, IBinder r2, IBinder r3, PendingIntent r4, String r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.os.IBinder, com.google.android.gms.location.zzv] */
    public static zzed zzb(IInterface r6, zzv r7, String r8) {
        if (r6 != null) goto L6;
        r6 = null;
    L6:
        return new zzed(2, r6, r7, null, r8);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeIBinder(r5, 2, this.zzb, false);
        SafeParcelWriter.writeIBinder(r5, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzd, r6, false);
        SafeParcelWriter.writeString(r5, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
