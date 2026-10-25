package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ConnectionTelemetryConfiguration> CREATOR = null;
    private final RootTelemetryConfiguration zza;
    private final boolean zzb;
    private final boolean zzc;
    private final int[] zzd;
    private final int zze;
    private final int[] zzf;

    static {
        CREATOR = new zzl();
    }

    public ConnectionTelemetryConfiguration(RootTelemetryConfiguration r1, boolean r2, boolean r3, int[] r4, int r5, int[] r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public int getMaxMethodInvocationsLogged() {
        return this.zze;
    }

    public int[] getMethodInvocationMethodKeyAllowlist() {
        return this.zzd;
    }

    public int[] getMethodInvocationMethodKeyDisallowlist() {
        return this.zzf;
    }

    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, this.zza, r6, false);
        SafeParcelWriter.writeBoolean(r5, 2, getMethodInvocationTelemetryEnabled());
        SafeParcelWriter.writeBoolean(r5, 3, getMethodTimingTelemetryEnabled());
        SafeParcelWriter.writeIntArray(r5, 4, getMethodInvocationMethodKeyAllowlist(), false);
        SafeParcelWriter.writeInt(r5, 5, getMaxMethodInvocationsLogged());
        SafeParcelWriter.writeIntArray(r5, 6, getMethodInvocationMethodKeyDisallowlist(), false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final RootTelemetryConfiguration zza() {
        return this.zza;
    }
}
