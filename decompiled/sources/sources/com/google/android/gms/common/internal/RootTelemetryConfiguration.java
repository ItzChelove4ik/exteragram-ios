package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class RootTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RootTelemetryConfiguration> CREATOR = null;
    private final int zza;
    private final boolean zzb;
    private final boolean zzc;
    private final int zzd;
    private final int zze;

    static {
        CREATOR = new zzag();
    }

    public RootTelemetryConfiguration(int r1, boolean r2, boolean r3, int r4, int r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    public int getBatchPeriodMillis() {
        return this.zzd;
    }

    public int getMaxMethodInvocationsInBatch() {
        return this.zze;
    }

    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    public int getVersion() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, getVersion());
        SafeParcelWriter.writeBoolean(r3, 2, getMethodInvocationTelemetryEnabled());
        SafeParcelWriter.writeBoolean(r3, 3, getMethodTimingTelemetryEnabled());
        SafeParcelWriter.writeInt(r3, 4, getBatchPeriodMillis());
        SafeParcelWriter.writeInt(r3, 5, getMaxMethodInvocationsInBatch());
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
