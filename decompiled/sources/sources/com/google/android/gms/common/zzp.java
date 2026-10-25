package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public final class zzp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzp> CREATOR = null;
    private final String zza;
    private final boolean zzb;
    private final boolean zzc;
    private final Context zzd;
    private final boolean zze;
    private final boolean zzf;
    private final boolean zzg;

    static {
        CREATOR = new zzq();
    }

    public zzp(String r1, boolean r2, boolean r3, IBinder r4, boolean r5, boolean r6, boolean r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = (Context) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(r4));
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [android.os.IBinder, com.google.android.gms.dynamic.IObjectWrapper] */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeBoolean(r4, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.writeIBinder(r4, 4, ObjectWrapper.wrap(this.zzd), false);
        SafeParcelWriter.writeBoolean(r4, 5, this.zze);
        SafeParcelWriter.writeBoolean(r4, 6, this.zzf);
        SafeParcelWriter.writeBoolean(r4, 8, this.zzg);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
